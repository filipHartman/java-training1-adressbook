package AdressBook;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddressBook {
    private String name;
    private ArrayList<Address> addresses = new ArrayList<>();

    public AddressBook(String name) {
        this.name = name;
    }

    public int getAddressesSize() {
        return addresses.size();
    }

    public ArrayList<Address> getAddresses() {
        return addresses;
    }

    public String getName() {
        return name;
    }

    public void addAddress(Address address) {
        addresses.add(address);
    }

    public ArrayList<Address> find(String searchPhrase) throws IndexOutOfBoundsException {
        ArrayList<Address> result = new ArrayList<>();
        searchPhrase = searchPhrase.trim();
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

    public void quickSort(int lowerIndex, int higherIndex) {
        if (!(addresses.isEmpty())) {
            int i = lowerIndex;
            int j = higherIndex;

            int pivotIndex = lowerIndex + (higherIndex- lowerIndex)/2;
            Address pivot = addresses.get(pivotIndex);

            while (i<= j) {
                while(addresses.get(i).compareTo(pivot) < 0){
                    i++;
                }
                while(addresses.get(j).compareTo(pivot) > 0){
                    j--;
                }
                if (i <= j) {
                    exchangeAddresses(i, j);
                    i++;
                    j--;
                }
            }

            if (lowerIndex < j) {
                quickSort(lowerIndex, j);
            }

            if (i < higherIndex) {
                quickSort(i, higherIndex);
            }
        }
    }

    private void exchangeAddresses(int i, int j) {
        Address temp = addresses.get(i);
        addresses.set(i, addresses.get(j));
        addresses.set(j, temp);
    }

    public static AddressBook createFromCSV(String listName, String csvPath) {
        AddressBook addressBook = new AddressBook(listName);

        try{
            BufferedReader csvFile = new BufferedReader(new FileReader(csvPath));
            csvFile.readLine();
            String line = null;
            while((line = csvFile.readLine()) != null) {
                Address address = createAddressFromCSVData(line);
                addressBook.addAddress(address);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return addressBook;
    }

    private static Address createAddressFromCSVData(String line) {
        int personIndex = 0;
        int cityIndex = 1;
        int streetIndex = 2;
        int houseNoIndex = 3;
        int companyIndex = 4;

        Address address = null;
        String [] data = line.split(",");
        String person = data[personIndex];
        String city = data[cityIndex];
        String street = data[streetIndex];
        String houseNo = data[houseNoIndex];
        if (data.length == 4){
            address = new Address(person, city, street, houseNo);
        } else if (data.length == 5) {
            String company = data[companyIndex];
            address = new WorkAddress(person, city, street, houseNo, company);
        }
        return address;
    }

    public void saveToCSV() {
        String header = "person, city, street, houseNo, company\n";
        String filePath = "out/" + getName() + ".csv";

        FileWriter fw = null;
        try {
            fw = new FileWriter(filePath);
            fw.write(header);
            for (Address address : addresses) {
                exportAddress(address, fw);
            }
            fw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void exportAddress(Address address, FileWriter fw) throws IOException{
        String commaDelimiter = ",";
        String newLineSeparator = "\n";
        StringBuilder addressData = new StringBuilder();
        addressData.append(address.getPerson() + commaDelimiter);
        addressData.append(address.getCity() + commaDelimiter);
        addressData.append(address.getStreet() + commaDelimiter);
        addressData.append(address.getHouseNo() + commaDelimiter);
        if (address instanceof WorkAddress) {
            addressData.append(((WorkAddress) address).getCompany());
        }
        addressData.append(newLineSeparator);
        fw.write(addressData.toString());
    }
}
