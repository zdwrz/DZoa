package com.dz.oa.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the project_status_track database table.
 * 
 */
@Entity
@Table(name="project_status_track")
@NamedQuery(name="ProjectStatusTrack.findAll", query="SELECT p FROM ProjectStatusTrack p")
public class ProjectStatusTrack implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;

	@Column(name="inactive_ind")
	private String inactiveInd;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="status_date")
	private Date statusDate;

	//bi-directional many-to-one association to Project
	@ManyToOne
	private Project project;

	//bi-directional many-to-one association to ProjectLookup
	@ManyToOne
	@JoinColumn(name="status_id")
	private ProjectLookup projectLookup;

	public ProjectStatusTrack() {
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

	public Date getStatusDate() {
		return this.statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public ProjectLookup getProjectLookup() {
		return this.projectLookup;
	}

	public void setProjectLookup(ProjectLookup projectLookup) {
		this.projectLookup = projectLookup;
	}

}