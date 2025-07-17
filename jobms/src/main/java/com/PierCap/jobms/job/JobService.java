package com.PierCap.jobms.job;

import java.util.List;

import com.PierCap.jobms.job.dto.JobDTO;

public interface JobService {
    
    List<JobDTO> findAll();

    void createJob(Job job);

    JobDTO findJobById(Long id);

    boolean deleteJobById(Long id);

    boolean updateJob(Long id, Job updatedJob);
}
