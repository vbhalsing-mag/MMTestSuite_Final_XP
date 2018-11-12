package com.mm.dto;

import java.util.List;
import java.util.Map;


public class ClaimsDTO {

	public static String addFilePageTitle;
	public static String entitySearchListPageTitle;
	public static String lastName;
	public static String firstName;
	public static String fileTypeDropDownValue;
	public static String lobDropDownValue;
	public static String fileHandlerDropDownValue;
	public static String stateOfLossDropDownValue;
	public static String searchEntityPageTitle;
	public static String description;
	public String FileSearchPageTitle;
	public String claimNum;
	public String clientIDValue;
	public String clientNameValue;
	public String seperateCheck;
	public List<String> transactionType;
	public String paymentType;
	public List<String> vendorIDValue;
	public List<String> taxIDType;
	public List<String> transactionAmount;
	public List<String> invoiceNo;
	public List<String> payeeName;
	public String policyNum;
	public String CoverageDescription;

	//This method will access DTO Code.
	public ClaimsDTO(Map<String, List<String>> excelData) {

		for (int i = 0; i <= ClaimsDTO.class.getFields().length - 1; i++) {
			if (excelData.containsKey(ClaimsDTO.class.getFields()[i].getName().toLowerCase())) {
				if (ClaimsDTO.class.getFields()[i].getType().toString().toLowerCase().contains("java.util.list")) {
					try {
						ClaimsDTO.class.getFields()[i].set(this,
								excelData.get(ClaimsDTO.class.getFields()[i].getName().toLowerCase()));

					} catch (Exception e) {
						e.printStackTrace();
					}

				} else if (ClaimsDTO.class.getFields()[i].getType().toString().toLowerCase().contains("int")) {
					try {
						ClaimsDTO.class.getFields()[i].set(this, Integer.parseInt(
								excelData.get(ClaimsDTO.class.getFields()[i].getName().toLowerCase()).get(0)));

					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					try {
						ClaimsDTO.class.getFields()[i].set(this,
								excelData.get(ClaimsDTO.class.getFields()[i].getName().toLowerCase()).get(0));

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

	}
}
