package com.mm.dto;

import java.util.List;
import java.util.Map;

public class PolicyQuotePageDTO {

    public String policyPhaseValue;
    public String policyactionvalue;
    public String saveAsPolicyDDLValue;
    public String secondSaveAsPolicyDDLValue;
    public String valueOfPolicyActionCopy;
    public String quotePhaseValue;
    public String riskType;
    public String binderForm;
    public String saveAsPolicyValue;
    public String saveAsPolicyValueRenewal;
    public String saveAsPolicyValueOfficial;
    public String policyActionValue;
    public String productNotifyValue;
    public String quoteDescriptionText;
    public List<String> coverages;
    public List<String> phases;
    public String columnNumber;

    // This method will access DTO Code.
    public PolicyQuotePageDTO(Map<String, List<String>> excelData) {

        for (int i = 0; i <= PolicyQuotePageDTO.class.getFields().length - 1; i++) {
            if (excelData.containsKey(PolicyQuotePageDTO.class.getFields()[i].getName().toLowerCase())) {
                if (PolicyQuotePageDTO.class.getFields()[i].getType().toString().toLowerCase()
                        .contains("java.util.list")) {
                    try {
                        PolicyQuotePageDTO.class.getFields()[i].set(this,
                                excelData.get(PolicyQuotePageDTO.class.getFields()[i].getName().toLowerCase()));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else if (PolicyQuotePageDTO.class.getFields()[i].getType().toString().toLowerCase().contains("int")) {
                    try {
                        PolicyQuotePageDTO.class.getFields()[i].set(this, Integer.parseInt(
                                excelData.get(PolicyQuotePageDTO.class.getFields()[i].getName().toLowerCase()).get(0)));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        PolicyQuotePageDTO.class.getFields()[i].set(this,
                                excelData.get(PolicyQuotePageDTO.class.getFields()[i].getName().toLowerCase()).get(0));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }

    }
}
