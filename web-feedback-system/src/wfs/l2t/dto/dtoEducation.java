package wfs.l2t.dto;

import java.sql.Date;



public class dtoEducation {
	public Date startDate=null;
	public Date endDate=null;
	public String educationDescription ="";
	public int educationId=0;
	public String resumeId ="";
	public String schoolId ="";
	public String educationLevel ="";
	public String educationMajor="";
	public String educationLocation="";
	public String schoolName="";
	public Date getstartDate() {
		return startDate;
	}
	public void setstartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getendDate() {
		return endDate;
	}
	public void setendDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getEducationDescription() {
		return educationDescription;
	}
	public void setEducationDescription(String educationDescription) {
		this.educationDescription = educationDescription;
	}
	public int getEducationId() {
		return educationId;
	}
	public void setEducationId(int educationId) {
		this.educationId = educationId;
	}
	public String getResumeId() {
		return resumeId;
	}
	public void setResumeId(String resumeId) {
		this.resumeId = resumeId;
	}
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	public String getEducationLevel() {
		return educationLevel;
	}
	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
	}
	public String getEducationMajor() {
		return educationMajor;
	}
	public void setEducationMajor(String educationMajor) {
		this.educationMajor = educationMajor;
	}
	public String getEducationLocation() {
		return educationLocation;
	}
	public void setEducationLocation(String educationLocation) {
		this.educationLocation = educationLocation;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	
}
