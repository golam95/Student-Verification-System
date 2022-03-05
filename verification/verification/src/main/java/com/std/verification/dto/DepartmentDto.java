package com.std.verification.dto;


import java.util.Date;
import lombok.Data;

@Data
public class DepartmentDto {
	private String updateId;
	private String deptName;
	private String uniName;
	private Long uniId;
	private Long uniIdList;
	private Date date;

}
