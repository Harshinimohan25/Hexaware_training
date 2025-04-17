package com.CareerHub.oops.entity;

import java.util.ArrayList;
import java.util.List;

public class Applicant {

    private int applicantId;   // Change to int for applicant ID
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String resume;

    private List<JobApplication> applications = new ArrayList<>(); // Assuming JobApplication class is properly set up

    public Applicant(int applicantId, String firstName, String lastName, String email, String phone, String resume) {
        this.applicantId = applicantId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.resume = resume;
    }


    // Method to create or update profile
    public void createProfile(String email, String firstName, String lastName, String phone) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        System.out.println("Profile created/updated successfully for " + firstName + " " + lastName);
    }

    // Method to apply for a job
    public void applyForJob(JobListing jobListing, String coverLetter) {
        jobListing.apply(this.applicantId, coverLetter); // Assuming 'apply' method exists in JobListing class
        System.out.println("Applicant " + this.applicantId + " applied for job: " + jobListing.getJobTitle());
    }

    // Getters and Setters
    public int getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(int applicantId) {
        this.applicantId = applicantId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    // toString for debugging
    @Override
    public String toString() {
        return "Applicant{" +
                "applicantId=" + applicantId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", resume='" + resume + '\'' +
                '}';
    }
}
