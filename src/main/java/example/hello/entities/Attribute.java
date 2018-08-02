package example.hello.entities;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;

import javax.annotation.Nonnull;
import javax.persistence.*;
import java.util.Objects;

@Audited
@Entity
@Table(name = Attribute.TABLE)
public class Attribute {

    public static final String TABLE = "attributes";
    public static final String COLUMN_NAME = "name";

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = COLUMN_NAME, unique = true, nullable = false, updatable = false)
    private String name;

    public Attribute() {
    }

    public Attribute(@Nonnull String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Attribute)) return false;

        return Objects.equals(name, ((Attribute) o).getName());
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return name == null ? "null" : name;
    }
}
