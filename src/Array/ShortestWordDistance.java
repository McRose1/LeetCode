package Array;

/*
    243. Shortest Word Distance

    Given a list of words and two words word1 and word2,
    return the shortest distance between these two words in the list.

    For example:
    Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
    Input: word1 = "coding", word2 = "practice"
    Output: 3
    Input: word1 = "makes", word2 = "coding"
    Output: 1

    Note: You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 */

//  Optimized 2: Tow pointers -> Time = O(n)

import java.util.List;

public class ShortestWordDistance {
    public int shortestDistance(String[] words, String word1, String word2) {
        int res = words.length;
        int a = -1;
        int b = -1;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                a = i;
            } else if (words[i].equals(word2)) {
                b = i;
            }
            if (a != -1 && b != -1) {
                res = Math.min(res, Math.abs(a - b));
            }
        }
        return res;
    }
}

/*
    Naive Solution: Time = O(n^2)
    1. Iterate array to get to sorted list.
    2. Calculate distance of all valid (a, b) pairs, get the min one.

        int res = words.length;
        for (int i = 0; i < word.length; i++) {
            if (words[i].equals(word1)) {
                for (int j = 0; j < words.length; j++) {
                    if (words[j].equals(word2)) {
                        res = Math.min(res, Math.abs(i - j));
                    }
                }
            }
        }
        return res;
 */

/*
    Optimized 1: O(mlogn)
    a, b are both sorted
    Binary Search
 */