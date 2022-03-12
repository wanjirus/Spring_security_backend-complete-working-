package stan.security.spring_security.services.store;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import stan.security.spring_security.DTO.StoreDTO;
import stan.security.spring_security.exceptions.ResourceNotFoundException;
import stan.security.spring_security.mapper.StoreMapper;
import stan.security.spring_security.models.Store;
import stan.security.spring_security.models.User;
import stan.security.spring_security.repository.StoreRepository;
import stan.security.spring_security.services.auth.UserService;

import java.util.List;

@Service
@AllArgsConstructor
public class StoreService implements StoreServiceInterface{



    private final StoreMapper storeMapper;

    private final StoreRepository storeRepository;
    private  final UserService userService;


    public StoreDTO findStoreById(long id) throws ResourceNotFoundException {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No staff record found with Id::" + id));

        return (StoreDTO) storeMapper.toStoreDto(store);
    }

    public StoreDTO createNewStore(long userId, StoreDTO storeDTO) {
        User user = userService.findUserById(userId);
        Store store = constructStoreObject(storeDTO);
        store.setUser(user);
        store = storeRepository.save(store);
        return (StoreDTO) storeMapper.toStoreDto(store);
    }

    @Override
    public StoreDTO findStoreByUserId(long userId) {
        List<Store> storeList = storeRepository.findByUser(userId);
        return (StoreDTO) storeMapper.toStoreDto((Store) storeList);
    }

    //
    private Store constructStoreObject(StoreDTO storeDTO) {
        return Store.builder().name(storeDTO.getName())
                .location(storeDTO.getLocation())
                .storeType(storeDTO.getStoreType())
                .build();
    }

}
