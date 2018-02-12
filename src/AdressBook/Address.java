package AdressBook;

import java.util.Objects;

public class Address implements Comparable<Address>{
    private String person;
    private String city;
    private String street;
    private String houseNo;

    public Address(String person, String city, String street, String houseNo) {
        this.person = person;
        this.city = city;
        this.street = street;
        this.houseNo = houseNo;
    }

    public String getFullAddress() {
        return String.format(
          "%s, %s, %s %s", person, city, street, houseNo
        );
    }

    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }

        if(obj == null) {
            return false;
        }
        if(!(obj instanceof Address)) {
            return false;
        }
        Address address = (Address) obj;

        return Objects.equals(getFullAddress(), address.getFullAddress());
    }

    public int hashCode() {
        return Objects.hash(getFullAddress());
    }

    public int compareTo(Address o) {
        int comparePerson = person.compareTo(o.person);
        if (comparePerson == 0) {
            return city.compareTo(o.city);
        }
        return comparePerson;
    }
}
