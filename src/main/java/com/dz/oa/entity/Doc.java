package com.dz.oa.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the docs database table.
 * 
 */
@Entity
@Table(name="docs")
@NamedQuery(name="Doc.findAll", query="SELECT d FROM Doc d")
public class Doc implements Serializable {

	private static final long serialVersionUID = -6914039304814388339L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Lob
	@Column(name="doc_content")
	private byte[] docContent;

	@Column(name="doc_name")
	private String docName;

	//bi-directional many-to-one association to UserDocInfo
	@OneToOne(mappedBy="doc")
	private UserDocInfo userDocInfo;

	public Doc() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getDocContent() {
		return this.docContent;
	}

	public void setDocContent(byte[] docContent) {
		this.docContent = docContent;
	}

	public String getDocName() {
		return this.docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public UserDocInfo getUserDocInfo() {
		return this.userDocInfo;
	}

	public void setUserDocInfo(UserDocInfo userDocInfo) {
		this.userDocInfo = userDocInfo;
	}

	

}