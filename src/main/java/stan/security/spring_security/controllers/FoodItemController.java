package com.sunflash.sunappointment.controller.v1;

import com.sunflash.sunappointment.entities.job.Jobs;
import com.sunflash.sunappointment.exceptions.ResourceNotFoundException;
import com.sunflash.sunappointment.mapper.JobsMapper;
import com.sunflash.sunappointment.model.JobDTO;
import com.sunflash.sunappointment.repositories.JobRepository;
import com.sunflash.sunappointment.service.job.JobServiceInterface;
import com.sunflash.sunappointment.service.job.JobsService;
import com.sunflash.sunappointment.service.staff.StaffServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/jobs")
public class JobController {
    private final JobServiceInterface jobServiceInterface;
    private final JobsService jobService;
    private final JobRepository jobRepository;
    private final JobsMapper jobsMapper;

    // Api to Create a job and job Details API
    @PostMapping("/{userId}")
    public ResponseEntity<Object> createJob(@PathVariable(value = "userId") Long userId, @Valid @RequestBody JobDTO jobDTO) throws
            ResourceNotFoundException {
        try {
            JobDTO returnedJobDTO =jobServiceInterface.createNewJob(userId,jobDTO);
            return  new ResponseEntity<>(returnedJobDTO,HttpStatus.CREATED);

        } catch (Exception e) {
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping
    public  ResponseEntity<Object> getJobByUserId(@RequestParam Long userId) {
        try {
            List<JobDTO> returnedJobDTO = jobServiceInterface.findJobByUserId(userId);
            return new ResponseEntity<>(returnedJobDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

        @GetMapping("/{userId}")
                public ResponseEntity<Object> getJobsById(@PathVariable(value = "userId") Long userId){
       try {
           JobDTO jobDTO = jobServiceInterface.findJobById(userId);
           return new ResponseEntity<>(jobDTO, HttpStatus.OK);
        }catch (Exception e){
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
}

