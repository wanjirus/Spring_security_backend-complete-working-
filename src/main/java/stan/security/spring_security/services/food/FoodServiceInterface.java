package com.sunflash.sunappointment.service.job;

import com.sunflash.sunappointment.model.JobDTO;
import com.sunflash.sunappointment.model.StaffDTO;

import java.util.List;
/**
 * Author::Stanley
 * Since
 * Version 1.0.0
 */
public interface JobServiceInterface {
    public JobDTO findJobById(long jobId);

    public JobDTO createNewJob(long staffId,  JobDTO jobDTO);

    public List<JobDTO> findJobByUserId(long userId);




}
