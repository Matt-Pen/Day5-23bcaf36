package org.example;

import java.util.Scanner;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Employee em=new Employee();
        Search sr= new Search();
        while(true)
        {
            System.out.println("Enter your choice:");
            System.out.println("1.Create a new Employee Record.");
            System.out.println("2.Update Employee Record.");
            System.out.println("3.Search for an Employee Record.");
            System.out.println("4.Delete a Employee Record.");
            System.out.println("5.Display all Employees.");
            int ch=sc.nextInt();
            switch(ch) {
                case 1:
                    em.create();
                    break;
                case 2:
                    em.Update();
                    break;
                case 3:
                    sr.searchemp();
                    break;
                case 4:
                    em.delete();
                    break;
                case 5:
                    sr.paginat();
                    break;
                case 6:
                    sr.aggreg();
                    break;
            }
        }

    }
}
