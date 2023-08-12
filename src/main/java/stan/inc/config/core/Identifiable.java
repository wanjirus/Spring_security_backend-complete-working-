package stan.inc.config.core;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

//@EqualsAndHashCode(of = {"id"})
@MappedSuperclass
public abstract class Identifiable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Identifiable)) return false;
        Identifiable that = (Identifiable) o;
        return this.id != null && Objects.equals(this.id, that.id);
    }
    @Override
    public int hashCode() {
        return 31;
    }
}
