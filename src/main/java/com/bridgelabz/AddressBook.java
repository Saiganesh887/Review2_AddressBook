package com.bridgelabz;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class AddressBook {
    //to store multiple contacts in addressbook -> uc5
    public List<Contacts> contactList = new ArrayList<>();

    //for adding new contact -> uc2
    public void addNewContact() {
        boolean flag = true;
        Scanner scan = new Scanner(System.in);
        System.out.println("Creating new contact");
        System.out.println("Enter the details of contact");
        String firstName=null;
        String lastName=null;
        while (flag) {
            System.out.println("Enter First Name :");
            firstName = scan.nextLine();
            System.out.println("Enter Last Name :");
            lastName = scan.nextLine();
            flag = checkIfAlreadyExists(firstName, lastName);
        }
        System.out.println("Enter Address :");
        String address = scan.nextLine();
        System.out.println("Enter city :");
        String city = scan.nextLine();
        System.out.println("Enter state :");
        String state = scan.nextLine();
        System.out.println("Enter e-mail :");
        String email = scan.nextLine();
        System.out.println("Enter phone number :");
        String phoneNo = scan.nextLine();
        contactList.add(new Contacts(firstName, lastName, address, city, state, email, phoneNo));
    }

    //for checking if contact already exists in contactList -> uc7
    private boolean checkIfAlreadyExists(String firstName, String lastName) {
        for (Contacts contact : contactList) {
            if (contact.firstName.equals(firstName) && contact.lastName.equals(lastName)) {
                System.out.println("Contacts Already exists");
                return true;
            }
        }
        return false;
    }

    //for printing all the contacts in contactList
    public void printAllContacts() {
        for (int i = 0; i < contactList.size(); i++) {
            System.out.println(contactList.get(i));
        }
    }

    //for deleting the contact based on first name -> uc4
    public void deleteContact() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the first name of contact to be deleted :");
        String name = scan.nextLine();
        for (Contacts contact : contactList) {
            if (contact.firstName.equals(name)) {
                contactList.remove(contact);
                System.out.println("Contacts with first name "+ contact.firstName +" deleted");
            }
        }
    }

    //for editing the particular contact details by using name -> uc3
    public void editContact() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter name of contact to Edit");
        String name = scan.nextLine();
        for (Contacts contacts : contactList) {
            if (contacts.firstName.equals(name)) {
                System.out.println(contacts);
                System.out.println("Enter what you want to edit \n1.First Name \n2.Last Name"
                        + " \n3.Address \n4.City \n5.State \n6.E-mail \n7.Phone Number");
                int choice = scan.nextInt();
                scan.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("Enter the new First Name");
                        String newFirstName = scan.nextLine();
                        contacts.firstName = newFirstName;
                        break;

                    case 2:
                        System.out.println("Enter the new Last Name");
                        String newLastName = scan.nextLine();
                        contacts.lastName = newLastName;
                        break;

                    case 3:
                        System.out.println("Enter the new Address");
                        String newAddress = scan.nextLine();
                        contacts.address = newAddress;
                        break;

                    case 4:
                        System.out.println("Enter the new City");
                        String newCity = scan.nextLine();
                        contacts.city = newCity;
                        break;

                    case 5:
                        System.out.println("Enter the new State");
                        String newState = scan.nextLine();
                        contacts.state = newState;
                        break;
                    case 6:
                        System.out.println("Enter the new E-mail");
                        String newEmail = scan.nextLine();
                        contacts.email = newEmail;
                        break;

                    case 7:
                        System.out.println("Enter the new Phone Number");
                        String newPhoneNo = scan.nextLine();
                        contacts.phoneNo = newPhoneNo;
                        break;
                    default:
                        System.out.println("Invalid Input");
                }
            }
        }
    }


    // to print contacts in alphabetical order of name-> uc11
    public void printSorted() {
        contactList.stream().sorted((contact1,contact2) -> contact1.firstName.compareTo(contact2.lastName)).forEach(contact -> System.out.println(contact));
    }

    // to sort the entries in address book by city or state -> uc12
    public void printSortedByOptions() {
        int option = 0;
        Scanner scan = new Scanner(System.in);
        while (option != 7) {
            System.out.println("Sort by");
            System.out.println("1.City \n2.State"
                    + " \n3.Exit");
            option = scan.nextInt();
            switch (option) {
                case 1:
                    contactList.stream().sorted(Comparator.comparing(contact -> contact.city)).forEach(contact -> System.out.println(contact));
                    break;
                case 2:
                    contactList.stream().sorted(Comparator.comparing(contact -> contact.state)).forEach(contact -> System.out.println(contact));
                    break;
                case 3:
                    System.out.println("Exiting");
                    break;
                default:
                    System.out.println("Invalid Selection ");
            }
        }

    }
}
