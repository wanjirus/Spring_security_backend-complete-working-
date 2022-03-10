package com.sunflash.sunappointment.service.job;
/**
 * Author::Stanley
 * Since
 * Version 1.0.0
 */
import com.sunflash.sunappointment.entities.auth.Users;
import com.sunflash.sunappointment.entities.job.Jobs;
import com.sunflash.sunappointment.entities.staff.Staffs;
import com.sunflash.sunappointment.mapper.JobsMapper;
import com.sunflash.sunappointment.model.JobDTO;
import com.sunflash.sunappointment.repositories.JobRepository;
import com.sunflash.sunappointment.service.auth.UserService;
import com.sunflash.sunappointment.service.staff.StaffService;
import lombok.AllArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
@Service
@AllArgsConstructor
public class JobsService implements JobServiceInterface {
private  final JobRepository jobRepository;
private JobsMapper jobsMapper;
private final UserService userService;

    public JobDTO findJobById(long id) throws  ResourceNotFoundException{
    Jobs jobs = jobRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("No job record found with that id::" + id));
    JobDTO jobDTO = jobsMapper.toJobDTO(jobs);
    return jobDTO;
}

public  Jobs findInternalJobById(long id) throws  ResourceNotFoundException{
    Jobs jobs = jobRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("No Job record found with Id:: " + id));

return jobs;
}


public JobDTO createNewJob(long userId, JobDTO jobDTO) throws ResourceNotFoundException{
    Users user = userService.findUserById(userId);
    Jobs job = constructJobObject(jobDTO);
    job.setUsers(user);
    job = jobRepository.save(job);
    return  jobsMapper.toJobDTO(job);
}

@Override
    public  List<JobDTO>findJobByUserId(long userId){
    List<Jobs> jobsList = jobRepository.findByUsersId(userId);
    return jobsMapper.toJobDtoList(jobsList);
}

private Jobs constructJobObject(JobDTO jobDTO){
    Jobs job =Jobs.builder()
            .jobCategory(jobDTO.getJobCategory())
            .jobName(jobDTO.getJobName())
            .privateJob(jobDTO.getPrivateJob())
            .jobCost(jobDTO.getJobCost())
            .jobDuration(jobDTO.getJobDuration())
            .bufferDuration(jobDTO.getBufferDuration())
            .videoMeeting(jobDTO.getVideoMeeting())
            .build();
    return  job;

}

}