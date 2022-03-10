package stan.security.spring_security.DTO;

import lombok.Data;

@Data
public class FoodDTO {
    private Long id;
    private StoreDTO storeDTO;
    private String foodName;
    private String location;
    private String price;
    private String quantity;
}
