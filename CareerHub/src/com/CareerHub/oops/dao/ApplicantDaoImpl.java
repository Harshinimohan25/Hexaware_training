package com.CareerHub.oops.dao;

import com.CareerHub.oops.entity.Applicant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApplicantDaoImpl implements ApplicantDao {
    private Connection connection;

    public ApplicantDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public void insertApplicant(Applicant applicant) {
        String query = "INSERT INTO applicants (firstname, lastname, email, phone, resume) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, applicant.getFirstName());
            statement.setString(2, applicant.getLastName());
            statement.setString(3, applicant.getEmail());
            statement.setString(4, applicant.getPhone());
            statement.setString(5, applicant.getResume());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    System.out.println("Applicant " + applicant.getFirstName() + " " + applicant.getLastName() +
                            " added successfully with ID: " + generatedId);
                }
            } else {
                System.out.println("Failed to add applicant.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Applicant> getAllApplicants() {
        List<Applicant> applicants = new ArrayList<>();
        String query = "SELECT * FROM applicants";
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                int applicantId = rs.getInt("applicantid");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String resume = rs.getString("resume");

                Applicant applicant = new Applicant(applicantId, firstName, lastName, email, phone, resume);
                applicants.add(applicant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applicants;
    }

    // Retrieve an applicant by their ID
    public Applicant getApplicantById(int applicantId) {
        Applicant applicant = null;
        String query = "SELECT * FROM applicants WHERE applicantid = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, applicantId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String resume = rs.getString("resume");

                applicant = new Applicant(applicantId, firstName, lastName, email, phone, resume);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applicant;
    }

    // Retrieve an applicant by their email
    public Applicant getApplicantByEmail(String email) {
        Applicant applicant = null;
        String query = "SELECT * FROM applicants WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                int applicantId = rs.getInt("applicantid");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String phone = rs.getString("phone");
                String resume = rs.getString("resume");

                applicant = new Applicant(applicantId, firstName, lastName, email, phone, resume);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applicant;
    }

    // Create the applicants table in the database if it doesn't already exist
    @Override
    public void createTable() {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS applicants (" +
                                  "applicantid INT PRIMARY KEY AUTO_INCREMENT, " +
                                  "firstname VARCHAR(100), " +
                                  "lastname VARCHAR(100), " +
                                  "email VARCHAR(100), " +
                                  "phone VARCHAR(15), " +
                                  "resume TEXT)";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(createTableQuery);
            System.out.println("Applicants table created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
