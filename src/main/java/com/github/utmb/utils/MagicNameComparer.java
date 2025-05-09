package com.github.utmb.utils;

import org.apache.commons.text.similarity.LevenshteinDistance;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MagicNameComparer {

    private static final double MIN_SIMILARITY = 0.80;

    public static boolean areNamesEquivalent(String name1, String name2) {
        List<String> words1 = normalizeAndSort(name1);
        List<String> words2 = normalizeAndSort(name2);

        if (words1.size() != words2.size()) {
            return false;
        }

        // Check fuzzy match word-by-word after sorting
        for (int i = 0; i < words1.size(); i++) {
            if (!isSimilarEnough(words1.get(i), words2.get(i))) {
                return false;
            }
        }

        return true;
    }

    private static List<String> normalizeAndSort(String name) {
        return Arrays.stream(stripAccents(name.trim().toLowerCase()).split("\\s+"))
                .sorted()
                .collect(Collectors.toList());
    }

    private static String stripAccents(String input) {
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .replaceAll("[^\\p{ASCII}]", "");
    }

    private static boolean isSimilarEnough(String word1, String word2) {
        LevenshteinDistance ld = new LevenshteinDistance();
        int distance = ld.apply(word1, word2);
        int maxLength = Math.max(word1.length(), word2.length());

        if (maxLength == 0) return true;

        double similarity = 1.0 - ((double) distance / maxLength);
        return similarity >= MIN_SIMILARITY;
    }
}
