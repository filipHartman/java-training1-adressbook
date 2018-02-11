package AdressBook;

public class WorkAddress extends Address {
    private String company;

    public WorkAddress(String name, String city, String street, String houseNo, String company) {
        super(name, city, street, houseNo);
        this.company = company;
    }
    
}
