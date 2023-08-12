package stan.inc.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import stan.inc.DTO.PropertyDTO;
import stan.inc.models.property.Category;
import stan.inc.models.property.Property;
import stan.inc.models.property.Type;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-11T22:40:06+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 16.0.2 (Oracle Corporation)"
)
@Component
public class PropMapperImpl implements PropMapper {

    @Override
    public PropertyDTO toPropertyDto(Property property) {
        if ( property == null ) {
            return null;
        }

        PropertyDTO propertyDTO = new PropertyDTO();

        propertyDTO.setId( property.getId() );
        propertyDTO.setName( property.getName() );
        propertyDTO.setLocation( property.getLocation() );
        propertyDTO.setStatus( property.getStatus() );
        propertyDTO.setPrice( property.getPrice() );
        propertyDTO.setDescription( property.getDescription() );
        propertyDTO.setContactInfo( property.getContactInfo() );
        propertyDTO.setDimensions( property.getDimensions() );
        propertyDTO.setCategories( categorySetToObjectSet( property.getCategories() ) );
        propertyDTO.setTypes( typeSetToObjectSet( property.getTypes() ) );

        return propertyDTO;
    }

    @Override
    public List<PropertyDTO> toPropertyDtoList(List<Property> properties) {
        if ( properties == null ) {
            return null;
        }

        List<PropertyDTO> list = new ArrayList<PropertyDTO>( properties.size() );
        for ( Property property : properties ) {
            list.add( toPropertyDto( property ) );
        }

        return list;
    }

    protected Set<Object> categorySetToObjectSet(Set<Category> set) {
        if ( set == null ) {
            return null;
        }

        Set<Object> set1 = new HashSet<Object>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Category category : set ) {
            set1.add( category );
        }

        return set1;
    }

    protected Set<Object> typeSetToObjectSet(Set<Type> set) {
        if ( set == null ) {
            return null;
        }

        Set<Object> set1 = new HashSet<Object>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Type type : set ) {
            set1.add( type );
        }

        return set1;
    }
}
