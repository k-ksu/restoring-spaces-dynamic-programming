

//Ksenia Korchgina
    import java.util.Scanner;
    import java.util.ArrayList;
    import java.util.Arrays;
    import java.util.List;
    import java.util.HashSet;
    import java.util.Set;


    public class Main {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            // Input
            int N = scanner.nextInt(); // Number of words in the dictionary
            int K = scanner.nextInt(); // Number of symbols in text
            scanner.nextLine(); // Consume newline

            // Reading words into an array for faster lookup
            String[] words = scanner.nextLine().split(" ");

            // Reading corrupted text
            String text = scanner.nextLine();

            // Solve using Dynamic Programming
            List<String> result = reconstructText(words, text, K);

            // Printing the result
            for (String word : result) {
                System.out.print(word + " ");
            }
        }

        public static List<String> reconstructText(String[] words, String text, int K) {
            // Create a set for faster word lookup
            Set<String> wordSet = new HashSet<>(Arrays.asList(words));

            // Store the previous valid word index for each index in the text
            int[] prevWordIndex = new int[K + 1];

            // Initialize prevWordIndex array without using Arrays.fill
            for (int i = 0; i < prevWordIndex.length; i++) {
                prevWordIndex[i] = -1;
            }

            // Set the initial value for the first character
            prevWordIndex[0] = 0;

            // Find all valid words in the text
            for (int i = 0; i <= K; i++) {
                if (prevWordIndex[i] != -1) {
                    for (String word : words) {
                        int end = i + word.length();
                        if (end <= K && text.substring(i, end).equals(word)) {
                            prevWordIndex[end] = i;
                        }
                    }
                }
            }

            // Reconstruct the text
            List<String> reconstructedText = new ArrayList<>();
            int currentIndex = K;
            while (currentIndex > 0) {
                int prevIndex = prevWordIndex[currentIndex];
                reconstructedText.add(0, text.substring(prevIndex, currentIndex));
                currentIndex = prevIndex;
            }

            return reconstructedText;
        }
    }