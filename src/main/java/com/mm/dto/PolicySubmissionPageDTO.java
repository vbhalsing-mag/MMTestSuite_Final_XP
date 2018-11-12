package com.mm.dto;

import java.util.List;
import java.util.Map;

public class PolicySubmissionPageDTO {

	public String policyPhase;
	public String organisationType;
	public String discoveryPeriodRating;
	public String quoteDescription;
	public String valueOfPolicyActionCopy;
	public String indicationPhaseValue;

	//This method will access DTO Code.
	public PolicySubmissionPageDTO(Map<String, List<String>> excelData) {

		for (int i = 0; i <= PolicySubmissionPageDTO.class.getFields().length - 1; i++) {
			if (excelData.containsKey(PolicySubmissionPageDTO.class.getFields()[i].getName().toLowerCase())) {
				if (PolicySubmissionPageDTO.class.getFields()[i].getType().toString().toLowerCase()
						.contains("java.util.list")) {
					try {
						PolicySubmissionPageDTO.class.getFields()[i].set(this,
								excelData.get(PolicySubmissionPageDTO.class.getFields()[i].getName().toLowerCase()));

					} catch (Exception e) {
						e.printStackTrace();
					}

				} else if (PolicySubmissionPageDTO.class.getFields()[i].getType().toString().toLowerCase()
						.contains("int")) {
					try {
						PolicySubmissionPageDTO.class.getFields()[i].set(this, Integer.parseInt(excelData
								.get(PolicySubmissionPageDTO.class.getFields()[i].getName().toLowerCase()).get(0)));

					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					try {
						PolicySubmissionPageDTO.class.getFields()[i].set(this, excelData
								.get(PolicySubmissionPageDTO.class.getFields()[i].getName().toLowerCase()).get(0));

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
