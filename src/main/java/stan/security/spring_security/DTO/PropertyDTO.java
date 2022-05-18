package stan.security.spring_security.DTO;

import lombok.Data;

@Data
public class PropertyDTO {
    private Long id;
    private String name;
    private String location;
    private Double price;
    private String description;
    private String contactInfo;
    private String phoneNumber;
}
