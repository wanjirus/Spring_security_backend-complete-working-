package stan.security.spring_security.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
@Table(	name = "foodEvent")

public class FoodEvent {

    @ManyToOne
    @JoinColumn(name = "store_id")
    @JsonBackReference
    private Store store;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String eventName;

    @NotBlank
    @Size(max = 20)
    private String location;

    @NotBlank
    @Size(max = 20)
    private Date schedule;

    @NotBlank
    @Size(max = 20)
    private String requirements;

    public FoodEvent(){

    }


}
