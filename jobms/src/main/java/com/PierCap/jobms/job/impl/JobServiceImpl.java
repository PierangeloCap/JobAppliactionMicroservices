package com.PierCap.jobms.job.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.PierCap.jobms.job.Job;
import com.PierCap.jobms.job.JobRepository;
import com.PierCap.jobms.job.JobService;
import com.PierCap.jobms.job.clients.CompanyClient;
import com.PierCap.jobms.job.clients.ReviewClient;
import com.PierCap.jobms.job.dto.JobDTO;
import com.PierCap.jobms.job.external.Company;
import com.PierCap.jobms.job.external.Review;
import com.PierCap.jobms.job.mapper.JobMapper;



@Service
public class JobServiceImpl implements JobService {

    //private List<Job> jobs = new ArrayList<>();
    JobRepository jobRepository;

    private CompanyClient companyClient;
    private ReviewClient reviewClient;

    @Autowired
    RestTemplate restTemplate;

    

    public JobServiceImpl(JobRepository jobRepository, CompanyClient companyClient, ReviewClient reviewClient) {
        this.jobRepository = jobRepository;
        this.companyClient=companyClient;
        this.reviewClient=reviewClient;
    }

    @Override
    public List<JobDTO> findAll() {
        List<Job> jobs = jobRepository.findAll();
        
        return jobs.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private JobDTO convertToDto(Job job){
        

        Company company = companyClient.getCompany(job.getCompanyId());

        List<Review> reviews = reviewClient.getReviews(job.getCompanyId());
        
        JobDTO jobWithCompanyDTO = JobMapper.mapToJobWithCompanyDTO(job, company, reviews);
        //jobWithCompanyDTO.setCompany(company);

        return jobWithCompanyDTO;
    }

    

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public JobDTO findJobById(Long id) {
       Job job = jobRepository.findById(id).orElse(null);
       return convertToDto(job);
    }

    @Override
    public boolean deleteJobById(Long id) {
        try{
            jobRepository.deleteById(id);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        
            if (jobOptional.isPresent()){
                Job job = jobOptional.get();
                job.setTitle(updatedJob.getTitle());
                job.setDescription(updatedJob.getDescription());
                job.setMinSalary(updatedJob.getMinSalary());
                job.setMaxSalary(updatedJob.getMaxSalary());
                job.setLocation(updatedJob.getLocation());
                jobRepository.save(job);
                return true;
            }

        return false;
    }
    
}
