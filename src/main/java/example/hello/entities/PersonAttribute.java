package example.hello.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.envers.Audited;

import javax.annotation.Nonnull;
import javax.persistence.*;

@Audited
@Entity
@Table(name = PersonAttribute.TABLE)
@IdClass(PersonAttributeId.class)
public class PersonAttribute {

    public static final String TABLE = "persons_attributes";

    public static final String COLUMN_PERSON_ID = "person_id";
    public static final String COLUMN_ATTRIBUTE_ID = "attribute_id";
    public static final String COLUMN_VALUE = "value";

    @OnDelete(action = OnDeleteAction.CASCADE)
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_PERSON_ID, nullable = false)
    private Person person;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_ATTRIBUTE_ID, nullable = false)
    private Attribute attribute;

    @Id
    @Column(name = COLUMN_VALUE, nullable = false)
    private String value;

    public PersonAttribute() {

    }

    public PersonAttribute(@Nonnull Person person, @Nonnull Attribute attribute, @Nonnull String value) {
        this.person = person;
        this.attribute = attribute;
        this.value = value;
    }

    public Person getPerson() {
        return person;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonAttribute)) return false;

        PersonAttribute that = (PersonAttribute) o;

        if (!person.equals(that.getPerson())) return false;
        if (!attribute.equals(that.getAttribute())) return false;
        return value.equals(that.getValue());
    }

    @Override
    public int hashCode() {
        int result = person.hashCode();
        result = 31 * result + attribute.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }
}
