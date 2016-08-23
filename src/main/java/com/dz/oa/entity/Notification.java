package com.dz.oa.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the notification database table.
 * 
 */
@Entity
@NamedQueries({
		@NamedQuery(name="Notification.findToday",query = "select n from Notification n where n.inactiveInd != 'Y' and CURRENT_DATE between n.startDate and n.expirationDate"),
		@NamedQuery(name="Notification.findAll", query="SELECT n FROM Notification n")
})
public class Notification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String content;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="expiration_date")
	private Date expirationDate;

	@Column(name="inactive_ind")
	private String inactiveInd;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_date")
	private Date startDate;

	private String title;

	//bi-directional many-to-one association to Project
	@ManyToOne
	private Project project;

	//bi-directional many-to-one association to AdminLookup
	@ManyToOne
	@JoinColumn(name="type_code_id")
	private AdminLookup type;

	public Notification() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getExpirationDate() {
		return this.expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getInactiveInd() {
		return this.inactiveInd;
	}

	public void setInactiveInd(String inactiveInd) {
		this.inactiveInd = inactiveInd;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public AdminLookup getType() {
		return this.type;
	}

	public void setType(AdminLookup type) {
		this.type = type;
	}

}