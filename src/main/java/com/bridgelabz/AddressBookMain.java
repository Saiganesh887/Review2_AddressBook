package com.bridgelabz;

import java.util.*;

public class AddressBookMain {
    //to store multiple address books -> uc6
    private static List<AddressBook> addressBookList = new ArrayList<>();

    private static Map<String, AddressBook> map
            = new HashMap<String, AddressBook>();
    private static Map<String, List<String>> cityMap
            = new HashMap<String, List<String>>();
    private static Map<String, List<String>> stateMap
            = new HashMap<String, List<String>>();

    private static AddressBook addressBook;
    public static void addNewAddressBook() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the name of Address Book");
        String name = scan.nextLine();
        AddressBook addressBook = new AddressBook();
        addressBookList.add(addressBook);
        map.put(name, addressBook);
        System.out.println(" New Address Book created "+ name);
    }

    private static void removeAddressBook() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the name of Address Book to be removed");
        String addressBookname = scan.nextLine();
        for (Map.Entry<String, AddressBook> m : map.entrySet()) {
            if (m.getKey().equals(addressBookname)) {
                map.remove(addressBookname);
                System.out.println("Address Book named "+ addressBookname +" was deleted");
            }else{
                System.out.println("Address Book named "+addressBookname+" was not present");
            }
        }
    }

    private static void selectAddressBook(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the name of address book to be selected");
        String name = scan.nextLine();
//        map.forEach((x,y) -> {
//            System.out.println(x + "..."+ y);
//        });
        for (Map.Entry<String, AddressBook> m : map.entrySet()) {
            boolean yes = m.getKey().equals(name);
            if (yes) {
                addressBook = m.getValue();
                int option = 0;
                while (option != 12) {
                    System.out.println("1.Add Contact \n2.Remove Contact"
                            + " \n3.Edit Contact \n4.Print all Contact \n5.Print contacts in sorting Name order " +
                            "\n6.Print contacts in sorting by options \n7.Print to File \n8.Read from File  \n9.write to CSV" +
                            " \n10.read from CSV \n11.write to json \n12.Read from Json \n13.Back to main menu");
                    option = scan.nextInt();
                    FileHandler files = new FileHandler();
                    switch (option) {
                        case 1:
                            addressBook.addNewContact();
                            break;
                        case 2:
                            addressBook.deleteContact();
                            break;
                        case 3:
                            addressBook.editContact();
                            break;
                        case 4:
                            addressBook.printAllContacts();
                            break;
                        case 5:
                            addressBook.printSorted();
                            break;
                        case 6:
                            addressBook.printSortedByOptions();
                            break;
                        case 7:
                            files.writeIntoFile(addressBook);
                            break;
                        case 8:
                            files.readFromFile();
                            break;
                        case 9 :
                            files.writeToCSV(addressBook);
                            break;
                        case 10:
                            files.readFromCSV();
                            break;
                        case 11:
                            files.writeToJson(addressBook);
                            break;
                        case 12:
                            files.readFromJson();
                            break;
                        case 13:
                            System.out.println("Back to main menu");
                            break;
                        default:
                            System.out.println("Invalid Selection ");
                    }
                }
            }
        }
    }

    //to search person in a city or state in all addressbooks -> uc8
    private static void search() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the Name of State or City");
        String searchData = scan.nextLine();
        for (AddressBook addressBook : addressBookList) {
            addressBook.contactList.stream().filter(contacts -> contacts.state.equals(searchData)||contacts.city.equals(searchData)).forEach(contacts -> System.out.println(contacts));
        }
    }

    // to display contact by state -> uc9
    private static void displayByState() {
        addressBookList.stream().forEach((adBook) -> {
            adBook.contactList.stream().forEach(contact -> {
                if (stateMap.containsKey(contact.state))
                    stateMap.get(contact.state).add(contact.firstName+" "+contact.lastName);
                else {
                    List<String> state = new ArrayList<>();
                    state.add(contact.firstName + " " + contact.lastName);
                    stateMap.put(contact.state, state);
                }
            });
        });
        System.out.println(stateMap);
    }

    //display contact by city -> uc9
    private static void displayByCity() {
        addressBookList.stream().forEach((adBook) -> {
            adBook.contactList.stream().forEach(contact -> {
                if (cityMap.containsKey(contact.city))
                    cityMap.get(contact.city).add(contact.firstName+" "+contact.lastName);
                else {
                    List<String> city = new ArrayList<>();
                    city.add(contact.firstName + " " + contact.lastName);
                    cityMap.put(contact.city, city);
                }
            });
        });
        System.out.println(stateMap);
    }

    private static void showAddressBook() {
        for (Map.Entry<String, AddressBook> m : map.entrySet()) {
            if(m.getKey() == null)
                System.out.println("No Address Book with name are present");
            else
                System.out.println(m.getKey());
        }
    }

    // count contacts by city or state -> uc10
    private static void count() {
        System.out.println("count by city : "+cityMap.size());
        System.out.println("count by state : "+stateMap.size());
    }
    public static void main(String[] args) {
        System.out.println("Welcome to Address Book Program");
        int n = 0;
        Scanner scan = new Scanner(System.in);

        while (n != 9) {
            System.out.println("1.Create new Address book \n2.Remove Address book"
                    + "\n3.Print all Address book \n4. Select Address book \n5. Search \n6. Display by City \n7. Display by State \n8.Count \n9. Exit");
            System.out.println("Choose one option from the top: ");
            n = scan.nextInt();
            switch (n) {
                case 1:
                    addNewAddressBook();
                    break;
                case 2:
                    removeAddressBook();
                    break;
                case 3:
                    showAddressBook();
                    break;
                case 4:
                    try {
                        selectAddressBook();
                    }catch (Exception e){
                        System.out.println(e);
                    }
                    break;
                case 5:
                    search();
                    break;
                case 6:
                    displayByCity();
                    break;
                case 7:
                    displayByState();
                    break;
                case 8:
                    count();
                    break;
                case 9:
                    System.out.println("Exiting the AddressBook");
                    break;
                default:
                    System.out.println("Invalid Input");
            }
        }

    }
}
