package com.PierCap.jobms.job;

import java.util.List;

import com.PierCap.jobms.job.dto.JobWithCompanyDTO;

public interface JobService {
    
    List<JobWithCompanyDTO> findAll();

    void createJob(Job job);

    Job findJobById(Long id);

    boolean deleteJobById(Long id);

    boolean updateJob(Long id, Job updatedJob);
}
