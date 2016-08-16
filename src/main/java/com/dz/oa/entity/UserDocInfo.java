package com.dz.oa.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the user_doc_info database table.
 * 
 */
@Entity
@Table(name="user_doc_info")
@NamedQuery(name="UserDocInfo.findAll", query="SELECT u FROM UserDocInfo u")
public class UserDocInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;

	@Column(name="doc_name")
	private String docName;

	@Column(name="file_location")
	private String fileLocation;

	@Column(name="file_type")
	private String fileType;

	@Column(name="inactive_ind")
	private String inactiveInd;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="upload_time")
	private Date uploadTime;

	//bi-directional many-to-one association to EnterpriseLookup
	@ManyToOne
	@JoinColumn(name="doc_type")
	private EnterpriseLookup enterpriseLookup;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	public UserDocInfo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDocName() {
		return this.docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getFileLocation() {
		return this.fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	public String getFileType() {
		return this.fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getInactiveInd() {
		return this.inactiveInd;
	}

	public void setInactiveInd(String inactiveInd) {
		this.inactiveInd = inactiveInd;
	}

	public Date getUploadTime() {
		return this.uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public EnterpriseLookup getEnterpriseLookup() {
		return this.enterpriseLookup;
	}

	public void setEnterpriseLookup(EnterpriseLookup enterpriseLookup) {
		this.enterpriseLookup = enterpriseLookup;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}