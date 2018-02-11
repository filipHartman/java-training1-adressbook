package AdressBook;

import java.util.Objects;

public class WorkAddress extends Address {
    private String company;

    public WorkAddress(String name, String city, String street, String houseNo, String company) {
        super(name, city, street, houseNo);
        this.company = company;
    }

    public String getFullAddress() {
        return String.format("%s, %s", super.getFullAddress(), company);
    }

    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }

        if(obj == null) {
            return false;
        }

        if(!(obj instanceof WorkAddress)) {
            return false;
        }
        WorkAddress address = (WorkAddress) obj;

        return Objects.equals(getFullAddress(), address);
    }
    
    public int hashCode() {
        return Objects.hash(getFullAddress());
    }
}
