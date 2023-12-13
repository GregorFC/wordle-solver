import java.util.ArrayList;

public class Guesser {


    public static void main(String[] args) {
        int wins = 0;
        long totalTime = 0;
        int tries = 100;

        for (int i = 0; i < tries; i++) {
            Game game = new Game(5, 10);
            long begin = System.currentTimeMillis();
            if (bruteForce(game,5, 10, false)) wins++;
            long end = System.currentTimeMillis();
            totalTime += (end - begin);
        }

        System.out.println();
        System.out.println("Average time: " + (totalTime / tries) + " milliseconds");
        System.out.println("Wins: " + wins);
    }


    public static boolean bruteForce(Game game, int wordLength, int guesses, boolean print) {
        ArrayList<String> availableWords = Setup.readWords(wordLength);
        String guess = Setup.randomWord(wordLength);
        boolean[] correct = new boolean[wordLength];

        for (int i = 0; i < guesses; i++) {
            if (print) System.out.println("Guess: " + guess);
            game.guess(guess);
            if (game.isWon) {
                if (print) System.out.println("You Won!");
                return true;
            }
            char[] prev = game.getPreviousGuesses(false);
            // Remove words that don't match previous guess
            // Only with regards to correct chars
            for (int index = 0; index < wordLength; index++) {
                ArrayList<String> toRemove = new ArrayList<>();
                if (prev[index] == 'C') {
                    int finalIndex = index;
                    correct[finalIndex] = true;
                    char finalChar = guess.charAt(finalIndex);
                    availableWords.stream().filter(word -> word.charAt(finalIndex) != finalChar).forEach(toRemove::add);
                }
                availableWords.removeAll(toRemove);
            }
            // Remove words that don't match previous guess
            // With wrong or misplaced chars
            for (int index = 0; index < wordLength; index++) {
                ArrayList<String> toRemove = new ArrayList<>();
                if (prev[index] == 'M') {
                    int finalIndex = index;
                    CharSequence finalChar = guess.substring(finalIndex, finalIndex + 1);
                    availableWords.stream().filter(word -> !word.contains(finalChar)).forEach(toRemove::add);
                }
                if (prev[index] == 'X') {
                    for (String word : availableWords) {
                        if (word.charAt(index) == guess.charAt(index)) {
                            toRemove.add(word);
                        }
                    }
                }
                availableWords.removeAll(toRemove);
            }
            // If there is only one word left, guess it
            // Average time: 678 milliseconds
            // Wins: 67
//            guess = availableWords.get(0);

            // Average time: 670 milliseconds
            // Wins: 87
            // Random guess from the remaining words
            guess = availableWords.get((int) (Math.random() * availableWords.size()));

        }
        return false;

    }

}
