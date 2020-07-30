package HashTable;

/*  336. Palindrome Pairs
    Given a list of unique words, find all of distinct indices (i, j) in the given list, so that the concatenation of the two words,
    i.e. words[i] + words[j] is a palindrome.

    Example 1:
    Input: ["abcd","dcba","lls","s","sssll"]
    Output: [[0,1],[1,0],[3,2],[2,4]]
    Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]

    Example 2:
    Input: ["bat","tab","cat"]
    Output: [[0,1],[1,0]]
    Explanation: The palindromes are ["battab","tabbat"]
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*  HashMap: Time = O(k^2 * n) Space = O(n^2)
            Word1   Word2
    Case 1:  CAT  +  TAC

           Word1      Word2
    Case 2: CAT  +   SOLOSTAC

             Word1      Word2
    Case 3: CATSOLOS  +  TAC
 */
public class PalindromePairs {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        if (words == null || words.length == 0) return res;

        // Build a word -> original index mapping for efficient lookup
        HashMap<String, Integer> wordSet = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            wordSet.put(words[i], i);
        }

        for (String word : wordSet.keySet()) {
            int curWordIdx = wordSet.get(word);
            // 这一步非常妙，有点反推的味道
            String reversedWord = new StringBuilder(word).reverse().toString();

            // Build results of case #1: This word will be word 1.
            if (wordSet.containsKey(reversedWord) && wordSet.get(reversedWord) != curWordIdx) {
                res.add(Arrays.asList(curWordIdx, wordSet.get(reversedWord)));
            }

            // Build results of case #2: This word will be word 2.
            for (String suffix : allValidSuffixes(word)) {
                String reversedSuffix = new StringBuilder(suffix).reverse().toString();
                if (wordSet.containsKey(reversedSuffix)) {
                    res.add(Arrays.asList(wordSet.get(reversedSuffix), curWordIdx));
                }
            }

            // Build results of case #3: This word will be word 1.
            for (String prefix : allValidPrefixes(word)) {
                String reversedPrefix = new StringBuilder(prefix).reverse().toString();
                if (wordSet.containsKey(reversedPrefix)) {
                    res.add(Arrays.asList(curWordIdx, wordSet.get(reversedPrefix)));
                }
            }
        }
        return res;
    }

    // 在前缀找到所有回文子串
    private List<String> allValidPrefixes(String word) {
        List<String> validPrefixes = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            if (isPalindromeBetween(word, i, word.length() - 1)) {
                validPrefixes.add(word.substring(0, i));
            }
        }
        return validPrefixes;
    }

    // 在后缀找到所有回文子串
    private List<String> allValidSuffixes(String word) {
        List<String> validSuffixes = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            if (isPalindromeBetween(word, 0, i)) {
                validSuffixes.add(word.substring(i + 1));
            }
        }
        return validSuffixes;
    }

    private boolean isPalindromeBetween(String word, int left, int right) {
        while (left < right) {
            if (word.charAt(left) != word.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}

/*  Brute Force: Time = O(n^2 * k) Space = O(n^2)

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        if (words == null || words.length == 0) return res;

        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if (i == j) continue;

                // base case: 处理字符串为 "" 的情况
                if (words[i].length() == 0) {
                    if (isPalindrome(words[j])) {
                        res.add(Arrays.asList(i, j));
                    }
                    continue;
                } else if (words[j].length() == 0) {
                    if (isPalindrome(words[i])) {
                        res.add(Arrays.asList(i, j));
                    }
                    continue;
                }
                // 不加这句会 TLE
                if (words[i].charAt(0) != words[j].charAt(words[j].length() - 1)) continue;

                String cur = words[i] + words[j];
                if (isPalindrome(cur)) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }
        return res;
    }

    private boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                break;
            }
        }
        return left >= right;
    }
 */