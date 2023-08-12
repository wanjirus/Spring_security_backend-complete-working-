package stan.inc.models.property;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import stan.inc.config.audit.Auditable;
import stan.inc.models.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Builder
@NoArgsConstructor
//@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@ToString
//@RequiredArgsConstructor
@Table(	name = "property"

//        uniqueConstraints = {
//        @UniqueConstraint(columnNames = {
// "contact_Info"
//}
//)}
)

public class Property extends Auditable {
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Size(max = 20)
    private String dimensions;

    @NotBlank
    @Size(max = 20)
    private String location;

    @NotBlank
    private String status;

//    @NotBlank
//    @Size(max = 20)
//    private String phoneNumber;

    @NotBlank
    @Size(max = 20)
    private Double price;

    @NotBlank
//    @Size(max = 20)
    @Column(name = "contact_info")
    private String contactInfo;

    @NotBlank
    @Size(max = 20)
    private String description;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "category_properties",
            joinColumns = @JoinColumn(name = "properties_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    private Set<Category> categories = new HashSet<>();


//    @ManyToOne
//    @JoinColumn(name = "category_id")
//    @JsonBackReference
//    private Category category;

    @OneToMany(mappedBy = "property", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<PropertyImages> propertyImages;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "type_properties",
            joinColumns = @JoinColumn(name = "properties_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude

    private Set<Type> types = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Property property = (Property) o;
        return Objects.equals(user, property.user) && Objects.equals(name, property.name) && Objects.equals(dimensions, property.dimensions) && Objects.equals(location, property.location) && Objects.equals(status, property.status) && Objects.equals(price, property.price) && Objects.equals(contactInfo, property.contactInfo) && Objects.equals(description, property.description) && Objects.equals(categories, property.categories) && Objects.equals(types, property.types);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), user, name, dimensions, location, status, price, contactInfo, description, categories, types);
    }
}
