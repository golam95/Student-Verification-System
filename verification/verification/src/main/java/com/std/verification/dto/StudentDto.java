package com.std.verification.dto;


import lombok.Data;

@Data
public class StudentDto {
	private String updateId;
	private Long uniId;
	private Long deptId;
	private String stdRollNo;
	private String name;
	private String email;
	private String contactNo;
	private String batch;
	private String status;
	//
	private String stdID;
	private String stdName;
	private String stdDepartment;
}
