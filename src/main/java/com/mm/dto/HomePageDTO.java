package com.mm.dto;

import java.util.List;
import java.util.Map;

public class HomePageDTO {

	public String lastOrgName;
	public String effectiveFromDate;
	public String issueCompany;
	public String issueState;
	public String policyNum;
	public String policyNo;
	public String clientFirstName;
	public String clientLastName;
	public String backUpPpolicyNum;

	//This method will access DTO Code.
	public HomePageDTO(Map<String, List<String>> excelData) {

		for (int i = 0; i <= HomePageDTO.class.getFields().length - 1; i++) {
			if (excelData.containsKey(HomePageDTO.class.getFields()[i].getName().toLowerCase())) {
				if (HomePageDTO.class.getFields()[i].getType().toString().toLowerCase().contains("java.util.list")) {
					try {
						HomePageDTO.class.getFields()[i].set(this,
								excelData.get(HomePageDTO.class.getFields()[i].getName().toLowerCase()));

					} catch (Exception e) {
						e.printStackTrace();
					}

				} else if (HomePageDTO.class.getFields()[i].getType().toString().toLowerCase().contains("int")) {
					try {
						HomePageDTO.class.getFields()[i].set(this, Integer.parseInt(
								excelData.get(HomePageDTO.class.getFields()[i].getName().toLowerCase()).get(0)));

					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					try {
						HomePageDTO.class.getFields()[i].set(this,
								excelData.get(HomePageDTO.class.getFields()[i].getName().toLowerCase()).get(0));

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
