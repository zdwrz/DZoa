package com.dz.oa.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ts_period_um_lookup database table.
 * 
 */
@Entity
@Table(name="ts_period_um_lookup")
@NamedQuery(name="TsPeriodUmLookup.findAll", query="SELECT t FROM TsPeriodUmLookup t")
public class TsPeriodUmLookup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_date")
	private Date endDate;

	@Column(name="inactive_ind")
	private String inactiveInd;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_date")
	private Date startDate;

	//bi-directional many-to-one association to TsSlotLookup
	@OneToMany(mappedBy="tsPeriodUmLookup")
	private List<TsSlotLookup> tsSlotLookups;

	public TsPeriodUmLookup() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getInactiveInd() {
		return this.inactiveInd;
	}

	public void setInactiveInd(String inactiveInd) {
		this.inactiveInd = inactiveInd;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public List<TsSlotLookup> getTsSlotLookups() {
		return this.tsSlotLookups;
	}

	public void setTsSlotLookups(List<TsSlotLookup> tsSlotLookups) {
		this.tsSlotLookups = tsSlotLookups;
	}

	public TsSlotLookup addTsSlotLookup(TsSlotLookup tsSlotLookup) {
		getTsSlotLookups().add(tsSlotLookup);
		tsSlotLookup.setTsPeriodUmLookup(this);

		return tsSlotLookup;
	}

	public TsSlotLookup removeTsSlotLookup(TsSlotLookup tsSlotLookup) {
		getTsSlotLookups().remove(tsSlotLookup);
		tsSlotLookup.setTsPeriodUmLookup(null);

		return tsSlotLookup;
	}

}