package com.dz.oa.entity;import javax.persistence.*;import java.io.Serializable;/** * The persistent class for the enterprise_module_detail database table. *  */@Entity@Table(name="enterprise_module_detail")@NamedQuery(name="EnterpriseModuleDetail.findAll", query="SELECT e FROM EnterpriseModuleDetail e")public class EnterpriseModuleDetail implements Serializable {	private static final long serialVersionUID = 1L;	@Id	@GeneratedValue(strategy= GenerationType.IDENTITY)	private int id;	@Column(name="detail_value")	private String detailValue;	@Column(name="inactive_ind")	private String inactiveInd;	//bi-directional many-to-one association to EnterpriseModule	@ManyToOne	@JoinColumn(name="em_id")	private EnterpriseModule enterpriseModule;	public EnterpriseModuleDetail() {	}	public int getId() {		return this.id;	}	public void setId(int id) {		this.id = id;	}	public String getDetailValue() {		return this.detailValue;	}	public void setDetailValue(String detailValue) {		this.detailValue = detailValue;	}	public String getInactiveInd() {		return this.inactiveInd;	}	public void setInactiveInd(String inactiveInd) {		this.inactiveInd = inactiveInd;	}	public EnterpriseModule getEnterpriseModule() {		return this.enterpriseModule;	}	public void setEnterpriseModule(EnterpriseModule enterpriseModule) {		this.enterpriseModule = enterpriseModule;	}}