import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CompoundedWordsFinder {

    public static void main(String[] args) {
        long StartTime = System.currentTimeMillis();

        
        List<String> words1 = readWordsFromFile("Input_01.txt");
        List<String> words2 = readWordsFromFile("Input_02.txt");

        // Combine the lists and remove duplicate
        Set<String> allWord = new HashSet<>(words1);
        allWord.addAll(words2);
        List<String> allWords = new ArrayList<>(allWord);


        List<String> compoundedWords = findCompoundedWords(allWords);

        // Sort compounded words by length
        compoundedWords.sort((a, b) -> Integer.compare(b.length(), a.length()));


        String longestCompoundedWord = compoundedWords.size() > 0 ? compoundedWords.get(0) : null;
        String secondLongestCompoundedWord = compoundedWords.size() > 1 ? compoundedWords.get(1) : null;

        // Calculate time taken
        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - StartTime;

        // Display results
        System.out.println("Longest compounded word: " + longestCompoundedWord);
        System.out.println("Second longest compounded word: " + secondLongestCompoundedWord);
        System.out.println("Time taken to process the input files: " + timeTaken + " milliseconds");
    }

    private static List<String> readWordsFromFile(String filePath) {
        List<String> words = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                words.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    private static List<String> findCompoundedWords(List<String> words) {
        Set<String> wordSet = new HashSet<>(words);
        List<String> compoundedWords = new ArrayList<>();

        for (String word : words) {
            if (isCompounded(word, wordSet)) {
                compoundedWords.add(word);
            }
        }
        return compoundedWords;
    }

    private static boolean isCompounded(String word, Set<String> wordSet) {
        for (int i = 1; i < word.length(); i++) {
            String prefix = word.substring(0, i);
            String suffix = word.substring(i);
            if (wordSet.contains(prefix) && wordSet.contains(suffix)) {
                return true;
            }
        }
        return false;
    }
}