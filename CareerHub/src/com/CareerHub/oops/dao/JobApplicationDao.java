package com.CareerHub.oops.dao;

import com.CareerHub.oops.entity.Applicant;
import com.CareerHub.oops.entity.JobApplication;

import java.util.List;




	public interface JobApplicationDao {
	    void insertJobApplication(JobApplication jobApplication);
	    List<JobApplication> getAllJobApplications();
	    JobApplication getJobApplicationById(int applicationId);
	    List<JobApplication> getJobApplicationsForJob(int jobId);
	    List<JobApplication> getJobApplicationsForApplicant(int applicantId);
		void createTable();
		List<JobApplication> getApplicationsByJobId(int jobId);
		Applicant getApplicationsByJobId(String applicantEmailForJob);
	}



