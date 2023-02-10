package com.bridgelabz;

import com.google.gson.Gson;
import com.opencsv.CSVWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.xml.crypto.Data;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHandler {
    public void writeIntoFile(AddressBook addressBook) {

        Path path = Paths.get("C:\\Users\\User\\IdeaProjects\\Review2\\src\\main\\java\\com\\bridgelabz\\ContactBook.txt");

        try {
            String data = "";
            for (Contacts contact: addressBook.contactList) {
                data += contact.toString();
            }
            java.nio.file.Files.write(path,data.getBytes());
            System.out.println("Data written into file");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void readFromFile(){
        Path path = Paths.get("C:\\Users\\User\\IdeaProjects\\Review2\\src\\main\\java\\com\\bridgelabz\\ContactBook.txt");
        try {
            System.out.println(Files.readAllLines(path));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void writeToCSV(AddressBook addressBook) {
        try {
            CSVWriter write = new CSVWriter(new FileWriter("C:\\Users\\User\\IdeaProjects\\Review2\\src\\main\\java\\com\\bridgelabz\\Contacts.csv"));
            for (Contacts contact : addressBook.contactList) {
                String[] data = new String[]{contact.firstName, contact.lastName, contact.address, contact.city, contact.state, contact.email, contact.phoneNo};
                write.writeNext(data);
            }
            System.out.println("Data written into csv file");
            write.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void readFromCSV(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\User\\IdeaProjects\\Review2\\src\\main\\java\\com\\bridgelabz\\Contacts.csv"));
            String data = "";
            while((data = reader.readLine()) != null) {
                String[] values = data.split(",");
                for (int i = 0; i < values.length; i++)
                    System.out.println(values[i]);
            }
            System.out.println(" ");
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //uc15
    public void writeToJson(AddressBook addressBook) {
        Gson gson = new Gson();
        String[] data = new String[10];
        for (Contacts contact : addressBook.contactList) {
            data = new String[]{contact.firstName, contact.lastName, contact.address, contact.city, contact.state, contact.email, contact.phoneNo};
        }
        try (FileWriter writer = new FileWriter("C:\\Users\\User\\IdeaProjects\\Review2\\src\\main\\java\\com\\bridgelabz\\ContactBook.json")){
            gson.toJson(data,writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readFromJson(){
        Gson gson = new Gson();
        try {
            FileReader reader = new FileReader("C:\\Users\\User\\IdeaProjects\\Review2\\src\\main\\java\\com\\bridgelabz\\ContactBook.json");
            Data data = gson.fromJson(reader, Data.class);
            System.out.println(data);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
