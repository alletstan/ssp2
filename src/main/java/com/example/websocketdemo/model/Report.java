package com.example.websocketdemo.model;

public class Report {

	private int structuredReportId;

	private String incidentDateTime;

	private String name;

	private String positionInEf;

	private String incidentLocation;

	private String incidentDescription;

	private String assetsActivated;

	private String timeToFullDeploy;

	private String timelyUpdate;

	private String incidentEndDateTime;

	private String casualtyAssessment;

	private String damageAssesment;

	private boolean isAcknowledged = false;

	public Report() {
	};

	public Report(int structuredReportId, String incidentDateTime, String incidentLocation, String incidentDescription,
			String assetsActivated, String timeToFullDeploy, String timelyUpdate, String incidentEndDateTime,
			String casualtyAssessment, String damageAssesment) {
		this.structuredReportId = structuredReportId;
		this.incidentDateTime = incidentDateTime;
		this.incidentLocation = incidentLocation;
		this.incidentDescription = incidentDescription;
		this.assetsActivated = assetsActivated;
		this.timeToFullDeploy = timeToFullDeploy;
		this.timelyUpdate = timelyUpdate;
		this.incidentEndDateTime = incidentEndDateTime;
		this.casualtyAssessment = casualtyAssessment;
		this.damageAssesment = damageAssesment;
	};

	public int getStructuredReportId() {
		return structuredReportId;
	}

	public String getIncidentDateTime() {
		return incidentDateTime;
	}

	public String getIncidentLocation() {
		return incidentLocation;
	}

	public String getIncidentDescription() {
		return incidentDescription;
	}

	public String getAssetsActivated() {
		return assetsActivated;
	}

	public String getTimeToFullDeploy() {
		return timeToFullDeploy;
	}

	public String getTimelyUpdate() {
		return timelyUpdate;
	}

	public String getEndIncidentDateTime() {
		return incidentEndDateTime;
	}

	public String getCasualtyAssessment() {
		return casualtyAssessment;
	}

	public String getDamageAssesment() {
		return damageAssesment;
	}

	public boolean isAcknowledged() {
		return isAcknowledged;
	}

	public void setStructuredReportId(int structuredReportId) {
		this.structuredReportId = structuredReportId;
	}

	public void setIncidentDateTime(String string) {
		this.incidentDateTime = string;
	}

	public void setIncidentLocation(String incidentLocation) {
		this.incidentLocation = incidentLocation;
	}

	public void setIncidentDescription(String incidentDescription) {
		this.incidentDescription = incidentDescription;
	}

	public void setAssetsActivated(String assetsActivated) {
		this.assetsActivated = assetsActivated;
	}

	public void setTimeToFullDeploy(String timeToFullDeploy) {
		this.timeToFullDeploy = timeToFullDeploy;
	}

	public void setTimelyUpdate(String timelyUpdate) {
		this.timelyUpdate = timelyUpdate;
	}

	public void setEndIncidentDateTime(String incidentEndDateTime) {
		this.incidentEndDateTime = incidentEndDateTime;
	}

	public void setCasualtyAssessment(String casualtyAssessment) {
		this.casualtyAssessment = casualtyAssessment;
	}

	public void setDamageAssesment(String damageAssesment) {
		this.damageAssesment = damageAssesment;
	}

	public void setAcknowledged() {
		this.isAcknowledged = true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPositionInEf() {
		return positionInEf;
	}

	public void setPositionInEf(String positionInEf) {
		this.positionInEf = positionInEf;
	}

	@Override
	public String toString() {
		return "Report [structuredReportId=" + structuredReportId + ", incidentDateTime=" + incidentDateTime
				+ ", incidentLocation=" + incidentLocation + ", incidentDescription=" + incidentDescription
				+ ", assetsActivated=" + assetsActivated + ", timeToFullDeploy=" + timeToFullDeploy + ", timelyUpdate="
				+ timelyUpdate + ", incidentEndDateTime=" + incidentEndDateTime + ", casualtyAssessment="
				+ casualtyAssessment + ", damageAssesment=" + damageAssesment + "]";
	}

}
