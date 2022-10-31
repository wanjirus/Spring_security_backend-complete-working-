package stan.security.spring_security.models;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "type")
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EType name;

    public Type() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EType getName() {
        return name;
    }

    public void setName(EType name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Type type = (Type) o;
        return Objects.equals(id, type.id) && name == type.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
