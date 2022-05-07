package stan.security.spring_security.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.Hibernate;
import stan.security.spring_security.audit.Auditable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@Builder
@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
@Getter
@Setter
@ToString
//@RequiredArgsConstructor
@Table(	name = "property",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = {
 "contact_Info"
}
)}
)

public class Property extends Auditable {
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @NotBlank
    @Size(max = 20)
    private String name;

    @NotBlank
    @Size(max = 20)
    private String location;

    @NotBlank
    @Size(max = 20)
    private String phoneNumber;

    @NotBlank
    @Size(max = 20)
    private Double price;

    @NotBlank
    @Size(max = 20)
    @Column(name = "contact_info")
    private String contactInfo;

    @NotBlank
    @Size(max = 20)
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Property property = (Property) o;
        return getId() != null && Objects.equals(getId(), property.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
