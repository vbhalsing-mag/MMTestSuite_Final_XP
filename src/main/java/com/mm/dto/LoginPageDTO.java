package com.mm.dto;

import java.util.List;
import java.util.Map;

public class LoginPageDTO {
	public String username;
	public String password;

	//This method will access DTO Code.
	public LoginPageDTO(Map<String, List<String>> excelData) {

		for (int i = 0; i <= LoginPageDTO.class.getFields().length - 1; i++) {
			if (excelData.containsKey(LoginPageDTO.class.getFields()[i].getName().toLowerCase())) {
				if (LoginPageDTO.class.getFields()[i].getType().toString().toLowerCase().contains("java.util.list")) {
					try {
						LoginPageDTO.class.getFields()[i].set(this,
								excelData.get(LoginPageDTO.class.getFields()[i].getName().toLowerCase()));

					} catch (Exception e) {
						e.printStackTrace();
					}

				} else if (LoginPageDTO.class.getFields()[i].getType().toString().toLowerCase().contains("int")) {
					try {
						LoginPageDTO.class.getFields()[i].set(this, Integer.parseInt(
								excelData.get(LoginPageDTO.class.getFields()[i].getName().toLowerCase()).get(0)));

					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					try {
						LoginPageDTO.class.getFields()[i].set(this,
								excelData.get(LoginPageDTO.class.getFields()[i].getName().toLowerCase()).get(0));

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
