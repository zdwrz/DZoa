package com.dz.oa.entity;import javax.persistence.*;import java.io.Serializable;import java.util.Date;import java.util.List;/** * The persistent class for the ts_slot_lookup database table. *  */@Entity@Table(name="ts_slot_lookup")@NamedQuery(name="TsSlotLookup.findAll", query="SELECT t FROM TsSlotLookup t")public class TsSlotLookup implements Serializable {	private static final long serialVersionUID = 1L;	@Id	@GeneratedValue(strategy= GenerationType.IDENTITY)	private int id;	@Temporal(TemporalType.TIMESTAMP)	private Date date;	@Column(name="is_week_day")	private String isWeekDay;	//bi-directional many-to-one association to TsMain	@OneToMany(mappedBy="tsSlotLookup")	private List<TsMain> tsMains;	//bi-directional many-to-one association to TsPeriodUmLookup	@ManyToOne	@JoinColumn(name="period_id")	private TsPeriodUmLookup tsPeriodUmLookup;	public TsSlotLookup() {	}	public int getId() {		return this.id;	}	public void setId(int id) {		this.id = id;	}	public Date getDate() {		return this.date;	}	public void setDate(Date date) {		this.date = date;	}	public String getIsWeekDay() {		return this.isWeekDay;	}	public void setIsWeekDay(String isWeekDay) {		this.isWeekDay = isWeekDay;	}	public List<TsMain> getTsMains() {		return this.tsMains;	}	public void setTsMains(List<TsMain> tsMains) {		this.tsMains = tsMains;	}	public TsMain addTsMain(TsMain tsMain) {		getTsMains().add(tsMain);		tsMain.setTsSlotLookup(this);		return tsMain;	}	public TsMain removeTsMain(TsMain tsMain) {		getTsMains().remove(tsMain);		tsMain.setTsSlotLookup(null);		return tsMain;	}	public TsPeriodUmLookup getTsPeriodUmLookup() {		return this.tsPeriodUmLookup;	}	public void setTsPeriodUmLookup(TsPeriodUmLookup tsPeriodUmLookup) {		this.tsPeriodUmLookup = tsPeriodUmLookup;	}}