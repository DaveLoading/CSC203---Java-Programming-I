//import the scanner to read the input
import java.util.Scanner; 

public class Interactive {
    // start of my java file 
    public static void main(String[] args) {
    Scanner myObj = new Scanner(System.in);  // Create a Scanner object
    //introduces itself and asks for users name
    System.out.println("\nHi my name is micheal I wanna get to know you\nWhat's your name ?");

    String Name = myObj.nextLine();  // Read user input
    System.out.println("it's nice to meet you" + Name);  // Output users name

    Scanner myAge = new Scanner(System.in);  // Create a Scanner object
    //introduces itself and asks for users name
    System.out.println("\nHow old are you?");

    int Age = myAge.nextInt();  // Read user input as an integer
    System.out.println("\noh wow your " + Age + " what a great age to be" );  // Output users age

    //calculting when you turn 100 first get the current year
     int currentYear = java.time.Year.now().getValue(); // Get the current year
    // we then add 100 and take away the age that's when you'll be 100 since we don't gather any years the user's are born
     int yearTurn100 = currentYear + (100 - Age); // Add remaining years
    // letting the user know when they'll be 100    
     System.out.println("\nDid you know you will turn 100 in the year " + yearTurn100 + "?");
    
     // Add a fun fact about their current age
        if (Age < 13) {
            System.out.println("\nFun fact: You have way more energy than most adults—no wonder recess feels so short.");
        } else if (Age < 20) {
            System.out.println("\nFun fact: Teens often have stronger bones than at any other point in life.");
        } else if (Age == 21) {
            System.out.println("\nFun fact: In the U.S., 21 is the legal drinking age—but in some countries, it’s 18 or even younger.");
        } else if (Age < 30) {
            System.out.println("\nFun fact: People in their 20s are most likely to move cities or switch jobs.");
        } else if (Age < 65) {
            System.out.println("\nFun fact: Adults in their 30s–40s tend to have their strongest problem-solving skills.");
        } else {
            System.out.println("\nFun fact: Many people over 65 live longer today than at any time in history.");
        }
    }
}