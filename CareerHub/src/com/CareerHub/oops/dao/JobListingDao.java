package com.CareerHub.oops.dao;

	import com.CareerHub.oops.entity.JobListing;
	import java.util.List;

	public interface JobListingDao {
	    void insertJob(JobListing jobListing);
	    JobListing getJobById(int jobId);
	    List<JobListing> getAllJobs();
	    List<JobListing> getJobsByCompany(int companyId);
		
	}



