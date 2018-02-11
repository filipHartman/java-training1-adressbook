package AdressBook;

import java.util.Objects;

public class Adress {
    private String person;
    private String city;
    private String street;
    private String houseNo;

    public Adress(String person, String city, String street, String houseNo) {
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
        if(!(obj instanceof Adress)) {
            return false;
        }
        Adress adress = (Adress) obj;
        
        return Objects.equals(getFullAddress(), adress.getFullAddress());
    }
}
