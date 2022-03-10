package stan.security.spring_security.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(	name = "foodItem")

public class FoodItem {
    @ManyToOne
    @JoinColumn(name = "store_id")
    @JsonBackReference
    private Store store;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String foodname;

    @NotBlank
    @Size(max = 20)
    private String location ;

    @NotBlank
    @Size(max = 20)
    private String price;

    @NotBlank
    @Size(max = 20)
    private String quantity;


}
