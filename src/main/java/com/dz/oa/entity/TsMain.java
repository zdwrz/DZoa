package com.dz.oa.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the ts_main database table.
 * 
 */
@Entity
@Table(name="ts_main")
@NamedQuery(name="TsMain.findAll", query="SELECT t FROM TsMain t")
public class TsMain implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;

	@Column(name="inactive_ind")
	private String inactiveInd;

	private int value;

	//bi-directional many-to-one association to TsApproval
	@OneToMany(mappedBy="tsMain")
	private List<TsApproval> tsApprovals;

	//bi-directional many-to-one association to TsBillCodeLookup
	@ManyToOne
	@JoinColumn(name="bill_code_id")
	private TsBillCodeLookup tsBillCodeLookup;

	//bi-directional many-to-one association to TsSlotLookup
	@ManyToOne
	@JoinColumn(name="slot_id")
	private TsSlotLookup tsSlotLookup;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	public TsMain() {
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

	public int getValue() {
		return this.value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public List<TsApproval> getTsApprovals() {
		return this.tsApprovals;
	}

	public void setTsApprovals(List<TsApproval> tsApprovals) {
		this.tsApprovals = tsApprovals;
	}

	public TsApproval addTsApproval(TsApproval tsApproval) {
		getTsApprovals().add(tsApproval);
		tsApproval.setTsMain(this);

		return tsApproval;
	}

	public TsApproval removeTsApproval(TsApproval tsApproval) {
		getTsApprovals().remove(tsApproval);
		tsApproval.setTsMain(null);

		return tsApproval;
	}

	public TsBillCodeLookup getTsBillCodeLookup() {
		return this.tsBillCodeLookup;
	}

	public void setTsBillCodeLookup(TsBillCodeLookup tsBillCodeLookup) {
		this.tsBillCodeLookup = tsBillCodeLookup;
	}

	public TsSlotLookup getTsSlotLookup() {
		return this.tsSlotLookup;
	}

	public void setTsSlotLookup(TsSlotLookup tsSlotLookup) {
		this.tsSlotLookup = tsSlotLookup;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}