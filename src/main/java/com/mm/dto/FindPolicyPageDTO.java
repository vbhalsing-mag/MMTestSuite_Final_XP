package com.mm.dto;

import java.util.List;
import java.util.Map;

public class FindPolicyPageDTO {

    public static String lastTransaction;
    public static String phaseValue;
    public static String status;
    public static String termStatusCode;
    public static String policyTypeValue;
    public static String issueCompanyValue;
    public static String columnNo;

    // This method will access DTO Code.
    public FindPolicyPageDTO(Map<String, List<String>> excelData) {

        for (int i = 0; i <= FindPolicyPageDTO.class.getFields().length - 1; i++) {
            if (excelData.containsKey(FindPolicyPageDTO.class.getFields()[i].getName().toLowerCase())) {
                if (FindPolicyPageDTO.class.getFields()[i].getType().toString().toLowerCase()
                        .contains("java.util.list")) {
                    try {
                        FindPolicyPageDTO.class.getFields()[i].set(this,
                                excelData.get(FindPolicyPageDTO.class.getFields()[i].getName().toLowerCase()));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else if (FindPolicyPageDTO.class.getFields()[i].getType().toString().toLowerCase().contains("int")) {
                    try {
                        FindPolicyPageDTO.class.getFields()[i].set(this, Integer.parseInt(
                                excelData.get(FindPolicyPageDTO.class.getFields()[i].getName().toLowerCase()).get(0)));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        FindPolicyPageDTO.class.getFields()[i].set(this,
                                excelData.get(FindPolicyPageDTO.class.getFields()[i].getName().toLowerCase()).get(0));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
