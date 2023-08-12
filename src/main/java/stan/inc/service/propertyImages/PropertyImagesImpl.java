package stan.inc.service.propertyImages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stan.inc.models.property.PropertyImages;
import stan.inc.repository.PropertyImageRepository;

import java.util.List;

@Service
public class PropertyImagesImpl implements PropertyImagesService {
    @Autowired
    PropertyImageRepository propertyImageRepository;
    @Override
    public List<PropertyImages> getAllPropImages(Long propertyId) {
        return propertyImageRepository.findAllByPropertyId(propertyId);
    }

    @Override
    public void saveAllPropImages(List<PropertyImages> imageList) {
        for (PropertyImages propertyImages: imageList)
            propertyImageRepository.save(propertyImages);

    }
}
