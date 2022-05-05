package stan.security.spring_security.mapper;

import org.mapstruct.Mapper;
import stan.security.spring_security.DTO.PropertyDTO;
import stan.security.spring_security.models.Property;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PropertyMapper {
    PropertyDTO toPropertyDto(Property property);
    List<PropertyDTO> toPropertyDtoList(List<Property> properties);
}
