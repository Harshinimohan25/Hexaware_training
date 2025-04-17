package com.CareerHub.oops.dao;

import com.CareerHub.oops.entity.JobListing;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class JobListingDaoImpl implements JobListingDao {
    private Connection connection;

    public JobListingDaoImpl(Connection connection) {
        this.connection = connection;
    }
    @Override
    public void insertJob(JobListing jobListing) {
        String query = "INSERT INTO jobs (companyid, jobtitle, jobdescription, joblocation, salary, jobtype, posteddate) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, jobListing.getCompanyId());
            stmt.setString(2, jobListing.getJobTitle());
            stmt.setString(3, jobListing.getJobDescription());
            stmt.setString(4, jobListing.getJobLocation());
            stmt.setDouble(5, jobListing.getSalary());
            stmt.setString(6, jobListing.getJobType());
            stmt.setTimestamp(7, Timestamp.valueOf(jobListing.getPostedDate()));
            stmt.executeUpdate();
            System.out.println("Job listing added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get a job listing by its ID
    @Override
    public JobListing getJobById(int jobId) {
        JobListing jobListing = null;
        String query = "SELECT * FROM jobs WHERE jobid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, jobId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                jobListing = mapResultSetToJobListing(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobListing;
    }

    // Get all job listings
    @Override
    public List<JobListing> getAllJobs() {
        List<JobListing> jobs = new ArrayList<>();
        String query = "SELECT jobs.jobid, jobs.companyid, jobs.jobtitle, jobs.jobdescription, jobs.joblocation, " +
                       "jobs.salary, jobs.jobtype, jobs.posteddate, companies.companyname " +
                       "FROM jobs JOIN companies ON jobs.companyid = companies.companyid";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                jobs.add(mapResultSetToJobListing(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobs;
    }

    // Get all job listings by company ID
    @Override
    public List<JobListing> getJobsByCompany(int companyId) {
        List<JobListing> jobs = new ArrayList<>();
        String query = "SELECT * FROM jobs WHERE companyid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, companyId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                jobs.add(mapResultSetToJobListing(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobs;
    }


    private JobListing mapResultSetToJobListing(ResultSet rs) throws SQLException {
        int jobId = rs.getInt("jobid");
        int companyId = rs.getInt("companyid");
        String jobTitle = rs.getString("jobtitle");
        String jobDescription = rs.getString("jobdescription");
        String jobLocation = rs.getString("joblocation");
        double salary = rs.getDouble("salary");
        String jobType = rs.getString("jobtype");
        Timestamp postedDateTimestamp = rs.getTimestamp("posteddate");

        LocalDateTime postedDate = postedDateTimestamp != null ? postedDateTimestamp.toLocalDateTime() : null;

        return new JobListing(jobId, companyId, jobTitle, jobDescription, jobLocation, salary, jobType, postedDate);
    }
}
