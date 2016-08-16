package com.dz.oa.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the ts_approval database table.
 * 
 */
@Entity
@Table(name="ts_approval")
@NamedQuery(name="TsApproval.findAll", query="SELECT t FROM TsApproval t")
public class TsApproval implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;

	@Column(name="inactive_ind")
	private String inactiveInd;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="status_date")
	private Date statusDate;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="approver_id")
	private User user;

	//bi-directional many-to-one association to AdminLookup
	@ManyToOne
	@JoinColumn(name="status_id")
	private AdminLookup adminLookup;

	//bi-directional many-to-one association to TsMain
	@ManyToOne
	@JoinColumn(name="ts_main_id")
	private TsMain tsMain;

	public TsApproval() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInactiveInd() {
		return this.inactiveInd;
	}

	public void setInactiveInd(String inactiveInd) {
		this.inactiveInd = inactiveInd;
	}

	public Date getStatusDate() {
		return this.statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public AdminLookup getAdminLookup() {
		return this.adminLookup;
	}

	public void setAdminLookup(AdminLookup adminLookup) {
		this.adminLookup = adminLookup;
	}

	public TsMain getTsMain() {
		return this.tsMain;
	}

	public void setTsMain(TsMain tsMain) {
		this.tsMain = tsMain;
	}

}