package com.CareerHub.main;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import com.CareerHub.oops.entity.Applicant;
import com.CareerHub.oops.entity.Company;
import com.CareerHub.oops.entity.JobApplication;
import com.CareerHub.oops.entity.JobListing;
import com.CareerHub.oops.util.DatabaseManager;

public class CareerHubMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DatabaseManager dbManager = new DatabaseManager();

        try {
            dbManager.initializeDatabase();

            while (true) {
                System.out.println("\n--- Job Board Application ---");
                System.out.println("1. Add Job Listing");
                System.out.println("2. View Job Listings");
                System.out.println("3. Add Applicant");
                System.out.println("4. Apply for Job");
                System.out.println("5. View Applications");
                System.out.println("6. Exit");

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:System.out.print("Enter Company ID: ");
                    int companyId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    System.out.print("Enter Job Title: ");
                    String jobTitle = scanner.nextLine();

                    System.out.print("Enter Job Description: ");
                    String jobDescription = scanner.nextLine();

                    System.out.print("Enter Company Name: ");
                    String companyName = scanner.nextLine();

                    System.out.print("Enter Job Location: ");
                    String jobLocation = scanner.nextLine();

                    System.out.print("Enter Salary: ");
                    double salary = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline

                    System.out.print("Enter Job Type (e.g., Full-time, Part-time, Contract): ");
                    String jobType = scanner.nextLine();

                    Company company = new Company(companyId, companyName, jobLocation);
                    dbManager.insertCompany(company); 
                    LocalDateTime postedDate = LocalDateTime.now();
                    JobListing jobListing = new JobListing(
                        0, 
                        companyId,
                        jobTitle,
                        jobDescription,
                        jobLocation,
                        salary,
                        jobType,
                        postedDate
                    );

                    dbManager.insertJobListing(jobListing);

                    System.out.println("Job listing added successfully.");
                    break;


                    case 2:
                        List<JobListing> jobListings = dbManager.getJobListings();
                        System.out.println("\n--- Available Job Listings ---");
                        for (JobListing job : jobListings) {
                            System.out.println(job);
                        }
                        break;

                    case 3:
                        // Adding customer ID input for Applicant
                        System.out.print("Enter Customer ID (Company ID): ");
                        int applicantCustomerId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        
                        System.out.print("Enter First Name: ");
                        String firstName = scanner.nextLine();
                        System.out.print("Enter Last Name: ");
                        String lastName = scanner.nextLine();
                        System.out.print("Enter Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Enter Phone Number: ");
                        String phone = scanner.nextLine();
                        System.out.print("Enter Resume Details (or path): ");
                        String resume = scanner.nextLine();

                        Applicant applicant = new Applicant(0, firstName, lastName, email, phone, resume);
                        dbManager.insertApplicant(applicant);
                        System.out.println("Applicant added successfully.");
                        break;

                    case 4:
                        System.out.print("Enter Applicant Email: ");
                        String applicantEmailForJob = scanner.nextLine();
                        System.out.print("Enter Job Listing ID: ");
                        int jobListingId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        Applicant applicantForJob = dbManager.getApplicantByEmail(applicantEmailForJob);
                        JobListing jobForApplication = dbManager.getJobListingById(jobListingId);

                        if (applicantForJob != null && jobForApplication != null) {
                            JobApplication jobApplication = new JobApplication(applicantForJob, jobForApplication);
                            dbManager.insertJobApplication(jobApplication);
                            System.out.println("Application submitted successfully.");
                        } else {
                            System.out.println("Invalid applicant or job listing ID.");
                        }
                        break;

                    case 5:
                        List<JobApplication> jobApplications = dbManager.getJobApplications();
                        System.out.println("\n--- Job Applications ---");
                        for (JobApplication app : jobApplications) {
                            System.out.println(app);
                        }
                        break;

                    case 6:
                        System.out.println("Exiting the application...");
                        return;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }

        } finally {
            scanner.close();
        }
    }
}
