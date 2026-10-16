import java.util.Random;
import java.util.Scanner;

class SquareCalculator extends Thread {
    int number;

    SquareCalculator(int number) {
        this.number = number;
    }

    public void run() {
        System.out.println("Square: " + (number * number));
    }
}

class CubeCalculator extends Thread {
    int number;

    CubeCalculator(int number) {
        this.number = number;
    }

    public void run() {
        System.out.println("Cube: " + (number * number * number));
    }
}

class RandomNumberGenerator extends Thread {
    public void run() {
        Random random = new Random();

        for (int i = 0; i < 3; i++) {
            int number = random.nextInt(10) + 1;

            System.out.println("Generated: " + number);

            Thread calculator;

            if (number % 2 == 0) {
                calculator = new SquareCalculator(number);
            } else {
                calculator = new CubeCalculator(number);
            }

            calculator.start();

            try {
                calculator.join();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}

public class Q9 {
    public static void main(String[] args)
            throws InterruptedException {
        Scanner sc = new Scanner(System.in);

        int choice;

        do {
            System.out.println("1. Start Simulation");
            System.out.println("2. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            if (choice == 1) {
                RandomNumberGenerator generator =
                        new RandomNumberGenerator();

                generator.start();
                generator.join();
            } else if (choice != 2) {
                System.out.println("Invalid choice");
            }
        } while (choice != 2);

        System.out.println("Simulation exited");
        sc.close();
    }
}