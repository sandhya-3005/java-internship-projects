package src;
import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {

        Random random = new Random();
        Scanner sc = new Scanner(System.in);

        int secretNumber = random.nextInt(100) + 1;
        int guess = 0;
        int attempts = 0;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("Guess a number between 1 and 100.");

        while (guess != secretNumber) {

            System.out.print("Enter your guess: ");

            if (sc.hasNextInt()) {
                guess = sc.nextInt();
                attempts++;
            } else {
                System.out.println(
                        "Invalid input! Please enter a number."
                );
                sc.next();
                continue;
            }

            if (guess < secretNumber) {
                System.out.println("low! Try a higher number.");
            } else if (guess > secretNumber) {
                System.out.println("high! Try a lower number.");
            } else {
                System.out.println(
                        "Correct! You guessed the number."
                );
                System.out.println(
                        "You guessed the number in "
                                + attempts + " attempts!"
                );
            }
        }

        sc.close();
    }
}