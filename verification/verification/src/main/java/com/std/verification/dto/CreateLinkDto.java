package com.std.verification.dto;

import lombok.Data;

@Data
public class CreateLinkDto {
	private String updateId;
	private Long uniId;
	private String urlName;
	private String hasNewImage;
}
