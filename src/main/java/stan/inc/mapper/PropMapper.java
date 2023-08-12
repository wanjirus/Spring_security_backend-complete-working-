package stan.inc.mapper;

import org.mapstruct.Mapper;
import stan.inc.DTO.PropertyDTO;
import stan.inc.models.property.Property;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PropMapper {

    PropertyDTO toPropertyDto(Property property);

    List<PropertyDTO> toPropertyDtoList(List<Property> properties);
}
