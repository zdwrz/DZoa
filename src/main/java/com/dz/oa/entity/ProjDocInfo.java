package com.dz.oa.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the user_doc_info database table.
 * 
 */
@Entity
@Table(name="project_doc_info")
@NamedQueries({
		@NamedQuery(name = "ProjDocInfo.findAll", query = "SELECT p FROM ProjDocInfo p"),
		@NamedQuery(name = "ProjDocInfo.findByProjId", query = "SELECT p FROM ProjDocInfo p where p.project.id=:projId and p.inactiveInd = 'N' order by p.uploadTime desc"),
		@NamedQuery(name = "ProjDocInfo.findById", query = "SELECT distinct p FROM ProjDocInfo p where p.id=:fileId and p.inactiveInd = 'N'")
})
public class ProjDocInfo implements Serializable {

	private static final long serialVersionUID = -5339552333573407188L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "doc_name")
	private String docName;

	@Column(name = "file_location")
	private String fileLocation;

	@Column(name = "file_type")
	private String fileType;

	@Column(name = "inactive_ind")
	private String inactiveInd = "N";

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "uploaded_time")
	private Date uploadTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "file_time")
	private Date fileTime;

	//bi-directional many-to-one association to EnterpriseLookup
	@ManyToOne
	@JoinColumn(name = "category")
	private EnterpriseLookup category;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	@ManyToOne
	@JoinColumn(name="proj_id")
	private Project project;

	public ProjDocInfo() {
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

	public EnterpriseLookup getCategory() {
		return this.category;
	}

	public void setCategory(EnterpriseLookup enterpriseLookup) {
		this.category = enterpriseLookup;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getFileTime() {
		return fileTime;
	}

	public void setFileTime(Date fileTime) {
		this.fileTime = fileTime;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
}