package com.CareerHub.oops.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Company {

    private int companyId;
    private String companyName;
    private String location;

    private List<JobListing> jobListings = new ArrayList<>();

    // Constructor
    public Company(int companyId, String companyName, String location) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.location = location;
    }

    // Method to post a job
    public void postJob(String jobTitle, String jobDescription, String jobLocation, double salary, String jobType) {
        int jobId = generateJobId(); 
        LocalDateTime postedDate = LocalDateTime.now();

        JobListing job = new JobListing(
            jobId,
            companyId,
            jobTitle,
            jobDescription,
            jobLocation,
            salary,
            jobType,
            postedDate
        );

        jobListings.add(job);
        System.out.println("Job '" + jobTitle + "' posted by " + companyName);
    }

    // Method to get all jobs posted by the company
    public List<JobListing> getJobs() {
        return jobListings;
    }

    // Private method to generate unique job IDs based on the size of the list
    private int generateJobId() {
        return jobListings.size() + 1; // Simple counter for job ID
    }

    // Getters and Setters
    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // toString for easy debugging
    @Override
    public String toString() {
        return "Company{" +
                "companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
