import java.util.*;

public class PlagiarismDetector {

    private HashMap<String, Set<String>> ngramIndex = new HashMap<>();

    private List<String> generateNGrams(String text, int n) {

        String[] words = text.split(" ");
        List<String> ngrams = new ArrayList<>();

        for (int i = 0; i <= words.length - n; i++) {

            StringBuilder gram = new StringBuilder();

            for (int j = 0; j < n; j++) {
                gram.append(words[i + j]).append(" ");
            }

            ngrams.add(gram.toString().trim());
        }

        return ngrams;
    }

    public void addDocument(String docId, String text) {

        List<String> grams = generateNGrams(text, 3);

        for (String gram : grams) {

            ngramIndex.putIfAbsent(gram, new HashSet<>());
            ngramIndex.get(gram).add(docId);
        }
    }

    public void analyzeDocument(String docId, String text) {

        List<String> grams = generateNGrams(text, 3);

        HashMap<String, Integer> matchCount = new HashMap<>();

        for (String gram : grams) {

            if (ngramIndex.containsKey(gram)) {

                for (String existingDoc : ngramIndex.get(gram)) {

                    matchCount.put(existingDoc,
                            matchCount.getOrDefault(existingDoc, 0) + 1);
                }
            }
        }

        System.out.println("Analysis for " + docId);

        for (String doc : matchCount.keySet()) {

            int matches = matchCount.get(doc);

            double similarity = (matches * 100.0) / grams.size();

            System.out.println("Matches with " + doc + " → " + similarity + "% similarity");
        }
    }

    public static void main(String[] args) {

        PlagiarismDetector detector = new PlagiarismDetector();

        detector.addDocument("doc1",
                "data structures and algorithms are important for programming");

        detector.addDocument("doc2",
                "machine learning and artificial intelligence are important fields");

        detector.analyzeDocument("doc3",
                "data structures and algorithms are important topics");
    }
}