package efs.task.syntax;

import java.util.Random;
import java.util.Scanner;



public class GuessNumberGame {

    private final int M;
    private final int L;

    private int attempts = 0;

    //private int attempts;

    //Do not modify main method
    public static void main(String[] args) {
        try {
            GuessNumberGame game = new GuessNumberGame(args.length > 0 ? args[0] : "");
            game.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GuessNumberGame(String argument) {
        //TODO: Implement the constructor
        if (argument.isEmpty() || !argument.matches("\\d+")) {
            System.out.println(UsefulConstants.WRONG_ARGUMENT);
            throw new IllegalArgumentException();
        }

        M = Integer.parseInt(argument); //zmiana z wartości przekazywanej jako argument programu na int
        if (M < 1 || M > UsefulConstants.MAX_UPPER_BOUND) {
            System.out.println(UsefulConstants.WRONG_ARGUMENT);
            throw new IllegalArgumentException();
        }

        L = (int) Math.abs(Math.log(M) / Math.log(2)) + 1;

    }

    public void play() {
        System.out.println("<1," + M + ">");
//        int attempts = 0;
        Random random = new Random();
        int randomNumber = random.nextInt(M)+1;


        while (attempts < L) {
            int remainingAttempts = L - attempts-1;
            System.out.print("Twoje próby: [");
            for (int i = 0; i <= attempts; i++) {
                System.out.print("*");
            }
            for (int i = 0; i < remainingAttempts; i++) {
                System.out.print(".");
            }
            System.out.println("]");

            System.out.println("PODAJ");
            Scanner scanner = new Scanner(System.in);

            String input = scanner.nextLine();

            attempts++;
            try {
                int guess = Integer.parseInt(input);
                if (guess == randomNumber) {
                    System.out.println(UsefulConstants.YES);
                    System.out.println(UsefulConstants.CONGRATULATIONS);
                    //System.out.println(attempts + " - tyle prób zajęło Ci odgadnięcie liczby " + randomNumber);
                    return;
                } else if (guess > randomNumber) {
                    System.out.println(UsefulConstants.TO_MUCH);
                } else {
                    System.out.println(UsefulConstants.TO_LESS);

                }

            } catch (NumberFormatException e) {
                System.out.println(UsefulConstants.NOT_A_NUMBER);
            }
        }
        System.out.println(UsefulConstants.UNFORTUNATELY);
    }
}
