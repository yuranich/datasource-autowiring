package example.hello.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.envers.AuditMappedBy;
import org.hibernate.envers.Audited;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.*;
import java.util.*;
import java.util.stream.Stream;

@Audited
@Entity
@Table(name = "persons")
@SuppressWarnings("UnusedDeclaration")
public class Person {
    public static final int TRADER_SPLIT_COUNT = 10;

    public final static String INDEX_BY_EMAIL = "index_by_email";
    public final static String INDEX_BY_PHONE_NUMBER = "index_by_phone_number";

    public static final String PERSON_GROUP_JOIN_TABLE = "person_to_group";
    public static final String PERSON_GROUP_JOIN_COLUMN = "person_id";
    public static final String PERSON_GROUP_INV_JOIN_COLUMN = "group_id";

    public static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_LAST_NAME = "last_name";
    public static final String COLUMN_NICK_NAME = "nick_name";
    public static final String COLUMN_EXTRACTED = "extracted";
    public static final String COLUMN_MONITORED = "monitored";
    public static final String COLUMN_COMPANY_ID = "company_id";
    public static final String COLUMN_CONTACTS = "contacts";
    public static final String COLUMN_ATTRIBUTES = "attributes";

    public static final int NA_ID = -1;
    public static final int NOT_ASSIGNED_ID = 0;

    public Person() {
    }

    public Person(boolean monitored) {
        this.monitored = monitored;
    }

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false, insertable = true, updatable = true)
    private Integer id;

    @Column(name = COLUMN_FIRST_NAME, nullable = true, insertable = true, updatable = true, columnDefinition="varchar (255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci")
    private String firstName;

    @Column(name = COLUMN_LAST_NAME, nullable = true, insertable = true, updatable = true, columnDefinition="varchar (255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci")
    private String lastName;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person", orphanRemoval = true, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<PersonAttribute> attrs;

    /**
     * Monitored person flag.
     */
    @Column(name = COLUMN_MONITORED, insertable = true, updatable = true)
    private boolean monitored;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isMonitored() {
        return monitored;
    }

    public void setMonitored(boolean monitored) {
        this.monitored = monitored;
    }

    public Set<PersonAttribute> getAttrs() {
        if (attrs == null)
            attrs = new HashSet<>();

        return attrs;
    }

    public String getAttrValue(@Nonnull String name) {
        if (attrs == null)
            return null;

        for (PersonAttribute attr : attrs) {
            if (name.equalsIgnoreCase(attr.getAttribute().getName()))
                return attr.getValue();
        }

        return null;
    }

    public Stream<String> getAttrValues(@Nonnull String name) {
        if (attrs == null)
            return Stream.empty();

        return attrs.stream().filter(a -> name.equalsIgnoreCase(a.getAttribute().getName())).map(PersonAttribute::getValue);
    }

    public void setAttrs(Set<PersonAttribute> attrs) {
        this.attrs = attrs;
    }

    @Nullable
    public String getFullName() {
        StringBuilder fullName = new StringBuilder();
        if (getFirstName() != null) {
            fullName.append(getFirstName());
        }

        if (getLastName() != null) {
            fullName.append(fullName.length() > 0 ? " " : "");
            fullName.append(getLastName());
        }

        return fullName.length() > 0 ? fullName.toString() : null;
    }

    @SuppressWarnings("RedundantIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (id == null)
            return false;

        return Objects.equals(id, person.getId());
    }

    @Override
    public int hashCode() {
        return id == null ? System.identityHashCode(this) : id;
    }

    @Override
    public String toString() {
        return "#" + id + " " + Optional.ofNullable(getFullName()).orElse("");
    }
}
