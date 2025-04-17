package com.CareerHub.oops.dao;
import com.CareerHub.oops.entity.Company;
import java.util.List;
public interface CompanyDao {
	
	    void insertCompany(Company company);
	    List<Company> getAllCompanies();
	    Company getCompanyById(int companyId);
		void createTable();
	
	}



