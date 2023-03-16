import utils.DomainConstraint;
import utils.NotPossibleException;
import utils.AttrRef;
import utils.DOpt;
import utils.OptType;

public class Person {
    // state space
    @DomainConstraint(type = "Integer", mutable = false, optional = false, min = 1)
    private int id;

    @DomainConstraint(type = "String", mutable = true, optional = false, length = 30)
    private String name;

    @DomainConstraint(type = "MobilePhone", mutable = true, optional = true)
    private MobilePhone phone;

    // behaviour space
    public Person(@AttrRef("id") int id, @AttrRef("name") String name) throws NotPossibleException {
        if (!validateId(id)) {
            throw new NotPossibleException("Person.init: invalid id: " + id);
        }

        if (!validateName(name)) {
            throw new NotPossibleException("Person.init: invalid name: " + name);
        }

        this.id = id;
        this.name = name;
    }

    @DOpt(type = OptType.Observer)
    @AttrRef("id")
    public int getId() {
        return id;
    }

    @DOpt(type = OptType.Observer)
    @AttrRef("name")
    public String getName() {
        return name;
    }

    @DOpt(type = OptType.Observer)
    @AttrRef("phone")
    public MobilePhone getPhone() {
        return phone;
    }

    @DOpt(type = OptType.Mutator)
    @AttrRef("name")
    public boolean setName(String name) {
        if (validateName(name)) {
            this.name = name;
            return true;
        } else {
            return false;
        }

    }

    @DOpt(type = OptType.Mutator)
    @AttrRef("phone")
    public boolean setPhone(MobilePhone phone) {
        if (validatePhone(phone)) {
            this.phone = phone;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        String str;
        str = "Person(" + id + "," + name + ")";
        // use string buffer

        // StringBuffer sb = new StringBuffer();
        // sb.append("Person(");
        // sb.append(id).append("," );
        // sb.append(name).append(",");
        // sb.append(phone).append(")");
        // str = sb.toString();

        return str;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Person)) {
            return false;
        } else {
            Person other = (Person) o;
            return id == other.getId();
        }
    }

    public boolean repOK() {
        if (validateId(id) && validateName(name)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean validateId(int id) {
        if (id < 1) {
            return false;
        } else {
            return true;
        }
    }

    private boolean validateName(String name) {
        if (name == null || name.length() == 0) {
            return false;
        }
        if (name.length() > 30) {
            return false;
        } else {
            return true;
        }
    }

    private boolean validatePhone(MobilePhone phone) {
        return true;
    }
}
