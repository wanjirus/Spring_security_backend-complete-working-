package stan.security.spring_security.models;

import javax.persistence.*;


@Entity
@Table(name = "user_avatar")
public class UserAvatar {
        public UserAvatar() {
            super();
        }
        public UserAvatar(String name, String type, byte[] picByte) {
            this.name = name;
            this.type = type;
            this.picByte = picByte;
        }

    @OneToOne(orphanRemoval = true)
    @JoinTable(name = "avatar_table_user",
            joinColumns = @JoinColumn(name = "user_avatar_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private User user;

        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "name")
        private String name;

        @Column(name = "type")
        private String type;

        //image bytes can have large length, so we specify a value
        //which is more than the default length for picByte column
        @Column(name = "picByte", length = 1000)
        private byte[] picByte;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getPicByte() {
        return picByte;
    }

    public void setPicByte(byte[] picByte) {
        this.picByte = picByte;
    }

}
