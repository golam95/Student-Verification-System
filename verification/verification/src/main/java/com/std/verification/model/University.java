package com.std.verification.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
@Table(name = "university")
public class University {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long unID;

	@Column(name = "name")
	private String universityName;
	
	@Column(name = "shortName")
	private String uniShortName;
	
	@Column(name = "date")
	private Date date;
}
