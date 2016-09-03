package com.dz.oa.entity;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the project_location database table.
 * 
 */
@Entity
@Table(name="project_location")
@NamedQuery(name="ProjectLocation.findAll", query="SELECT p FROM ProjectLocation p")
public class ProjectLocation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;

	private String address1;

	private String address2;

	private String city;

	private String state;

	private String country;

	private String zip;
	@Column(name="custom_address")
	private String customAddress;

	private String lat;

	private String lng;
	//bi-directional many-to-one association to Project
	@ManyToOne
	private Project project;
	public ProjectLocation(){}
	public ProjectLocation(String location, int id) {
		this.customAddress = location;
		this.project = new Project(id);
	}

	public String getCustomAddress() {
		return customAddress;
	}

	public void setCustomAddress(String customAddress) {
		this.customAddress = customAddress;
	}

	public ProjectLocation(String location) {
    	this.address1 = location;
    }

    public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}
}