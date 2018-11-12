package com.mm.dto;

import java.util.List;
import java.util.Map;


public class CISPageDTO {

	public String OrgName;
	public String addressLine1;
	public String City;
	public String phoneNumber;
	public String areaCode;
	public String classEffctToDate;
	public String zipCode;
	public String Classification;
	public String addrType;
	public String State;
	public String dateOfBirth;
	public String phoneNumType;

	public String clientFirstName;
	public String clientLastName;
	public String clientNameValue;
	public String clientIDValue;
	public List<String> mainTabList;
	public List<String> personInfoTabsListbyID;
	public List<String> demographicTabMenuOption;
	public List<String> windowTitlesForDemographicTabs;
	public List<String> summaryTabMenuOption;
	public List<String> windowTitlesForSubMenuTabs;
	public List<String> backgroundTabMenuOption;
	public List<String> windowTitlesForBackgroundTabs;
	public List<String> relationTabMenuOption;
	public List<String> windowTitlesForRelationTabs;
	public List<String> vendorTabMenuOption;
	public List<String> windowTitlesForVendorTabs;
	public List<String> auditTabMenuOption;
	public List<String> windowTitlesForAuditTabs;
	public List<String> allMenuOptions;

	//This method will access DTO Code.
	public CISPageDTO(Map<String, List<String>> excelData) {

		for (int i = 0; i <= CISPageDTO.class.getFields().length - 1; i++) {
			if (excelData.containsKey(CISPageDTO.class.getFields()[i].getName().toLowerCase())) {
				if (CISPageDTO.class.getFields()[i].getType().toString().toLowerCase().contains("java.util.list")) {
					try {
						CISPageDTO.class.getFields()[i].set(this,
								excelData.get(CISPageDTO.class.getFields()[i].getName().toLowerCase()));

					} catch (Exception e) {
						e.printStackTrace();
					}

				} else if (CISPageDTO.class.getFields()[i].getType().toString().toLowerCase().contains("int")) {
					try {
						CISPageDTO.class.getFields()[i].set(this, Integer.parseInt(
								excelData.get(CISPageDTO.class.getFields()[i].getName().toLowerCase()).get(0)));

					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					try {
						CISPageDTO.class.getFields()[i].set(this,
								excelData.get(CISPageDTO.class.getFields()[i].getName().toLowerCase()).get(0));

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		}
	}

}