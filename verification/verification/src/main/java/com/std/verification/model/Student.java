package com.std.verification.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long stdId;

	@Column(name = "u_id")
	private Long uniId;

	@Column(name = "dept_id")
	private Long deptId;

	@Column(name = "std_id")
	private String stdRollNo;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "contact_no")
	private String contactNo;

	@Column(name = "date")
	private Date date;
	
	@Column(name = "batch")
	private String batch;
	
	@Column(name = "status")
	private String status;
	
	
	

}
