package com.dz.oa.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;

	@Column(name="inactive_ind")
	private String inactiveInd;

	private String password;

	@Column(name="user_name")
	private String userName;

	//bi-directional many-to-one association to UserRole
	@OneToMany(mappedBy="user")
	private List<UserRole> userRoles;

	//bi-directional many-to-one association to ProjectUserAssoc
	@OneToMany(mappedBy="user")
	private List<ProjectUserAssoc> projectUserAssocs;

	//bi-directional many-to-one association to TsApproval
	@OneToMany(mappedBy="user")
	private List<TsApproval> tsApprovals;

	//bi-directional many-to-one association to TsEmpSchedule
	@OneToMany(mappedBy="user")
	private List<TsEmpSchedule> tsEmpSchedules;

	//bi-directional many-to-one association to TsMain
	@OneToMany(mappedBy="user")
	private List<TsMain> tsMains;

	//bi-directional many-to-one association to UserDetail
	@OneToOne(mappedBy="user")
	private UserDetail userDetails;

	//bi-directional many-to-one association to UserDocInfo
	@OneToMany(mappedBy="user")
	private List<UserDocInfo> userDocInfos;

	public User() {
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

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public UserRole addUserRole(UserRole userRole) {
		getUserRoles().add(userRole);
		userRole.setUser(this);

		return userRole;
	}

	public UserRole removeUserRole(UserRole userRole) {
		getUserRoles().remove(userRole);
		userRole.setUser(null);

		return userRole;
	}

	public List<ProjectUserAssoc> getProjectUserAssocs() {
		return this.projectUserAssocs;
	}

	public void setProjectUserAssocs(List<ProjectUserAssoc> projectUserAssocs) {
		this.projectUserAssocs = projectUserAssocs;
	}

	public ProjectUserAssoc addProjectUserAssoc(ProjectUserAssoc projectUserAssoc) {
		getProjectUserAssocs().add(projectUserAssoc);
		projectUserAssoc.setUser(this);

		return projectUserAssoc;
	}

	public ProjectUserAssoc removeProjectUserAssoc(ProjectUserAssoc projectUserAssoc) {
		getProjectUserAssocs().remove(projectUserAssoc);
		projectUserAssoc.setUser(null);

		return projectUserAssoc;
	}

	public List<TsApproval> getTsApprovals() {
		return this.tsApprovals;
	}

	public void setTsApprovals(List<TsApproval> tsApprovals) {
		this.tsApprovals = tsApprovals;
	}

	public TsApproval addTsApproval(TsApproval tsApproval) {
		getTsApprovals().add(tsApproval);
		tsApproval.setUser(this);

		return tsApproval;
	}

	public TsApproval removeTsApproval(TsApproval tsApproval) {
		getTsApprovals().remove(tsApproval);
		tsApproval.setUser(null);

		return tsApproval;
	}

	public List<TsEmpSchedule> getTsEmpSchedules() {
		return this.tsEmpSchedules;
	}

	public void setTsEmpSchedules(List<TsEmpSchedule> tsEmpSchedules) {
		this.tsEmpSchedules = tsEmpSchedules;
	}

	public TsEmpSchedule addTsEmpSchedule(TsEmpSchedule tsEmpSchedule) {
		getTsEmpSchedules().add(tsEmpSchedule);
		tsEmpSchedule.setUser(this);

		return tsEmpSchedule;
	}

	public TsEmpSchedule removeTsEmpSchedule(TsEmpSchedule tsEmpSchedule) {
		getTsEmpSchedules().remove(tsEmpSchedule);
		tsEmpSchedule.setUser(null);

		return tsEmpSchedule;
	}

	public List<TsMain> getTsMains() {
		return this.tsMains;
	}

	public void setTsMains(List<TsMain> tsMains) {
		this.tsMains = tsMains;
	}

	public TsMain addTsMain(TsMain tsMain) {
		getTsMains().add(tsMain);
		tsMain.setUser(this);

		return tsMain;
	}

	public TsMain removeTsMain(TsMain tsMain) {
		getTsMains().remove(tsMain);
		tsMain.setUser(null);

		return tsMain;
	}

	public UserDetail getUserDetails() {
		return this.userDetails;
	}

	public void setUserDetails(UserDetail userDetails) {
		this.userDetails = userDetails;
	}

	public List<UserDocInfo> getUserDocInfos() {
		return this.userDocInfos;
	}

	public void setUserDocInfos(List<UserDocInfo> userDocInfos) {
		this.userDocInfos = userDocInfos;
	}

	public UserDocInfo addUserDocInfo(UserDocInfo userDocInfo) {
		getUserDocInfos().add(userDocInfo);
		userDocInfo.setUser(this);

		return userDocInfo;
	}

	public UserDocInfo removeUserDocInfo(UserDocInfo userDocInfo) {
		getUserDocInfos().remove(userDocInfo);
		userDocInfo.setUser(null);

		return userDocInfo;
	}

}