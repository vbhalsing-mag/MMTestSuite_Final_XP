package com.mm.dto;

import java.util.List;
import java.util.Map;

public class pdfReaderDTO {

	public List<String> verifyPDFcontent;

	//This method will access DTO Code.
	public pdfReaderDTO(Map<String, List<String>> excelData) {

		for (int i = 0; i <= pdfReaderDTO.class.getFields().length - 1; i++) {
			if (excelData.containsKey(pdfReaderDTO.class.getFields()[i].getName().toLowerCase())) {
				if (pdfReaderDTO.class.getFields()[i].getType().toString().toLowerCase().contains("java.util.list")) {
					try {
						pdfReaderDTO.class.getFields()[i].set(this,
								excelData.get(pdfReaderDTO.class.getFields()[i].getName().toLowerCase()));

					} catch (Exception e) {
						e.printStackTrace();
					}

				} else if (pdfReaderDTO.class.getFields()[i].getType().toString().toLowerCase().contains("int")) {
					try {
						pdfReaderDTO.class.getFields()[i].set(this, Integer.parseInt(
								excelData.get(pdfReaderDTO.class.getFields()[i].getName().toLowerCase()).get(0)));

					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					try {
						pdfReaderDTO.class.getFields()[i].set(this,
								excelData.get(pdfReaderDTO.class.getFields()[i].getName().toLowerCase()).get(0));

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
