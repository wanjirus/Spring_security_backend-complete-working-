package stan.security.spring_security.DTO;


import lombok.Data;
import stan.security.spring_security.models.Store;

@Data
public class StoreDTO extends Store {
    private Long id;
    private String name;
    private String storeType;
    private String location;




}
