package com.CareerHub.oops.dao;

import com.CareerHub.oops.entity.Applicant;
import com.CareerHub.oops.entity.JobApplication;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobApplicationDaoImpl implements JobApplicationDao {
    private Connection connection;

    // Constructor to initialize the connection
    public JobApplicationDaoImpl(Connection connection) {
        this.connection = connection;
    }

    // Insert a new job application
    @Override
    public void insertJobApplication(JobApplication jobApplication) {
        String query = "INSERT INTO applications (jobid, applicantid, applicationdate, coverletter) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            // Auto-increment fields like applicationid will be handled automatically by MySQL
            statement.setInt(1, jobApplication.getJobId());
            statement.setInt(2, jobApplication.getApplicantID());
            statement.setTimestamp(3, Timestamp.valueOf(jobApplication.getApplicationDate()));
            statement.setString(4, jobApplication.getCoverLetter());

            statement.executeUpdate();
            System.out.println("Job application for job ID " + jobApplication.getJobId() +
                    " by applicant ID " + jobApplication.getApplicantID() + " added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve all job applications
    @Override
    public List<JobApplication> getAllJobApplications() {
        List<JobApplication> jobApplications = new ArrayList<>();
        String query = "SELECT * FROM applications";
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                int applicationId = rs.getInt("applicationid");
                int jobId = rs.getInt("jobid");
                int applicantId = rs.getInt("applicantid");
                Timestamp applicationDate = rs.getTimestamp("applicationdate");
                String coverLetter = rs.getString("coverletter");

                JobApplication jobApplication = new JobApplication(applicationId, jobId, applicantId,
                        applicationDate.toLocalDateTime(), coverLetter);
                jobApplications.add(jobApplication);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobApplications;
    }

    // Retrieve a job application by its ID
    @Override
    public JobApplication getJobApplicationById(int applicationId) {
        JobApplication jobApplication = null;
        String query = "SELECT * FROM applications WHERE applicationid = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, applicationId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                int jobId = rs.getInt("jobid");
                int applicantId = rs.getInt("applicantid");
                Timestamp applicationDate = rs.getTimestamp("applicationdate");
                String coverLetter = rs.getString("coverletter");

                jobApplication = new JobApplication(applicationId, jobId, applicantId, applicationDate.toLocalDateTime(), coverLetter);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobApplication;
    }

    // Retrieve job applications for a specific job
    @Override
    public List<JobApplication> getJobApplicationsForJob(int jobId) {
        List<JobApplication> jobApplications = new ArrayList<>();
        String query = "SELECT * FROM applications WHERE jobid = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, jobId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int applicationId = rs.getInt("applicationid");
                int applicantId = rs.getInt("applicantid");
                Timestamp applicationDate = rs.getTimestamp("applicationdate");
                String coverLetter = rs.getString("coverletter");

                JobApplication jobApplication = new JobApplication(applicationId, jobId, applicantId, applicationDate.toLocalDateTime(), coverLetter);
                jobApplications.add(jobApplication);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobApplications;
    }

    // Retrieve job applications for a specific applicant
    @Override
    public List<JobApplication> getJobApplicationsForApplicant(int applicantId) {
        List<JobApplication> jobApplications = new ArrayList<>();
        String query = "SELECT * FROM applications WHERE applicantid = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, applicantId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int applicationId = rs.getInt("applicationid");
                int jobId = rs.getInt("jobid");
                Timestamp applicationDate = rs.getTimestamp("applicationdate");
                String coverLetter = rs.getString("coverletter");

                JobApplication jobApplication = new JobApplication(applicationId, jobId, applicantId, applicationDate.toLocalDateTime(), coverLetter);
                jobApplications.add(jobApplication);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobApplications;
    }

    // Create the applications table if it does not exist
    @Override
    public void createTable() {
        String query = "CREATE TABLE IF NOT EXISTS applications (" +
                "applicationid INT AUTO_INCREMENT PRIMARY KEY, " +
                "jobid INT, " +
                "applicantid INT, " +
                "applicationdate TIMESTAMP, " +
                "coverletter TEXT, " +
                "FOREIGN KEY (jobid) REFERENCES jobs(jobid), " +
                "FOREIGN KEY (applicantid) REFERENCES applicants(applicantid)" +
                ");";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Applications table created or already exists.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get all applications by job ID (JobApplications for a specific Job)
    @Override
    public List<JobApplication> getApplicationsByJobId(int jobId) {
        List<JobApplication> jobApplications = new ArrayList<>();
        String query = "SELECT * FROM applications WHERE jobid = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, jobId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int applicationId = rs.getInt("applicationid");
                int applicantId = rs.getInt("applicantid");
                Timestamp applicationDate = rs.getTimestamp("applicationdate");
                String coverLetter = rs.getString("coverletter");

                JobApplication jobApplication = new JobApplication(applicationId, jobId, applicantId, applicationDate.toLocalDateTime(), coverLetter);
                jobApplications.add(jobApplication);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobApplications;
    }

    
    public Applicant getApplicantByEmail(String email) {
        Applicant applicant = null;
        String query = "SELECT * FROM applicants WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                int applicantId = rs.getInt("applicantid");
                String name = rs.getString("name");
                String applicantEmail = rs.getString("email");
                applicant = new Applicant(applicantId, name, applicantEmail, applicantEmail, applicantEmail, applicantEmail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applicant;
    }

    // Overridden method from the interface (unimplemented, just for the interface definition)
    @Override
    public Applicant getApplicationsByJobId(String applicantEmailForJob) {
        // TODO: Implement this method if necessary
        return null;
    }
}
