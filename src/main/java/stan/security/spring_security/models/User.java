package stan.security.spring_security.models;

import lombok.*;
import org.hibernate.Hibernate;
import stan.security.spring_security.audit.Auditable;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(	name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })

public class User extends Auditable {


    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private UserAvatar userAvatar;

    @NotBlank
    @Size(max = 20)
    private String firstname;

    @NotBlank
    @Size(max = 20)
    private String secondName;


    @NotBlank
    @Size(max = 20)
    private String username;


    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ToString.Exclude
    private Set<Role> roles = new HashSet<>();

    public User(String firstname, String secondName, String username, String email, String password) {
        this.firstname = firstname;
        this.secondName = secondName;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return getId() != null && Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}