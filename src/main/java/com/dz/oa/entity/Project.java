package com.dz.oa.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the project database table.
 * 
 */
@Entity
@NamedQuery(name="Project.findAll", query="SELECT p FROM Project p")
@Table(name = "project")
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="complete_date")
	private Date completeDate;

	@Column(name="inactive_ind")
	private String inactiveInd = "N";

	private String name;

	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_date")
	private Date startDate;

	//bi-directional many-to-one association to Enterprise
	@ManyToOne
	private Enterprise enterprise;

	//bi-directional many-to-one association to ProjectLookup
	@ManyToOne
	@JoinColumn(name="status")
	private ProjectLookup status;

	//bi-directional many-to-one association to ProjectLocation
	@OneToMany(mappedBy="project")
	private List<ProjectLocation> projectLocations;

	//bi-directional many-to-one association to ProjectStatusTrack
	@OneToMany(mappedBy="project")
	private List<ProjectStatusTrack> projectStatusTracks;

	//bi-directional many-to-one association to ProjectUserAssoc
	@OneToMany(mappedBy="project")
	private List<ProjectUserAssoc> projectUserAssocs;

	//bi-directional many-to-one association to TsBillCodeLookup
	@OneToMany(mappedBy="project")
	private List<TsBillCodeLookup> tsBillCodeLookups;

	public Project() {
	}

	public Project(int id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCompleteDate() {
		return this.completeDate;
	}

	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}

	public String getInactiveInd() {
		return this.inactiveInd;
	}

	public void setInactiveInd(String inactiveInd) {
		this.inactiveInd = inactiveInd;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Enterprise getEnterprise() {
		return this.enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	public ProjectLookup getStatus() {
		return this.status;
	}

	public void setStatus(ProjectLookup projectLookup) {
		this.status = projectLookup;
	}

	public List<ProjectLocation> getProjectLocations() {
		return this.projectLocations;
	}

	public void setProjectLocations(List<ProjectLocation> projectLocations) {
		this.projectLocations = projectLocations;
	}

	public ProjectLocation addProjectLocation(ProjectLocation projectLocation) {
		getProjectLocations().add(projectLocation);
		projectLocation.setProject(this);

		return projectLocation;
	}

	public ProjectLocation removeProjectLocation(ProjectLocation projectLocation) {
		getProjectLocations().remove(projectLocation);
		projectLocation.setProject(null);

		return projectLocation;
	}

	public List<ProjectStatusTrack> getProjectStatusTracks() {
		return this.projectStatusTracks;
	}

	public void setProjectStatusTracks(List<ProjectStatusTrack> projectStatusTracks) {
		this.projectStatusTracks = projectStatusTracks;
	}

	public ProjectStatusTrack addProjectStatusTrack(ProjectStatusTrack projectStatusTrack) {
		getProjectStatusTracks().add(projectStatusTrack);
		projectStatusTrack.setProject(this);

		return projectStatusTrack;
	}

	public ProjectStatusTrack removeProjectStatusTrack(ProjectStatusTrack projectStatusTrack) {
		getProjectStatusTracks().remove(projectStatusTrack);
		projectStatusTrack.setProject(null);

		return projectStatusTrack;
	}

	public List<ProjectUserAssoc> getProjectUserAssocs() {
		return this.projectUserAssocs;
	}

	public void setProjectUserAssocs(List<ProjectUserAssoc> projectUserAssocs) {
		this.projectUserAssocs = projectUserAssocs;
	}

	public ProjectUserAssoc addProjectUserAssoc(ProjectUserAssoc projectUserAssoc) {
		getProjectUserAssocs().add(projectUserAssoc);
		projectUserAssoc.setProject(this);

		return projectUserAssoc;
	}

	public ProjectUserAssoc removeProjectUserAssoc(ProjectUserAssoc projectUserAssoc) {
		getProjectUserAssocs().remove(projectUserAssoc);
		projectUserAssoc.setProject(null);

		return projectUserAssoc;
	}

	public List<TsBillCodeLookup> getTsBillCodeLookups() {
		return this.tsBillCodeLookups;
	}

	public void setTsBillCodeLookups(List<TsBillCodeLookup> tsBillCodeLookups) {
		this.tsBillCodeLookups = tsBillCodeLookups;
	}

	public TsBillCodeLookup addTsBillCodeLookup(TsBillCodeLookup tsBillCodeLookup) {
		getTsBillCodeLookups().add(tsBillCodeLookup);
		tsBillCodeLookup.setProject(this);

		return tsBillCodeLookup;
	}

	public TsBillCodeLookup removeTsBillCodeLookup(TsBillCodeLookup tsBillCodeLookup) {
		getTsBillCodeLookups().remove(tsBillCodeLookup);
		tsBillCodeLookup.setProject(null);

		return tsBillCodeLookup;
	}

}