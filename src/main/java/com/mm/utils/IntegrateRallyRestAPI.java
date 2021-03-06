package com.mm.utils;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.TimeZone;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rallydev.rest.RallyRestApi;
import com.rallydev.rest.request.CreateRequest;
import com.rallydev.rest.request.QueryRequest;
import com.rallydev.rest.response.CreateResponse;
import com.rallydev.rest.response.QueryResponse;
import com.rallydev.rest.util.Fetch;
import com.rallydev.rest.util.QueryFilter;
import com.rallydev.rest.util.Ref;

public class IntegrateRallyRestAPI {

    public IntegrateRallyRestAPI() {

    }

    // Method to update the results in Rally
    public String updateResultsInRally(String serverURL, String username, String password, String workspace,
            String project, String testcaseFormattedID, String buildNumber, String notes, String userRef,
            double duration, String verdict, String attachmentsPath) throws URISyntaxException, IOException

    {

        // Initialize variables
        String errorString = "";
        // String workspaceId = "";
        String testcaseRefID;
        String testcaseRefURL;

        // Initialize REST connection object
        @SuppressWarnings("deprecation")
        RallyRestApi oRestApi = new RallyRestApi(new URI(serverURL), username, password);

        /*
         * //Read Test Set - kept for Future extension QueryRequest
         * testSetRequest = new QueryRequest("TestSet");
         * testSetRequest.setFetch(new Fetch("Name", "Subscription",
         * "DisplayName", "SubscriptionAdmin"));
         * testSetRequest.setQueryFilter(new QueryFilter("Name", "=",
         * "SmokeTest")); QueryResponse testSetQueryResponse =
         * oRestApi.query(testSetRequest); JsonArray testSetQueryResults =
         * testSetQueryResponse.getResults(); JsonElement testSetQueryElement =
         * testSetQueryResults.get(0); JsonObject testSetQueryObject =
         * testSetQueryElement.getAsJsonObject(); String testSetRef =
         * testSetQueryObject.get("_ref").getAsString();
         */

        // Get the test case reference
        QueryRequest testCaseReq = new QueryRequest("TestCase");
        testCaseReq.setFetch(new Fetch("FormattedID", "Rally REST API Enhancment Object ID to Formatted ID"));
        testCaseReq.setQueryFilter(new QueryFilter("FormattedID", "=", testcaseFormattedID));
        QueryResponse testCaseQueryResponse = oRestApi.query(testCaseReq);

        // retrieve the test case reference id.
        testcaseRefURL = testCaseQueryResponse.getResults().get(0).getAsJsonObject().get("_ref").getAsString();
        testcaseRefID = testcaseRefURL.substring(testcaseRefURL.lastIndexOf("/") + 1).toString();
        if (testcaseRefID.equals("")) {
            errorString = "Test case with id ".concat(testcaseFormattedID).concat("not found");
        } else {
            // Thread.sleep(5000);
            // Create the test case result PayLoad
            // {"TestCaseResult": {"Build": "A","Date":
            // "2018-05-03T21:42:13.000Z","Notes":
            // "Logs indicate pass, however code still doesn't produce expected
            // result.",
            // "TestCase":
            // "https://rally1.rallydev.com/slm/webservice/v2.0/testcase/211672493520",
            // "Verdict": "Pass"}}
            JsonObject testCaseResultPayload = new JsonObject();

            TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
            testCaseResultPayload.addProperty("Verdict", verdict);
            testCaseResultPayload.addProperty("Build", buildNumber);
            testCaseResultPayload.addProperty("Date",
                    new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.000'Z'").format(new Date()));
            testCaseResultPayload.addProperty("TestCase", testcaseRefURL);
            testCaseResultPayload.addProperty("Notes", notes);
            testCaseResultPayload.addProperty("Tester", userRef);
            testCaseResultPayload.addProperty("Duration", duration);
            // testCaseResultPayload.addProperty("TestSet", duration);
            CreateRequest createRequest = new CreateRequest("testcaseresult", testCaseResultPayload);
            CreateResponse createResponse = oRestApi.create(createRequest);
            if (!createResponse.wasSuccessful()) {
                errorString = "Error occured while creating results for test with formatted ID "
                        .concat(testcaseFormattedID).concat(".Error is ").concat(createResponse.getErrors().toString());
            } else {
                errorString = "Test case result created successfully for test with formatted ID "
                        .concat(testcaseFormattedID);
                System.out.println(String.format("Created %s", createResponse.getObject().get("_ref").getAsString()));

                // Read Test Case Result
                String testCaseResultRef = Ref.getRelativeRef(createResponse.getObject().get("_ref").getAsString());
                /*
                 * System.out.println(String.
                 * format("\nReading Test Case Result %s...",
                 * testCaseResultRef)); GetRequest getRequest = new
                 * GetRequest(testCaseResultRef); getRequest.setFetch(new
                 * Fetch("Date", "Verdict")); GetResponse getResponse =
                 * oRestApi.get(getRequest); JsonObject obj =
                 * getResponse.getObject(); System.out.println(String.
                 * format("Read Test Case Result. Date = %s, Verdict = %s",
                 * obj.get("Date").getAsString(),
                 * obj.get("Verdict").getAsString()));
                 */

                try {
                    if (!attachmentsPath.equals("")) {
                        String[] attachmentFilePath = attachmentsPath.split(";");

                        for (int iFile = 0; iFile < attachmentFilePath.length; iFile++) {
                            // Get and check length
                            RandomAccessFile myImageFileHandle = new RandomAccessFile(attachmentFilePath[iFile], "r");
                            long longLength = myImageFileHandle.length();
                            long maxLength = 5000000;
                            if (longLength >= maxLength) {
                                myImageFileHandle.close();
                                throw new IOException("File size >= 5 MB Upper limit for Rally.");
                            }
                            int fileLength = (int) longLength;

                            // Read file and return data
                            byte[] fileBytes = new byte[fileLength];
                            myImageFileHandle.readFully(fileBytes);
                            String imageBase64String = Base64.getEncoder().encodeToString(fileBytes);
                            int attachmentSize = fileLength;

                            // First create AttachmentContent from file string
                            JsonObject myAttachmentContent = new JsonObject();
                            myAttachmentContent.addProperty("Content", imageBase64String);
                            CreateRequest attachmentContentCreateRequest = new CreateRequest("AttachmentContent",
                                    myAttachmentContent);
                            CreateResponse attachmentContentResponse = oRestApi.create(attachmentContentCreateRequest);
                            String myAttachmentContentRef = attachmentContentResponse.getObject().get("_ref")
                                    .getAsString();
                            System.out.println("Attachment Content created: " + myAttachmentContentRef);

                            // Now create the Attachment itself
                            JsonObject myAttachment = new JsonObject();
                            myAttachment.addProperty("TestCaseResult", testCaseResultRef);
                            myAttachment.addProperty("Content", myAttachmentContentRef);
                            myAttachment.addProperty("Name", attachmentFilePath[iFile]
                                    .substring(attachmentFilePath[iFile].lastIndexOf("\\") + 1));
                            myAttachment.addProperty("Description", "Attachment as expected by Test");
                            myAttachment.addProperty("ContentType", "File");
                            myAttachment.addProperty("Size", attachmentSize);
                            myAttachment.addProperty("User", userRef);

                            CreateRequest attachmentCreateRequest = new CreateRequest("Attachment", myAttachment);
                            CreateResponse attachmentResponse = oRestApi.create(attachmentCreateRequest);
                            String myAttachmentRef = attachmentResponse.getObject().get("_ref").getAsString();
                            System.out.println("Attachment  created: " + myAttachmentRef);

                            if (attachmentResponse.wasSuccessful()) {
                                System.out.println("Successfully created Attachment to the test case "
                                        .concat(testcaseFormattedID));
                            } else {
                                String[] attachmentContentErrors;
                                attachmentContentErrors = attachmentResponse.getErrors();
                                System.out.println("Error occurred creating Attachment: ");
                                for (int i = 0; i < attachmentContentErrors.length; i++) {
                                    System.out.println(attachmentContentErrors[i]);
                                }
                            }
                            myImageFileHandle.close();
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Exception occurred while attempting to create Content and/or Attachment: ");
                    e.printStackTrace();
                }
            }
        }
        oRestApi.close();
        return errorString;

    }

    // Get the user reference, separating this out in a method as it could be
    // called
    // only once.
    public String getRallyUserDetails(String serverURL, String username, String password)
            throws URISyntaxException, IOException

    {

        // Initialize variables
        String userRef = "USER NOT FOUND";

        // Initialize REST connection object

        @SuppressWarnings("deprecation")
        RallyRestApi oRestApi = new RallyRestApi(new URI(serverURL), username, password);

        // Read User
        QueryRequest userRequest = new QueryRequest("User");
        userRequest.setFetch(new Fetch("UserName", "Subscription", "DisplayName", "SubscriptionAdmin"));
        userRequest.setQueryFilter(new QueryFilter("UserName", "=", username));
        QueryResponse userQueryResponse = oRestApi.query(userRequest);
        JsonArray userQueryResults = userQueryResponse.getResults();
        JsonElement userQueryElement = userQueryResults.get(0);
        JsonObject userQueryObject = userQueryElement.getAsJsonObject();
        userRef = userQueryObject.get("_ref").getAsString();

        oRestApi.close();

        // Return user details
        return userRef;

    }

    // Encode a password.. call separately
    public String encodePassword(String password) {
        // Encode data on your side using BASE64
        byte[] bytesEncoded = Base64.getEncoder().encode(password.getBytes());
        return new String(bytesEncoded);
    }

    // Decode an encoded password String. Call this before passing the password.
    public String decodeString(String encodedString) {

        // Decode data on other side, by processing encoded data
        byte[] valueDecoded = Base64.getDecoder().decode(encodedString.getBytes());
        return new String(valueDecoded);
    }

}
