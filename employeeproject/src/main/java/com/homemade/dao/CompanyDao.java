package com.homemade.dao;

import com.homemade.entity.Company;

import java.util.Collection;

public interface CompanyDao {

    Collection<Company> getCompanies();

    Company getCompany(int id);
}
