package stan.security.spring_security.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;


@Entity
@Table(name = "property_snaps")
public class PropertySnaps {
        public PropertySnaps() {
            super();
        }
        public PropertySnaps(String name, String type, byte[] picByte) {
            this.name = name;
            this.type = type;
            this.picByte = picByte;
        }
    @ManyToOne
    @JoinColumn(name = "property_id")
    @JsonBackReference
    private Property property;
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

    public Property getProperty1() {
        return property;
    }

    public void setProperty1(Property property1) {
        this.property = property;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PropertySnaps that = (PropertySnaps) o;
        return Objects.equals(property, that.property) && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(type, that.type) && Arrays.equals(picByte, that.picByte);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(property, id, name, type);
        result = 31 * result + Arrays.hashCode(picByte);
        return result;
    }
}
