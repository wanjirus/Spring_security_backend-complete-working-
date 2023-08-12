package stan.inc.service.property;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stan.inc.exceptions.ResourceNotFoundException;
import stan.inc.models.property.Type;
import stan.inc.repository.TypeRepository;

@Service
@AllArgsConstructor
public class TypeService {
    private final TypeRepository typeRepository;
    @Transactional
    public Type findTypeById(long id) throws ResourceNotFoundException {
        return typeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("No Type record Found::"+id));
    }
}
