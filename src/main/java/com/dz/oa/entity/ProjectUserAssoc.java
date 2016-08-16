package com.dz.oa.entity;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the project_user_assoc database table.
 * 
 */
@Entity
@Table(name="project_user_assoc")
@NamedQuery(name="ProjectUserAssoc.findAll", query="SELECT p FROM ProjectUserAssoc p")
public class ProjectUserAssoc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;

	@Column(name="inactive_ind")
	private String inactiveInd;

	@Column(name="position_id")
	private int positionId;

	//bi-directional one-to-one association to ProjectLookup
	@OneToOne
	@JoinColumn(name="id")
	private ProjectLookup projectLookup;

	//bi-directional many-to-one association to Project
	@ManyToOne
	private Project project;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	public ProjectUserAssoc() {
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

	public int getPositionId() {
		return this.positionId;
	}

	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}

	public ProjectLookup getProjectLookup() {
		return this.projectLookup;
	}

	public void setProjectLookup(ProjectLookup projectLookup) {
		this.projectLookup = projectLookup;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}