package stan.security.spring_security.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;
//import stan.security.spring_security.models.User;

@Data
@Getter
@Setter
public class PropertyDTO {
    private Long id;

    @NotBlank
    @Size(min = 3, max = 20)
    private String name;

    @NotBlank
    @Size(min = 3, max = 20)
    private String location;

    @NotBlank
    @Size(min = 3, max = 20)
    private String status;

    @NotBlank
    @Size(min = 3, max = 20)
    private Double price;

    @NotBlank
    @Size(min = 3, max = 20)
    private String description;

    @NotBlank
    @Size(min = 3, max = 20)
    private String contactInfo;

    @NotBlank
    @Size(min = 3, max = 20)
    private String dimensions;

   private Set<Object> categories;

   private Set<Object> types;


    //    private Set<String> type;

//    private User owner;
//    private String phoneNumber;
}
