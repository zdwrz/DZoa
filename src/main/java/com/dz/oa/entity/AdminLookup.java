package com.dz.oa.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the admin_lookup database table.
 * 
 */
@Entity
@Table(name="admin_lookup")
@NamedQuery(name="AdminLookup.findAll", query="SELECT a FROM AdminLookup a")
public class AdminLookup implements Serializable {

	private static final long serialVersionUID = -7564025934322238048L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;

	private String abbrv;

	private String description;

	@Column(name="inactive_ind")
	private String inactiveInd;

	private String type;

	private String value;
//
//	//bi-directional many-to-one association to EnterpriseContact
//	@OneToMany(mappedBy="adminLookup")
//	private List<EnterpriseContact> enterpriseContacts;
//
//	//bi-directional many-to-one association to UserRole
//	@OneToMany(mappedBy="adminLookup")
//	private List<UserRole> userRoles;
//
//	//bi-directional many-to-one association to TsApproval
//	@OneToMany(mappedBy="adminLookup")
//	private List<TsApproval> tsApprovals;

	public AdminLookup() {
	}

    public AdminLookup(int id) {
    	this.id = id;
    }

    public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAbbrv() {
		return this.abbrv;
	}

	public void setAbbrv(String abbrv) {
		this.abbrv = abbrv;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getInactiveInd() {
		return this.inactiveInd;
	}

	public void setInactiveInd(String inactiveInd) {
		this.inactiveInd = inactiveInd;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

//	public List<EnterpriseContact> getEnterpriseContacts() {
//		return this.enterpriseContacts;
//	}
//
//	public void setEnterpriseContacts(List<EnterpriseContact> enterpriseContacts) {
//		this.enterpriseContacts = enterpriseContacts;
//	}

//	public EnterpriseContact addEnterpriseContact(EnterpriseContact enterpriseContact) {
//		getEnterpriseContacts().add(enterpriseContact);
//		enterpriseContact.setAdminLookup(this);
//
//		return enterpriseContact;
//	}
//
//	public EnterpriseContact removeEnterpriseContact(EnterpriseContact enterpriseContact) {
//		getEnterpriseContacts().remove(enterpriseContact);
//		enterpriseContact.setAdminLookup(null);
//
//		return enterpriseContact;
//	}

//	public List<UserRole> getUserRoles() {
//		return this.userRoles;
//	}
//
//	public void setUserRoles(List<UserRole> userRoles) {
//		this.userRoles = userRoles;
//	}

//	public UserRole addUserRole(UserRole userRole) {
//		getUserRoles().add(userRole);
//		userRole.setAdminLookup(this);
//
//		return userRole;
//	}
//
//	public UserRole removeUserRole(UserRole userRole) {
//		getUserRoles().remove(userRole);
//		userRole.setAdminLookup(null);
//
//		return userRole;
//	}

//	public List<TsApproval> getTsApprovals() {
//		return this.tsApprovals;
//	}
//
//	public void setTsApprovals(List<TsApproval> tsApprovals) {
//		this.tsApprovals = tsApprovals;
//	}

//	public TsApproval addTsApproval(TsApproval tsApproval) {
//		getTsApprovals().add(tsApproval);
//		tsApproval.setAdminLookup(this);
//
//		return tsApproval;
//	}
//
//	public TsApproval removeTsApproval(TsApproval tsApproval) {
//		getTsApprovals().remove(tsApproval);
//		tsApproval.setAdminLookup(null);
//
//		return tsApproval;
//	}

}