package com.sunflash.sunappointment.model;

import lombok.Data;

@Data
public class JobDTO {
    private Long id;
    private UserDTO users;
    private String jobCategory;
    private String jobName;
    private String jobCost;
    private String jobDuration;
    private String bufferDuration;
    private Boolean privateJob;
    private Boolean videoMeeting;
}
