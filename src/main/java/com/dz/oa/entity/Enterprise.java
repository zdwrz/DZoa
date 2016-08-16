package com.dz.oa.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the enterprise database table.
 * 
 */
@Entity
@NamedQuery(name="Enterprise.findAll", query="SELECT e FROM Enterprise e")
public class Enterprise implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;

	@Column(name="inactive_ind")
	private String inactiveInd = "N";

	private String name;

	//bi-directional many-to-one association to EnterpriseContact
	@OneToMany(mappedBy="enterprise")
	private List<EnterpriseContact> enterpriseContacts;

	//bi-directional many-to-one association to EnterpriseModule
	@OneToMany(mappedBy="enterprise")
	private List<EnterpriseModule> enterpriseModules;

	//bi-directional many-to-one association to UserRole
	@OneToMany(mappedBy="enterprise")
	private List<UserRole> userRoles;

	//bi-directional many-to-one association to Project
	@OneToMany(mappedBy="enterprise")
	private List<Project> projects;

	//bi-directional many-to-one association to ProjectLookup
	@OneToMany(mappedBy="enterprise")
	private List<ProjectLookup> projectLookups;

	public Enterprise() {
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<EnterpriseContact> getEnterpriseContacts() {
		return this.enterpriseContacts;
	}

	public void setEnterpriseContacts(List<EnterpriseContact> enterpriseContacts) {
		this.enterpriseContacts = enterpriseContacts;
	}

	public EnterpriseContact addEnterpriseContact(EnterpriseContact enterpriseContact) {
		getEnterpriseContacts().add(enterpriseContact);
		enterpriseContact.setEnterprise(this);

		return enterpriseContact;
	}

	public EnterpriseContact removeEnterpriseContact(EnterpriseContact enterpriseContact) {
		getEnterpriseContacts().remove(enterpriseContact);
		enterpriseContact.setEnterprise(null);

		return enterpriseContact;
	}

	public List<EnterpriseModule> getEnterpriseModules() {
		return this.enterpriseModules;
	}

	public void setEnterpriseModules(List<EnterpriseModule> enterpriseModules) {
		this.enterpriseModules = enterpriseModules;
	}

	public EnterpriseModule addEnterpriseModule(EnterpriseModule enterpriseModule) {
		getEnterpriseModules().add(enterpriseModule);
		enterpriseModule.setEnterprise(this);

		return enterpriseModule;
	}

	public EnterpriseModule removeEnterpriseModule(EnterpriseModule enterpriseModule) {
		getEnterpriseModules().remove(enterpriseModule);
		enterpriseModule.setEnterprise(null);

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
		userRole.setEnterprise(this);

		return userRole;
	}

	public UserRole removeUserRole(UserRole userRole) {
		getUserRoles().remove(userRole);
		userRole.setEnterprise(null);

		return userRole;
	}

	public List<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public Project addProject(Project project) {
		getProjects().add(project);
		project.setEnterprise(this);

		return project;
	}

	public Project removeProject(Project project) {
		getProjects().remove(project);
		project.setEnterprise(null);

		return project;
	}

	public List<ProjectLookup> getProjectLookups() {
		return this.projectLookups;
	}

	public void setProjectLookups(List<ProjectLookup> projectLookups) {
		this.projectLookups = projectLookups;
	}

	public ProjectLookup addProjectLookup(ProjectLookup projectLookup) {
		getProjectLookups().add(projectLookup);
		projectLookup.setEnterprise(this);

		return projectLookup;
	}

	public ProjectLookup removeProjectLookup(ProjectLookup projectLookup) {
		getProjectLookups().remove(projectLookup);
		projectLookup.setEnterprise(null);

		return projectLookup;
	}

}