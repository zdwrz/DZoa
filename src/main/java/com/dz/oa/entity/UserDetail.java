package com.dz.oa.entity;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the user_detail database table.
 * 
 */
@Entity
@Table(name="user_detail")
@NamedQuery(name="UserDetail.findAll", query="SELECT u FROM UserDetail u")
public class UserDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;

	private String email;

	@Column(name="first_name")
	private String firstName;

	@Column(name="home_address")
	private String homeAddress;

	@Column(name="inactive_ind")
	private String inactiveInd;

	@Column(name="last_name")
	private String lastName;

	@Column(name="mid_init")
	private String midInit;

	private String phone;

	//bi-directional many-to-one association to User
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	public UserDetail() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getHomeAddress() {
		return this.homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getInactiveInd() {
		return this.inactiveInd;
	}

	public void setInactiveInd(String inactiveInd) {
		this.inactiveInd = inactiveInd;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMidInit() {
		return this.midInit;
	}

	public void setMidInit(String midInit) {
		this.midInit = midInit;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}