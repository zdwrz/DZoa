package com.dz.oa.entity;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the ts_emp_schedule database table.
 * 
 */
@Entity
@Table(name="ts_emp_schedule")
@NamedQuery(name="TsEmpSchedule.findAll", query="SELECT t FROM TsEmpSchedule t")
public class TsEmpSchedule implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;

	@Column(name="max_hour")
	private int maxHour;

	private int period;

	//bi-directional many-to-one association to TsBillCodeLookup
	@ManyToOne
	@JoinColumn(name="bill_code_id")
	private TsBillCodeLookup tsBillCodeLookup;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	public TsEmpSchedule() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMaxHour() {
		return this.maxHour;
	}

	public void setMaxHour(int maxHour) {
		this.maxHour = maxHour;
	}

	public int getPeriod() {
		return this.period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public TsBillCodeLookup getTsBillCodeLookup() {
		return this.tsBillCodeLookup;
	}

	public void setTsBillCodeLookup(TsBillCodeLookup tsBillCodeLookup) {
		this.tsBillCodeLookup = tsBillCodeLookup;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}