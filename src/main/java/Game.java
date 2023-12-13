import java.util.Arrays;

public class Game {

    private String goal;
    private int wordLength;
    private int guesses;
    private int currentGuess;
    public char[][] previousGuesses;
    public String[] guessWords;
    public boolean isWon = false;

    public Game(int wordLength, int guesses) {
        this.wordLength = wordLength;
        this.guesses = guesses;
        this.goal = Setup.randomWord(wordLength);
        this.currentGuess = 0;
        this.previousGuesses = new char[guesses][wordLength];
        this.guessWords = new String[guesses];
    }

    public boolean guess(String guess) {
        if (guess.length() != wordLength) {
            System.err.println("Guess must be of length " + wordLength);
            return false;
        }
        if (guesses == currentGuess) {
            System.out.println("You Lost! The word was: " + goal);
            return false;
        }

        guessWords[currentGuess] = guess;
        int correct = 0;
        char[] goalChars = goal.toCharArray();
        // First remove all correct chars
        for (int i = 0; i < wordLength; i++) {
            if (guess.charAt(i) == goal.charAt(i)) {
                correct++;
                // remove char from goalChars
                goalChars[i] = ' ';
                previousGuesses[currentGuess][i] = 'C';
            }
        }

        for (int i = 0; i < wordLength; i++) {
            // Skip if the char is in right place
            if (previousGuesses[currentGuess][i] == 'C') continue;
            // check if char is in goal at another index
            if (Arrays.toString(goalChars).contains(guess.substring(i, i + 1))) {
                previousGuesses[currentGuess][i] = 'M';
                // remove found car from goalChars, to pretend double misplaced flag
                for (int j = 0; j < goalChars.length; j++) {
                    if (goalChars[j] == guess.charAt(i)) {
                        goalChars[j] = ' ';
                        break;
                    }
                }
            } else {
                previousGuesses[currentGuess][i] = 'X';
            }
        }
        currentGuess++;
        if (correct == wordLength) {
            isWon = true;
        }
        return isWon;
    }

    public char[] getPreviousGuesses(boolean print) {
        if (print) {
            for (char[] x : previousGuesses) {
                for (char y : x) {
                    System.out.print(y + " ");
                }
                System.out.println();
            }
        }
        return previousGuesses[this.currentGuess - 1];
    }


}
