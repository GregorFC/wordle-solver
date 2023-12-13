import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Setup {

    public static String goal(int wordLength) {
        ArrayList<String> words = readWords(wordLength);
        int index = (int) (Math.random() * words.size());
        return words.get(index);

    }

    public static ArrayList<String> readWords(int wordLength) {
        ArrayList<String> words = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("library/words_alpha.txt"));
            String line = br.readLine();
            while (line != null) {
                if (line.length() == wordLength) {
                    words.add(line);
                }
                line = br.readLine();
            }
        } catch (Exception e) {
            System.out.println("Error reading file");
        }

        return words;
    }
}
