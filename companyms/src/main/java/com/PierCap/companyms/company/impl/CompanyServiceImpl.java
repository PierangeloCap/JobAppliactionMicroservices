package com.PierCap.companyms.company.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.PierCap.companyms.company.Company;
import com.PierCap.companyms.company.CompanyRepository;
import com.PierCap.companyms.company.CompanyService;
import com.PierCap.companyms.company.clients.ReviewClient;
import com.PierCap.companyms.company.dto.ReviewMessage;


@Service
public class CompanyServiceImpl implements CompanyService{

    private CompanyRepository companyRepository;
    private ReviewClient reviewClient;

    

    public CompanyServiceImpl(CompanyRepository companyRepository, ReviewClient reviewClient) {
        this.companyRepository = companyRepository;
        this.reviewClient=reviewClient;
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



    @Override
    public void updateCompanyRating(ReviewMessage reviewMessage) {
        Company company = companyRepository.findById(reviewMessage.getCompanyId()).orElseThrow();
        double averageRating = reviewClient.getAverageRatingFotCompany(reviewMessage.getCompanyId());
        company.setRating(averageRating);
        companyRepository.save(company);   
    }



    
}
