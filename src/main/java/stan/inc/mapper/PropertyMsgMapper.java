package stan.inc.mapper;//package stan.security.spring_security.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import stan.inc.DTO.MsgsDTO;
import stan.inc.models.property.PropertyMessage;

import java.util.List;
@Component
@Mapper(componentModel = "spring")
public interface PropertyMsgMapper {
        MsgsDTO toMsgDto(PropertyMessage propertyMessage);
        List<MsgsDTO> toMsgDtoList(List<PropertyMessage> properties);
}
