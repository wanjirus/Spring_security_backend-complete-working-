package stan.inc.models.property;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import stan.inc.config.audit.Auditable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@Builder
@NoArgsConstructor
//@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@ToString
//@RequiredArgsConstructor
@Table(	name = "property_messages"

//        uniqueConstraints = {
//        @UniqueConstraint(columnNames = {
// "contact_Info"
//}
//)}
)
public class PropertyMessage extends Auditable {
    @NotBlank
    @Size(max = 50)
    String message;
    @NotBlank
    @Size(max = 50)
    String sender;

    @ManyToOne
    @JoinColumn(name = "property_id")
    @JsonBackReference
    private Property property;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PropertyMessage that = (PropertyMessage) o;
        return message.equals(that.message) && sender.equals(that.sender) && property.equals(that.property);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), message, sender, property);
    }
}
