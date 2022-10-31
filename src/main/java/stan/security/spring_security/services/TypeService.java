package stan.security.spring_security.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stan.security.spring_security.exceptions.ResourceNotFoundException;
import stan.security.spring_security.models.Type;
import stan.security.spring_security.models.User;
import stan.security.spring_security.repository.TypeRepository;
import stan.security.spring_security.repository.UserRepository;

@Service
@AllArgsConstructor
public class TypeService {
    private final TypeRepository typeRepository;
    @Transactional
    public Type findTypeById(long id) throws ResourceNotFoundException {
        return typeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("No Type record Found::"+id));
    }
}
