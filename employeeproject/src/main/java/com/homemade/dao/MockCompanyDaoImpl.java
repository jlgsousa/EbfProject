package com.homemade.dao;

import com.homemade.entity.Company;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Repository
@Qualifier("mockCompanyImpl")
public class MockCompanyDaoImpl implements CompanyDao{
    private static Set<Company> companies;

    private static Company createCompany(int companyId, String companyName) {

        Company company = new Company();
        company.setCompanyId(companyId);
        company.setName(companyName);

        return company;
    }

    static {
        companies = new HashSet<Company>() {
            {
                add(createCompany(1, "Apple"));
                add(createCompany(2, "Google"));
                add(createCompany(3, "Microsoft"));
                add(createCompany(4, "EBF"));
            }
        };
    }

    @Override
    public Collection<Company> getCompanies() {
        return companies;
    }

    @Override
    public Company getCompany(int id) {
        return companies.stream()
                .filter(c -> c.getCompanyId() == id)
                .findFirst()
                .orElse(null);
    }
}
