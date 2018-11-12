package com.mm.dto;

import java.util.List;
import java.util.Map;

public class RateAPolicyPageDTO {

    public List<String> coverageNames;
    public List<String> binderForms;
    public List<String> phase;
    public List<String> coverage;
    public String valueOfPolicyActionAccept;
    public String valueOfPolicyActionBillingSetup;
    public String paymentPlanValue;
    public String productNotifyValue;
    public String footerContent;
    public String optionValue;
    public String optionName;
    public String saveAsPolicyValue;
    public String listDDLValue;
    public String coverageFromCoverageTabGrid;
    public String manuscriptForm;
    public String viewModeOfficial;
    public String viewModeWIP;
    public String policyAction;
    public String endorsementReason;
    public String policyNum;
    public String policyNo;
    public String endorsementComment;
    public String backUpPolicyNum;
    public String policyPhaseValue;

    // This method will access DTO Code.
    public RateAPolicyPageDTO(Map<String, List<String>> excelData) {

        for (int i = 0; i <= RateAPolicyPageDTO.class.getFields().length - 1; i++) {
            if (excelData.containsKey(RateAPolicyPageDTO.class.getFields()[i].getName().toLowerCase())) {
                if (RateAPolicyPageDTO.class.getFields()[i].getType().toString().toLowerCase()
                        .contains("java.util.list")) {

                    // if
                    // (excelData.containsKey(RateAPolicyPageDTO.class.getFields()[i].getName().toLowerCase())){
                    // {
                    try {
                        RateAPolicyPageDTO.class.getFields()[i].set(this,
                                excelData.get(RateAPolicyPageDTO.class.getFields()[i].getName().toLowerCase()));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else if (RateAPolicyPageDTO.class.getFields()[i].getType().toString().toLowerCase().contains("int")) {
                    try {
                        RateAPolicyPageDTO.class.getFields()[i].set(this, Integer.parseInt(
                                excelData.get(RateAPolicyPageDTO.class.getFields()[i].getName().toLowerCase()).get(0)));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        RateAPolicyPageDTO.class.getFields()[i].set(this,
                                excelData.get(RateAPolicyPageDTO.class.getFields()[i].getName().toLowerCase()).get(0));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }
}
