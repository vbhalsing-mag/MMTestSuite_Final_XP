package com.mm.dto;

import java.util.List;
import java.util.Map;

public class PolicyIndicationPageDTO {

    public List<String> underwriterName;
    public List<String> teamName;
    public List<String> teamMembername;
    public List<String> coverage;
    public List<String> retroDate;
    public List<String> premiumAmount;
    public List<String> retroDateValue;
    public List<String> coverageTitle;
    public List<String> coverageName;
    public List<String> form;
    public List<String> sharedGroupCoverage;
    public List<String> sharedGroupDescription;
    public String coverageLimit;
    public String policyAction;
    public String producer;
    public List<String> riskCounty;
    public List<String> riskSpeciality;
    public String coverageFromPopup;
    public String policyForms;
    public String coverageClass;
    public String exposureUnit;
    public List<String> riskTypeValue;
    public String riskName;
    public String coverageNameForRisk;
    public List<String> FTEType;
    public List<String> riskTypeOrganization;
    public List<String> riskEntityName;
    public String stateCodeValue;
    public String policyPhaseValue;
    public String policyPhaseValue2;

    // This method will access DTO Code.
    public PolicyIndicationPageDTO(Map<String, List<String>> excelData)
    /* throws IllegalArgumentException, IllegalAccessException, SecurityException */ {

        for (int i = 0; i <= PolicyIndicationPageDTO.class.getFields().length - 1; i++) {
            if (excelData.containsKey(PolicyIndicationPageDTO.class.getFields()[i].getName().toLowerCase())) {
                if (PolicyIndicationPageDTO.class.getFields()[i].getType().toString().toLowerCase()
                        .contains("java.util.list")) {
                    try {
                        PolicyIndicationPageDTO.class.getFields()[i].set(this,
                                excelData.get(PolicyIndicationPageDTO.class.getFields()[i].getName().toLowerCase()));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else if (PolicyIndicationPageDTO.class.getFields()[i].getType().toString().toLowerCase()
                        .contains("int")) {
                    try {
                        PolicyIndicationPageDTO.class.getFields()[i].set(this, Integer.parseInt(excelData
                                .get(PolicyIndicationPageDTO.class.getFields()[i].getName().toLowerCase()).get(0)));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        PolicyIndicationPageDTO.class.getFields()[i].set(this, excelData
                                .get(PolicyIndicationPageDTO.class.getFields()[i].getName().toLowerCase()).get(0));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
