package com.dz.oa.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the ts_bill_code_lookup database table.
 * 
 */
@Entity
@Table(name="ts_bill_code_lookup")
@NamedQuery(name="TsBillCodeLookup.findAll", query="SELECT t FROM TsBillCodeLookup t")
public class TsBillCodeLookup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;

	@Column(name="code_desc")
	private String codeDesc;

	@Column(name="code_tyoe")
	private String codeTyoe;

	@Column(name="code_value")
	private String codeValue;

	@Column(name="inactive_ind")
	private String inactiveInd;

	//bi-directional many-to-one association to Project
	@ManyToOne
	private Project project;

	//bi-directional many-to-one association to TsEmpSchedule
	@OneToMany(mappedBy="tsBillCodeLookup")
	private List<TsEmpSchedule> tsEmpSchedules;

	//bi-directional many-to-one association to TsMain
	@OneToMany(mappedBy="tsBillCodeLookup")
	private List<TsMain> tsMains;

	public TsBillCodeLookup() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodeDesc() {
		return this.codeDesc;
	}

	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}

	public String getCodeTyoe() {
		return this.codeTyoe;
	}

	public void setCodeTyoe(String codeTyoe) {
		this.codeTyoe = codeTyoe;
	}

	public String getCodeValue() {
		return this.codeValue;
	}

	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}

	public String getInactiveInd() {
		return this.inactiveInd;
	}

	public void setInactiveInd(String inactiveInd) {
		this.inactiveInd = inactiveInd;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<TsEmpSchedule> getTsEmpSchedules() {
		return this.tsEmpSchedules;
	}

	public void setTsEmpSchedules(List<TsEmpSchedule> tsEmpSchedules) {
		this.tsEmpSchedules = tsEmpSchedules;
	}

	public TsEmpSchedule addTsEmpSchedule(TsEmpSchedule tsEmpSchedule) {
		getTsEmpSchedules().add(tsEmpSchedule);
		tsEmpSchedule.setTsBillCodeLookup(this);

		return tsEmpSchedule;
	}

	public TsEmpSchedule removeTsEmpSchedule(TsEmpSchedule tsEmpSchedule) {
		getTsEmpSchedules().remove(tsEmpSchedule);
		tsEmpSchedule.setTsBillCodeLookup(null);

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
		tsMain.setTsBillCodeLookup(this);

		return tsMain;
	}

	public TsMain removeTsMain(TsMain tsMain) {
		getTsMains().remove(tsMain);
		tsMain.setTsBillCodeLookup(null);

		return tsMain;
	}

}