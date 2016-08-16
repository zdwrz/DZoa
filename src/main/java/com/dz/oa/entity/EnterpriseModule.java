package com.dz.oa.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the enterprise_module database table.
 * 
 */
@Entity
@Table(name="enterprise_module")
@NamedQuery(name="EnterpriseModule.findAll", query="SELECT e FROM EnterpriseModule e")
public class EnterpriseModule implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String inactiveInd;
	private AdminUmLookup adminUmLookup;
	private Enterprise enterprise;
	private List<EnterpriseModuleDetail> enterpriseModuleDetails;

	public EnterpriseModule() {
	}


	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Column(name="inactive_ind")
	public String getInactiveInd() {
		return this.inactiveInd;
	}

	public void setInactiveInd(String inactiveInd) {
		this.inactiveInd = inactiveInd;
	}


	//bi-directional many-to-one association to AdminUmLookup
	@ManyToOne
	@JoinColumn(name="module_id")
	public AdminUmLookup getAdminUmLookup() {
		return this.adminUmLookup;
	}

	public void setAdminUmLookup(AdminUmLookup adminUmLookup) {
		this.adminUmLookup = adminUmLookup;
	}


	//bi-directional many-to-one association to Enterprise
	@ManyToOne
	public Enterprise getEnterprise() {
		return this.enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}


	//bi-directional many-to-one association to EnterpriseModuleDetail
	@OneToMany(mappedBy="enterpriseModule")
	public List<EnterpriseModuleDetail> getEnterpriseModuleDetails() {
		return this.enterpriseModuleDetails;
	}

	public void setEnterpriseModuleDetails(List<EnterpriseModuleDetail> enterpriseModuleDetails) {
		this.enterpriseModuleDetails = enterpriseModuleDetails;
	}

	public EnterpriseModuleDetail addEnterpriseModuleDetail(EnterpriseModuleDetail enterpriseModuleDetail) {
		getEnterpriseModuleDetails().add(enterpriseModuleDetail);
		enterpriseModuleDetail.setEnterpriseModule(this);

		return enterpriseModuleDetail;
	}

	public EnterpriseModuleDetail removeEnterpriseModuleDetail(EnterpriseModuleDetail enterpriseModuleDetail) {
		getEnterpriseModuleDetails().remove(enterpriseModuleDetail);
		enterpriseModuleDetail.setEnterpriseModule(null);

		return enterpriseModuleDetail;
	}

}