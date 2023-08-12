package stan.inc.service.propertyImages;

import stan.inc.models.property.PropertyImages;

import java.util.List;

public interface PropertyImagesService {
List<PropertyImages> getAllPropImages(Long propertyId);
void saveAllPropImages(List<PropertyImages> imageList);
}
