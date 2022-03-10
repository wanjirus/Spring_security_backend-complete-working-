package com.sunflash.sunappointment.mapper;

import com.sunflash.sunappointment.entities.job.Jobs;
import com.sunflash.sunappointment.model.JobDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",  uses = UserMapper.class)
public interface JobsMapper {
    JobDTO toJobDTO(Jobs jobs);

    List<JobDTO> toJobDtoList(List<Jobs> jobs);
}
