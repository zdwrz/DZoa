package com.dz.oa.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the admin_um_lookup database table.
 * 
 */
@Entity
@Table(name="admin_um_lookup")
@NamedQuery(name="AdminUmLookup.findAll", query="SELECT a FROM AdminUmLookup a")
public class AdminUmLookup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;

	private String abbrv;

	private String description;

	@Column(name="inactive_ind")
	private String inactiveInd;

	private String type;

	private String value;

	//bi-directional many-to-one association to EnterpriseModule
	@OneToMany(mappedBy="adminUmLookup")
	private List<EnterpriseModule> enterpriseModules;

	//bi-directional many-to-one association to UserRole
	@OneToMany(mappedBy= "position")
	private List<UserRole> userRoles;

	public AdminUmLookup() {
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

	public List<EnterpriseModule> getEnterpriseModules() {
		return this.enterpriseModules;
	}

	public void setEnterpriseModules(List<EnterpriseModule> enterpriseModules) {
		this.enterpriseModules = enterpriseModules;
	}

	public EnterpriseModule addEnterpriseModule(EnterpriseModule enterpriseModule) {
		getEnterpriseModules().add(enterpriseModule);
		enterpriseModule.setAdminUmLookup(this);

		return enterpriseModule;
	}

	public EnterpriseModule removeEnterpriseModule(EnterpriseModule enterpriseModule) {
		getEnterpriseModules().remove(enterpriseModule);
		enterpriseModule.setAdminUmLookup(null);

		return enterpriseModule;
	}

	public List<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public UserRole addUserRole(UserRole userRole) {
		getUserRoles().add(userRole);
		userRole.setPosition(this);

		return userRole;
	}

	public UserRole removeUserRole(UserRole userRole) {
		getUserRoles().remove(userRole);
		userRole.setPosition(null);

		return userRole;
	}

}