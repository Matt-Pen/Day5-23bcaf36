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
import java.util.regex.Pattern;
import javax.xml.namespace.QName;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Employee {
    public void create() {

        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String uri = "mongodb://admin:admin@172.21.17.53:27017,172.21.17.54:27017,172.21.17.92:27017/"; // Replace with your MongoDB URI if needed

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("CompanyMatt");
            MongoCollection<Document> booksCollection = database.getCollection("Employee");

            //
            System.out.println("enter the employee id: ");
            int eid = sc.nextInt();
            sc.nextLine();
            System.out.println("enter the name of the employee: ");
            String name = sc.nextLine();
            System.out.println("Enter email of the employee: ");
            String email = sc.nextLine();
            System.out.println("Enter the number of Skill to enter:");
            int sk = sc.nextInt();
            sc.nextLine();
            List<String> arr = new ArrayList<>();
            for (int i = 0; i < sk; i++) {
                int a=i+1;
                System.out.println("enter skill " + a + ":");
                arr.add(sc.nextLine());
            }
            System.out.println("Enter Department of the employee: ");
            String dpt = sc.nextLine();
            System.out.println("Date of Joining: ");
            String dt = sc.nextLine();
//                LocalDate date = LocalDate.parse(dt, formatter);

            Bson filter= Filters.eq("Email",email);
            Bson Projection = Projections.fields(Projections.include("Email"));
            Document empDoc = booksCollection.find(filter).projection(Projection).first();

            if(empDoc!=null) {
                throw new Exception("Email Already Exist ");
            }

            Document sampleemp = new Document("Emp id", eid).append("name", name).append("Email", email).append("Skills", arr).append("Department", dpt).append("Joining date", dt);
            System.out.println(sampleemp);
            booksCollection.insertOne(sampleemp);


        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void Update() {

        Scanner sc = new Scanner(System.in);
        String uri = "mongodb://admin:admin@172.21.17.53:27017,172.21.17.54:27017,172.21.17.92:27017/"; // Replace with your MongoDB URI if needed

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("CompanyMatt");
            MongoCollection<Document> booksCollection = database.getCollection("Employee");


            System.out.println("enter your choice to Update");
            System.out.println("1.Name");
            System.out.println("2.Email");
            System.out.println("3.Skills");
            System.out.println("4.Department");
            System.out.println("5.Joining Date");
            int ch = sc.nextInt();
            sc.nextLine();
            switch (ch) {
                case 1:
                    System.out.println("Enter Employee id:");
                    int eid = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter the new name of the Employee");
                    String name2 = sc.nextLine();
                    Bson filter = Filters.eq("Emp id", eid);
                    Bson update = Updates.set("name", name2);
// Updates first matching document
                    UpdateResult result = booksCollection.updateOne(filter, update);
                    break;
                case 2:
                    System.out.println("Enter Employee id:");
                    int eid2 = sc.nextInt();
                    sc.nextInt();
                    System.out.println("Enter the new Email of the Employee");
                    String mtitle2 = sc.nextLine();
                    Bson email= Filters.eq("Email",mtitle2);
                    Bson Projection = Projections.fields(Projections.include("Email"));
                    Document empDoc = booksCollection.find(email).projection(Projection).first();

                    if(empDoc!=null) {
                        throw new Exception("Email Already Exist ");
                    }
                    Bson filter2 = Filters.eq("Emp id", eid2);
                    Bson update2 = Updates.set("Email", mtitle2);
// Updates first matching document
                    UpdateResult result2 = booksCollection.updateOne(filter2, update2);
                    break;
                case 3:
                    System.out.println("Enter Employee id:");
                    int eid3 = sc.nextInt();
                    System.out.println("Enter the Number of skills:");
                    int sk = sc.nextInt();
                    System.out.println("Enter all skills old and new.");
                    sc.nextLine();
                    List<String> arr = new ArrayList<>();
                    for (int i = 0; i < sk; i++) {
                        int a=i+1;
                        System.out.println("enter New skill: " + a + ":");
                        arr.add(sc.nextLine());
                    }
                    Bson filter3 = Filters.eq("Emp id", eid3);
                    Bson update3 = Updates.set("Skills", arr);
// Updates first matching document
                    UpdateResult result3 = booksCollection.updateOne(filter3, update3);
                    break;

                case 5:
                    System.out.println("Enter Employee id:");
                    int eid4 = sc.nextInt();
                    System.out.println("Enter the New department:");
                    String mtitle3 = sc.nextLine();
                    Bson filter4 = Filters.eq("Emp id", eid4);
                    Bson update5 = Updates.set("Department", mtitle3);
// Updates first matching document
                    UpdateResult result4 = booksCollection.updateOne(filter4, update5);
                    break;

                case 4:
                    System.out.println("Enter Employee id:");
                    int eid5 = sc.nextInt();
                    System.out.println("Enter the New joining date:");
                    String mtitle4 = sc.nextLine();
                    Bson filter5 = Filters.eq("Emp id", eid5);
                    Bson update6 = Updates.set("Department", mtitle4);
// Updates first matching document
                    UpdateResult result6 = booksCollection.updateOne(filter5, update6);
                    break;
            }
        } catch (Exception e) {
            System.out.print(e);
        }

    }
    public void delete(){
        Scanner scan= new Scanner(System.in);
        String uri = "mongodb://admin:admin@172.21.17.53:27017,172.21.17.54:27017,172.21.17.92:27017/";
        try{
            MongoClient mongoClient= MongoClients.create(uri);
            MongoDatabase database = mongoClient.getDatabase("CompanyMatt");
            MongoCollection<Document> booksCollection = database.getCollection("Employee");


            System.out.println("Enter the Employee id whose record to delete: ");
            int ch=0;

            ch=scan.nextInt();
            Bson filter = Filters.eq("Emp id", ch);
            booksCollection.deleteOne(filter);
            System.out.println("Deleted Successfully");
        }
        catch (Exception e){
            System.out.println(e);
        }

    }

}
