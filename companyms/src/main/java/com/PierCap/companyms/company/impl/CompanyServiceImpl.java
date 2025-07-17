package com.PierCap.companyms.company.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.PierCap.companyms.company.Company;
import com.PierCap.companyms.company.CompanyRepository;
import com.PierCap.companyms.company.CompanyService;


@Service
public class CompanyServiceImpl implements CompanyService{

    private CompanyRepository companyRepository;

    

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }



    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }



    @Override
    public boolean updateCompany(Company updatedCompany, Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        
            if (companyOptional.isPresent()){
                Company company = companyOptional.get();
                company.setName(updatedCompany.getName());
                company.setDescription(updatedCompany.getDescription());
                companyRepository.save(company);
                return true;
            }

            return false;
    }



    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }



    @Override
    public boolean deleteCompanyById(Long id) {
        try {
            companyRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }



    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }
    
}
