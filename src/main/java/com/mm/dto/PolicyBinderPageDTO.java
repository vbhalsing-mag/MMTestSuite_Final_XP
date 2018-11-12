package com.mm.dto;

import java.util.List;
import java.util.Map;

public class PolicyBinderPageDTO {

	public String policyNumber;
	public String valueOfPolicyActionEndorse;
	public String saveAsPolicyValue;
	public String ProductNotifyValue;
	public String valueOfSelectReason;
	public String valueOfPolicyActionCopyToQuote;
	public String FileSearchPageTitle;
	public String addFilePageTitle;
	public String entitySelectListPageTitle;
	public String entitySearchListPageTitle;
	public String fileTypeDropDownValue;
	public String lobDropDownValue;
	public String fileHandlerDropDownValue;
	public String stateOfLossDropDownValue;
	public String searchEntityPageTitle;
	public String lastName;
	public String firstName;
	public String description;
	public String policyPhaseValueBinder;
	public String policyPhaseValuePolicy;

	//This method will access DTO Code.
	public PolicyBinderPageDTO(Map<String, List<String>> excelData) {

		for (int i = 0; i <= PolicyBinderPageDTO.class.getFields().length - 1; i++) {
			if (excelData.containsKey(PolicyBinderPageDTO.class.getFields()[i].getName().toLowerCase())) {
				if (PolicyBinderPageDTO.class.getFields()[i].getType().toString().toLowerCase()
						.contains("java.util.list")) {
					try {
						PolicyBinderPageDTO.class.getFields()[i].set(this,
								excelData.get(PolicyBinderPageDTO.class.getFields()[i].getName().toLowerCase()));

					} catch (Exception e) {
						e.printStackTrace();
					}

				} else if (PolicyBinderPageDTO.class.getFields()[i].getType().toString().toLowerCase()
						.contains("int")) {
					try {
						PolicyBinderPageDTO.class.getFields()[i].set(this, Integer.parseInt(excelData
								.get(PolicyBinderPageDTO.class.getFields()[i].getName().toLowerCase()).get(0)));

					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					try {
						PolicyBinderPageDTO.class.getFields()[i].set(this,
								excelData.get(PolicyBinderPageDTO.class.getFields()[i].getName().toLowerCase()).get(0));

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

	}

}
