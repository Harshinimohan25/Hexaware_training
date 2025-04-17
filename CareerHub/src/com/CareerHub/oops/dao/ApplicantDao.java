package com.CareerHub.oops.dao;

import com.CareerHub.oops.entity.Applicant;
import java.util.List;
public interface ApplicantDao {
	
	    void insertApplicant(Applicant applicant);
	    List<Applicant> getAllApplicants();
	    Applicant getApplicantById(int applicantId);
	    Applicant getApplicantByEmail(String email);
		void createTable();
		
	}


