package stan.inc.models.image;

import lombok.*;
import stan.inc.models.user.User;

import javax.persistence.*;

@Entity
@Table(name = "trial")
//@ToString
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrialImage {
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

    @OneToOne(orphanRemoval = true)
    @JoinTable(name = "trial_user",
            joinColumns = @JoinColumn(name = "trial_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private User user;
}
