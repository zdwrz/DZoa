package com.dz.oa.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the user_role database table.
 * 
 */
@Entity
@Table(name="user_role")
@NamedQuery(name="UserRole.findAll", query="SELECT u FROM UserRole u")
public class UserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_date")
	private Date endDate;

	@Column(name="inactive_ind")
	private String inactiveInd;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_date")
	private Date startDate;

	//bi-directional many-to-one association to AdminLookup
	@ManyToOne
	@JoinColumn(name="role_id")
	private AdminLookup adminLookup;

	//bi-directional many-to-one association to AdminUmLookup
	@ManyToOne
	@JoinColumn(name="position_id")
	private AdminUmLookup adminUmLookup;

	//bi-directional many-to-one association to Enterprise
	@ManyToOne
	private Enterprise enterprise;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	public UserRole() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

	public AdminLookup getAdminLookup() {
		return this.adminLookup;
	}

	public void setAdminLookup(AdminLookup adminLookup) {
		this.adminLookup = adminLookup;
	}

	public AdminUmLookup getAdminUmLookup() {
		return this.adminUmLookup;
	}

	public void setAdminUmLookup(AdminUmLookup adminUmLookup) {
		this.adminUmLookup = adminUmLookup;
	}

	public Enterprise getEnterprise() {
		return this.enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}