package com.bridgelabz;

public class Contacts {
    public String firstName;
    public String lastName;
    public String address;
    public String city;
    public String state;
    public String email;
    public String phoneNo;

    Contacts() {

    }

    public Contacts(String firstName, String lastName, String address, String city, String state, String email, String phoneNo) {

        this.firstName=firstName;
        this.lastName=lastName;
        this.address=address;
        this.city=city;
        this.state=state;
        this.email=email;
        this.phoneNo=phoneNo;
    }

    @Override
    public String toString() {
        return "Contacts{" +
                "\nfirstName='" + firstName + '\'' +
                "\nlastName='" + lastName + '\'' +
                "\naddress='" + address + '\'' +
                "\ncity='" + city + '\'' +
                "\nstate='" + state + '\'' +
                "\nemail='" + email + '\'' +
                "\nphoneNo='" + phoneNo + '\'' +
                '}';
    }
}
