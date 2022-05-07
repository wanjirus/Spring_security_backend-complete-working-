package stan.security.spring_security.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
    @Table(name = "image_table")
    public class ImageModel{
    public ImageModel() {
        super();
    }
    public ImageModel(String name, String type, byte[] picByte) {
        this.name = name;
        this.type = type;
        this.picByte = picByte;
    }

    @OneToOne(orphanRemoval = true)
    @JoinTable(name = "image_table_user",
            joinColumns = @JoinColumn(name = "image_model_id"),
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

        //image bytes can have large lengths so we specify a value
        //which is more than the default length for picByte column
        @Column(name = "picByte", length = 1000)
        private byte[] picByte;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ImageModel that = (ImageModel) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
