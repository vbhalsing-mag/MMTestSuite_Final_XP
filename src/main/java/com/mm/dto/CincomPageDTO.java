package com.mm.dto;

import java.util.List;
import java.util.Map;

public class CincomPageDTO {

    public List<String> coverage;
    public List<String> coverageList;
    public List<String> phase;

    // This method will access DTO Code.
    public CincomPageDTO(Map<String, List<String>> excelData) {

        for (int i = 0; i <= CincomPageDTO.class.getFields().length - 1; i++) {
            if (excelData.containsKey(CincomPageDTO.class.getFields()[i].getName().toLowerCase())) {
                if (CincomPageDTO.class.getFields()[i].getType().toString().toLowerCase().contains("java.util.list")) {
                    try {
                        CincomPageDTO.class.getFields()[i].set(this,
                                excelData.get(CincomPageDTO.class.getFields()[i].getName().toLowerCase()));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else if (CincomPageDTO.class.getFields()[i].getType().toString().toLowerCase().contains("int")) {
                    try {
                        CincomPageDTO.class.getFields()[i].set(this, Integer.parseInt(
                                excelData.get(CincomPageDTO.class.getFields()[i].getName().toLowerCase()).get(0)));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        CincomPageDTO.class.getFields()[i].set(this,
                                excelData.get(CincomPageDTO.class.getFields()[i].getName().toLowerCase()).get(0));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
