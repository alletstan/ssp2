package com.example.websocketdemo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_table")
public class Order {
	
	@Id
	private long crisisID;
	
	private String name;
	private String positionInCMO;
	private int threatLevel;
	private String crisisType;
	private String affectedArea;
	private String crisisDetails;
	private String courseofAction;
 
	public Order(){
        crisisID=0;
    }

	public Order(long crisisID, String name, String positionInCMO, int threatLevel, String crisisType,
			String affectedArea, String crisisDetails, String courseofAction) {
		this.crisisID = crisisID;
		this.name = name;
		this.positionInCMO = positionInCMO;
		this.threatLevel = threatLevel;
		this.crisisType = crisisType;
		this.affectedArea = affectedArea;
		this.crisisDetails = crisisDetails;
		this.setCourseofAction(courseofAction);
	}

	public long getCrisisID() {
		return crisisID;
	}

	public void setCrisisID(long crisisID) {
		this.crisisID = crisisID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPositionInCMO() {
		return positionInCMO;
	}

	public void setPositionInCMO(String positionInCMO) {
		this.positionInCMO = positionInCMO;
	}

	public int getThreatLevel() {
		return threatLevel;
	}

	public void setThreatLevel(int threatLevel) {
		this.threatLevel = threatLevel;
	}

	public String getCrisisType() {
		return crisisType;
	}

	public void setCrisisType(String crisisType) {
		this.crisisType = crisisType;
	}

	public String getAffectedArea() {
		return affectedArea;
	}

	public void setAffectedArea(String affectedArea) {
		this.affectedArea = affectedArea;
	}

	public String getCrisisDetails() {
		return crisisDetails;
	}

	public void setCrisisDetails(String crisisDetails) {
		this.crisisDetails = crisisDetails;
	}
	
	public String getCourseofAction() {
		return courseofAction;
	}

	public void setCourseofAction(String courseofAction) {
		this.courseofAction = courseofAction;
	}

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (crisisID ^ (crisisID >>> 32));
        return result;
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Order other = (Order) obj;
        if (crisisID != other.crisisID)
            return false;
        return true;
    }
 
    @Override
	public String toString() {
		return "Report [crisisID=" + crisisID + ", name=" + name + ", positionInCMO=" + positionInCMO
				+ ", threatLevel=" + threatLevel + ", crisisType=" + crisisType
				+ ", affectedArea=" + affectedArea + ", crisisDetails=" + crisisDetails
				+ ", courseofAction=" + courseofAction + "]";
	}
 
 
}