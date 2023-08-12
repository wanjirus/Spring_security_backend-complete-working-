package stan.inc.models.property;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "propertyImages")
@Getter
@Setter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
//@NoArgsConstructor
public class PropertyImages {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "image", unique = false, nullable = false, length = 100000)
    private byte[] image;
//
//    @OneToOne(orphanRemoval = true)
//    @JoinTable(name = "trial_user",
//            joinColumns = @JoinColumn(name = "trial_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id"))
//    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "property_id")
    private Property property;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PropertyImages that = (PropertyImages) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

