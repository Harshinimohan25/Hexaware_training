package com.CareerHub.oops.util;

import com.CareerHub.oops.dao.*;
import com.CareerHub.oops.entity.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DatabaseManager {

    private CompanyDao companyDAO;
    private JobListingDao jobListingDAO;
    private ApplicantDao applicantDAO;
    private JobApplicationDao jobApplicationDAO;
    private Connection connection;

    public DatabaseManager() {
        this.connection = DatabaseConnection.getConnection(); 
		if (connection != null) {
		            try {
		                connection.setAutoCommit(true); 
		            } catch (SQLException e) {
		                System.err.println("Error setting auto-commit: " + e.getMessage());
		            }
		    this.companyDAO = new CompanyDaoImpl(connection);
		    this.jobListingDAO = new JobListingDaoImpl(connection);
		    this.applicantDAO = new ApplicantDaoImpl(connection);
		    this.jobApplicationDAO = new JobApplicationDaoImpl(connection);
		} else {
		    System.out.println("Database connection is null.");
		}
    }

    public void initializeDatabase() {
        if (companyDAO != null) companyDAO.createTable();
//        if (jobListingDAO != null) jobListingDAO.createTable();
        if (applicantDAO != null) applicantDAO.createTable();
        if (jobApplicationDAO != null) jobApplicationDAO.createTable();
        System.out.println("Database schema initialized successfully.");
    }

    public void insertJobListing(JobListing job) {
        jobListingDAO.insertJob(job);
    }

    public void insertCompany(Company company) {
        companyDAO.insertCompany(company);
    }

    public void insertApplicant(Applicant applicant) {
        applicantDAO.insertApplicant(applicant);
    }

    public void insertJobApplication(JobApplication application) {
        jobApplicationDAO.insertJobApplication(application);
    }

    public List<JobListing> getJobListings() {
        return jobListingDAO.getAllJobs();
    }

    public List<Company> getCompanies() {
        return companyDAO.getAllCompanies();
    }

    public List<Applicant> getApplicants() {
        return applicantDAO.getAllApplicants();
    }

    public Applicant getApplicantByEmail(String email) {
        return applicantDAO.getApplicantByEmail(email);
    }

    public List<JobApplication> getJobApplications() {
        return jobApplicationDAO.getAllJobApplications();
    }

    public JobListing getJobListingById(int jobListingId) {
        return jobListingDAO.getJobById(jobListingId);
    }
}
