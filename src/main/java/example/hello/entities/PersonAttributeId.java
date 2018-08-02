package example.hello.entities;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PersonAttributeId implements Serializable {
    /**
     * Don't rename! Order of properties in primary key MUST be 'person, attribute, value'. Stupid hibernate sort properties by name.
     */
    private int person;

    /**
     * Don't rename! Order of properties in primary key MUST be 'person, attribute, value'. Stupid hibernate sort properties by name.
     */
    private int attribute;

    /**
     * Don't rename! Order of properties in primary key MUST be 'person, attribute, value'. Stupid hibernate sort properties by name.
     */
    private String value;

    private PersonAttributeId() {

    }

    public PersonAttributeId(int personId, int attributeId, String value) {
        this.person = personId;
        this.attribute = attributeId;
        this.value = value;
    }

    public int getPersonId() {
        return person;
    }

    public int getAttributeId() {
        return attribute;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonAttributeId)) return false;

        PersonAttributeId that = (PersonAttributeId) o;

        if (person != that.person) return false;
        if (attribute != that.attribute) return false;
        return value != null ? value.equals(that.value) : that.value == null;
    }

    @Override
    public int hashCode() {
        int result = person;
        result = 31 * result + attribute;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}