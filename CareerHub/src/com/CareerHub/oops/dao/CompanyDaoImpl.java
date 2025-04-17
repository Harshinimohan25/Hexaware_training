package com.CareerHub.oops.dao;

import com.CareerHub.oops.entity.Company;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyDaoImpl implements CompanyDao {
    private Connection connection;

    // Constructor to initialize the connection
    public CompanyDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insertCompany(Company company) {
        String query = "INSERT INTO companies (companyid, companyname, location) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, company.getCompanyId());
            statement.setString(2, company.getCompanyName());
            statement.setString(3, company.getLocation());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Company " + company.getCompanyName() + " added successfully.");
            } else {
                System.out.println("No rows affected, insertion might have failed.");
            }
        } catch (SQLException e) {
            System.out.println("Error while inserting company: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Retrieve all companies from the companies table
    @Override
    public List<Company> getAllCompanies() {
        List<Company> companies = new ArrayList<>();
        String query = "SELECT * FROM companies";
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                int companyId = rs.getInt("companyid");
                String companyName = rs.getString("companyname");
                String location = rs.getString("location");

                Company company = new Company(companyId, companyName, location);
                companies.add(company);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companies;
    }

    // Retrieve a company by its ID
    @Override
    public Company getCompanyById(int companyId) {
        Company company = null;
        String query = "SELECT * FROM companies WHERE companyid = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, companyId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                String companyName = rs.getString("companyname");
                String location = rs.getString("location");

                company = new Company(companyId, companyName, location);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return company;
    }

    // Create the companies table if it does not exist
    @Override
    public void createTable() {
        String query = "CREATE TABLE IF NOT EXISTS companies (" +
                "companyid INT PRIMARY KEY, " +
                "companyname VARCHAR(255) NOT NULL, " +
                "location VARCHAR(255) NOT NULL" +
                ");";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Companies table created or already exists.");
        } catch (SQLException e) {
            System.out.println("Error while creating the companies table: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
