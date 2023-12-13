import java.util.Scanner;

public class Application {

    public static void main(String... args) {
        int guesses = 10;
        Game game = new Game(4, guesses);
        for (int i = 0; i < guesses; i++) {
            // ask user for input
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your guess: ");
            String guess = scanner.nextLine();
            // check if input is valid
            boolean won = game.guess(guess);
            game.printPreviousGuesses();
            if (won) {
                System.out.println("You Won!");
                return;
            }
        }

        System.out.println("You Lost!");



    }
}
