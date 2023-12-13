import java.util.Scanner;

public class Application {

    public static void main(String... args) {
        long beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

        int guesses = 10;
        Game game = new Game(4, guesses);
        for (int i = 0; i < guesses; i++) {
            // ask user for input
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your guess: ");
            String guess = scanner.nextLine();
            // check if input is valid
            boolean won = game.guess(guess);
            game.getPreviousGuesses(true);
            if (won) {
                System.out.println("You Won!");
            }
            if (i == guesses - 1) {
                System.out.println("You Lost!");
            }
        }



        long afterUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        long actualMemUsed=afterUsedMem-beforeUsedMem;
        System.out.println("Memory used: " + (actualMemUsed/ 1024) + " KB");



    }
}
