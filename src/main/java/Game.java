public class Game {

    private String goal;
    private int wordLength;
    private int guesses;
    private int currentGuess;
    public Object[][] previousGuesses;
    public String[] guessWords;

    public Game(int wordLength, int guesses) {
        this.wordLength = wordLength;
        this.guesses = guesses;
        this.goal = Setup.goal(wordLength);
        this.currentGuess = 0;
        this.previousGuesses = new Object[guesses][wordLength];
        this.guessWords = new String[guesses];
    }

    public boolean guess(String guess) {
        if (guess.length() != wordLength) {
            throw new IllegalArgumentException("Guess must be of length " + wordLength);
        }
        if (guesses == currentGuess) {
            throw new IllegalStateException("No more guesses");
        }

        guessWords[currentGuess] = guess;
        int correct = 0;

        for (int i = 0; i < wordLength; i++) {
            if (guess.charAt(i) == goal.charAt(i)) {
                correct++;
                previousGuesses[currentGuess][i] = "C";
            } else if (goal.contains(guess.substring(i, i + 1))) {
                previousGuesses[currentGuess][i] = "M";
            } else {
                previousGuesses[currentGuess][i] = "X";
            }
        }
        currentGuess++;
        return correct == wordLength;
    }

    public void printPreviousGuesses() {
        for (Object[] x : previousGuesses) {
            if (x[0] == null) return;
            for (Object y : x) {
                System.out.print(y + " ");
            }
            System.out.println();
        }
    }


}
