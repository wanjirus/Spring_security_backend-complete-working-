//package stan.inc.models.user;
//
//import lombok.*;
//
//import javax.persistence.*;
//
//@ToString
//@Getter
//@Builder
//@Setter
//@RequiredArgsConstructor
//@Entity
//@Table(name = "ProfilePic")
//public class Image {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    private String name;
//
//    private String url;
//
//    @OneToOne(orphanRemoval = true)
//    @JoinTable(name = "profile_pic_user",
//            joinColumns = @JoinColumn(name = "image_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id"))
//    private User user;
//
//
//    // getters and setters
//}
