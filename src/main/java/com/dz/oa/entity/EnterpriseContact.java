package com.dz.oa.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the enterprise_contact database table.
 * 
 */
@Entity
@Table(name="enterprise_contact")
@NamedQuery(name="EnterpriseContact.findAll", query="SELECT e FROM EnterpriseContact e")
public class EnterpriseContact implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;

	@Column(name="inactive_ind")
	private String inactiveInd;

	@Temporal(TemporalType.TIMESTAMP)
	private Date value_D;

	private int value_N;

	private String value_X;

	//bi-directional many-to-one association to AdminLookup
	@ManyToOne
	@JoinColumn(name="contact_type")
	private AdminLookup adminLookup;

	//bi-directional many-to-one association to Enterprise
	@ManyToOne
	private Enterprise enterprise;

	public EnterpriseContact() {
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

	public Date getValue_D() {
		return this.value_D;
	}

	public void setValue_D(Date value_D) {
		this.value_D = value_D;
	}

	public int getValue_N() {
		return this.value_N;
	}

	public void setValue_N(int value_N) {
		this.value_N = value_N;
	}

	public String getValue_X() {
		return this.value_X;
	}

	public void setValue_X(String value_X) {
		this.value_X = value_X;
	}

	public AdminLookup getAdminLookup() {
		return this.adminLookup;
	}

	public void setAdminLookup(AdminLookup adminLookup) {
		this.adminLookup = adminLookup;
	}

	public Enterprise getEnterprise() {
		return this.enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

}