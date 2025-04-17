package com.CareerHub.oops.entity;
import java.time.LocalDateTime;

public class JobApplication {


	
	    private int applicationId;
	    private int jobId;
	    private int applicantId;
	    private LocalDateTime applicationDate;
	    private String coverLetter;
		private JobListing jobListing;

	    // Constructor
	    public JobApplication(int applicationId, int jobId, int applicantId, LocalDateTime applicationDate, String coverLetter) {
	        this.applicationId = applicationId;
	        this.jobId = jobId;
	        this.applicantId = applicantId;
	        this.applicationDate = applicationDate;
	        this.coverLetter = coverLetter;
	    }

	    

	    public JobApplication(Applicant applicantForJob, JobListing jobForApplication) {
	        this.applicantId = applicantId;
	        this.jobListing = jobForApplication;
	        this.applicationDate = LocalDateTime.now(); // Automatically assign current time
	    }

		// Getters and Setters
	    public int getApplicationId() {
	        return applicationId;
	    }

	    public void setApplicationId(int applicationId) {
	        this.applicationId = applicationId;
	    }

	    public int getJobId() {
	        return jobId;
	    }

	    public void setJobId(int jobId) {
	        this.jobId = jobId;
	    }

	    public int getApplicantID() {
	        return applicantId;
	    }

	    public void setApplicantID(int applicantId) {
	        this.applicantId = applicantId;
	    }

	    public LocalDateTime getApplicationDate() {
	        return applicationDate;
	    }

	    public void setApplicationDate(LocalDateTime applicationDate) {
	        this.applicationDate = applicationDate;
	    }

	    public String getCoverLetter() {
	        return coverLetter;
	    }

	    public void setCoverLetter(String coverLetter) {
	        this.coverLetter = coverLetter;
	    }

	    // toString for debugging
	    @Override
	    public String toString() {
	        return "JobApplication{" +
	                "applicationId=" + applicationId +
	                ", jobId=" + jobId +
	                ", applicantId=" + applicantId +
	                ", applicationDate=" + applicationDate +
	                ", coverLetter='" + coverLetter + '\'' +
	                '}';
	    }
	}



