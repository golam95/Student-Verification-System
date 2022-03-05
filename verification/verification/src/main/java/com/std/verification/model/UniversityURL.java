package com.std.verification.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "url")
public class UniversityURL {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long urlId;

	@Column(name = "uni_id")
	private Long uniId;

	@Column(name = "url_name")
	private String urlName;
	
	@Column(name = "date")
	private Date date;
	
	@Lob
    @Column(name = "logo")
    private byte[] uniLogoBlob;
	
}
