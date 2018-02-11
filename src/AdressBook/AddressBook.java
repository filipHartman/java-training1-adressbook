package AdressBook;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddressBook {
    private String name;
    private ArrayList<Address> addresses = new ArrayList<>();

    public AddressBook(String name) {
        this.name = name;
    }

    public void addAddress(Address address) {
        addresses.add(address);
    }

    public ArrayList<Address> find(String searchPhrase) throws IndexOutOfBoundsException {
        ArrayList<Address> result = new ArrayList<>();
        String pattern = ".*" + searchPhrase.toUpperCase() + ".*";
        Pattern p = Pattern.compile(pattern);
        for (Address address : addresses) {
            String fullAddress = address.getFullAddress();
            fullAddress = fullAddress.toUpperCase();
            Matcher m = p.matcher(fullAddress);
            if (m.matches()) {
                result.add(address);
            }
        }
        return result;
    }
}
