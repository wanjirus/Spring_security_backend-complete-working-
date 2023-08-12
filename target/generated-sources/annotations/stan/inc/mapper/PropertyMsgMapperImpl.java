package stan.inc.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import stan.inc.DTO.MsgsDTO;
import stan.inc.models.property.PropertyMessage;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-11T22:40:06+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 16.0.2 (Oracle Corporation)"
)
@Component
public class PropertyMsgMapperImpl implements PropertyMsgMapper {

    @Override
    public MsgsDTO toMsgDto(PropertyMessage propertyMessage) {
        if ( propertyMessage == null ) {
            return null;
        }

        MsgsDTO msgsDTO = new MsgsDTO();

        if ( propertyMessage.getId() != null ) {
            msgsDTO.setId( propertyMessage.getId() );
        }
        msgsDTO.setMessage( propertyMessage.getMessage() );
        msgsDTO.setSender( propertyMessage.getSender() );

        return msgsDTO;
    }

    @Override
    public List<MsgsDTO> toMsgDtoList(List<PropertyMessage> properties) {
        if ( properties == null ) {
            return null;
        }

        List<MsgsDTO> list = new ArrayList<MsgsDTO>( properties.size() );
        for ( PropertyMessage propertyMessage : properties ) {
            list.add( toMsgDto( propertyMessage ) );
        }

        return list;
    }
}
