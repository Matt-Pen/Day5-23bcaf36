package org.example;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Search {
    public void searchemp(){
        Scanner sc= new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String uri = "mongodb://admin:admin@172.21.17.53:27017,172.21.17.54:27017,172.21.17.92:27017/";
        try{
            MongoClient mongoClient= MongoClients.create(uri);
            MongoDatabase database = mongoClient.getDatabase("CompanyMatt");
            MongoCollection<Document> booksCollection = database.getCollection("Employee");

            System.out.println("Enter your Search Criteria:");
            System.out.println("1.By Name.");
            System.out.println("2.By Skill");
            System.out.println("3.By Department");
            System.out.println("4.Joining Date Range");

            int ch = sc.nextInt();
            sc.nextLine();
            switch (ch) {
                case 1:
                    System.out.println("Enter the name of the Employee: ");
                    String cho= sc.nextLine();

                    Bson filter = Filters.regex("name", cho,"i");
//            Bson Projection = Projections.fields(Projections.include("Email"));
//            Document empDoc = booksCollection.find(filter).first();

                    System.out.println("All Employee Records with "+ cho +" in the Employee name.");
                    booksCollection.find(filter).forEach(doc -> System.out.println(doc.toJson()));

                    break;
                case 2:
                    System.out.println("Enter Skill to search for:");
                    String skill = sc.nextLine();


                    Bson skillfil= Filters.regex("Skills",skill);
                    System.out.println("All Employee Records with the Skill:"+ skill);
                    booksCollection.find(skillfil).forEach(doc -> System.out.println(doc.toJson()));

                    break;
                case 3:
                    System.out.println("Enter the Department name:");
                    String title2= sc.nextLine();

                    Bson filter2= Filters.regex("Department",title2);
                    booksCollection.find(filter2).forEach(doc -> System.out.println(doc.toJson()));

                    break;

                case 4:
                    System.out.println("Enter Start date(format[dd-MM-yyyy]):");
                    String txt1= sc.nextLine();
                    LocalDate date1 = LocalDate.parse(txt1, formatter);

                    System.out.println("Enter End date(format[dd-MM-yyyy]):");
                    String txt2= sc.nextLine();
                    LocalDate date2 = LocalDate.parse(txt2, formatter);

                    Bson projection = Projections.fields(Projections.include("Joining date"));

                    for(Document doc:booksCollection.find().projection(projection)){
//                        System.out.println(doc.toJson());
                        String txt3= doc.getString("Joining date");

                        LocalDate date3 = LocalDate.parse(txt3, formatter);

                        if(date3.isAfter(date1) & date3.isBefore(date2)){
                            Bson filter3=Filters.eq("Joining date",txt3);
                            booksCollection.find(filter3).forEach(doc8 -> System.out.println(doc8.toJson()));

                        }

                    }

                    break;
            }




        }
        catch (Exception e){
            System.out.println(e);
        }



    }
}
