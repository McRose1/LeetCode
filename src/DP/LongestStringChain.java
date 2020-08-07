package DP;

/* 1048. Longest String Chain
    Given a list of words, each word consists of English lowercase letters.
    Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere in word1 to make it equal to word2.
    For example, "abc" is a predecessor of "abac".
    A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, where word_1 is a predecessor of word_2. word_2 is a predecessor of word_3, and so on.
    Return the longest possible length of a word chain with words chosen from the given list of words.

    Example:
    Input: ["a","b","ba","bca","bda","bdca"]
    Output: 4
    Explanation: one of the longest word chain is "a","ba","bda","bdca".

    Note:
    1. 1 <= words.length <= 1000
    2. 1 <= words[i].length <= 16
    3. words[i] only consists of English lowercase letters.

    Hint:
    1. Instead of adding a character, try deleting a character to from a chain in reverse.
    2. For each word in order of length, for each word2 which is word with one character removed, length[word2] = max(length[word2], length[word] + 1).
 */

import java.util.Arrays;

public class LongestStringChain {
    public int longestStrChain(String[] words) {
        Arrays.sort(words, ((o1, o2) -> o1.length() - o2.length()));
        int n = words.length;
        int[] dp = new int[n];
        int res = 0;
        for (int i = 0; i < n; i++) {
            String a = words[i];
            for (int j = i + 1; j < n; j++) {
                String b = words[j];
                if (isPredecessor(a, b)) {
                    dp[j] = Math.max(dp[j], dp[i] + 1);
                    res = Math.max(res, dp[j]);
                }
            }
        }
        return res + 1;
    }

    private boolean isPredecessor(String a, String b) {
        int i = 0, j = 0;
        int m = a.length(), n = b.length();
        if ((m + 1) != n) {
            return false;
        }
        while (i < m && j < n) {
            if (a.charAt(i) == b.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == m;
    }
}

/*  Optimized DP

    public int longestStrChain(String[] words) {
        Arrays.sort(words, ((o1, o2) -> o1.length() - o2.length()));
        int n = words.length;
        int[] arr = new int[17];
        for (int i = 0; i < n; i++) {
            arr[words[i].length()] = i;
        }
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int res = 0;
        for (int i = 0; i < n; i++) {
            int target = words[i].length() - 1;
            int index = arr[target];
            while (index >= 0 && words[index].length() == target) {
                if (isPredecessor(words[index], words[i])) {
                    dp[i] = Math.max(dp[i], dp[index] + 1);
                }
                index--;
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    private boolean isPredecessor(String a, String b) {
        int i = 0, j = 0;
        int m = a.length(), n = b.length();
        if ((m + 1) != n) {
            return false;
        }
        while (i < m && j < n) {
            if (a.charAt(i) == b.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == m;
    }
 */