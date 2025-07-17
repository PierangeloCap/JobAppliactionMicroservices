package com.PierCap.jobms.job.mapper;

import java.util.List;

import com.PierCap.jobms.job.Job;
import com.PierCap.jobms.job.dto.JobDTO;
import com.PierCap.jobms.job.external.Company;
import com.PierCap.jobms.job.external.Review;

public class JobMapper {

    public static JobDTO mapToJobWithCompanyDTO(Job job, Company company, List<Review> reviews){
        JobDTO jobWithCompanyDTO = new JobDTO();
        jobWithCompanyDTO.setId(job.getId());
        jobWithCompanyDTO.setTitle(job.getTitle());
        jobWithCompanyDTO.setMaxSalary(job.getMaxSalary());
        jobWithCompanyDTO.setMinSalary(job.getMinSalary());
        jobWithCompanyDTO.setLocation(job.getLocation());
        jobWithCompanyDTO.setCompany(company);
        jobWithCompanyDTO.setReviews(reviews);

        return jobWithCompanyDTO;
    }
    
}
