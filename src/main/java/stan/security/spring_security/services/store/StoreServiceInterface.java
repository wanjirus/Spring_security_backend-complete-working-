package stan.security.spring_security.services.store;

import stan.security.spring_security.DTO.StoreDTO;

public interface StoreServiceInterface {
    public StoreDTO createNewStore(long userId,  StoreDTO  storeDTO);
    public StoreDTO findStoreByUserId(long userId);
    public StoreDTO findStoreById(long storeId);

}
