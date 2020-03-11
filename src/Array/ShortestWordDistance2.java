package Array;

/*  244. Shortest Word Distance 2

    Design a class which receives a list of words in the constructor,
    and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list.
    You method will be called repeatedly many times with different parameters.

    Example:
    Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
    Input: word1 = "coding", word2 = "practice"
    Output: 3
    Input: word1 = "makes", word2 = "coding"
    Output: 1

    Note: You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 */

/*
    Multiple query with unlimited times -> Pre-computation: go through the array to build invertedIndexMap
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//  Time = O(n)
public class ShortestWordDistance2 {
    private HashMap<String, List<Integer>> map;

    public ShortestWordDistance2(String[] words) {
        map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            if (map.containsKey(words[i])) {
                map.get(words[i]).add(i);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(words[i], list);
            }
        }
    }
    public int shortest(String word1, String word2) {
        List<Integer> l1 = map.get(word1);
        List<Integer> l2 = map.get(word2);
        int res = Integer.MAX_VALUE;
        int i = 0, j = 0;
        while (i < l1.size() && j < l2.size()) {
            res = Math.min(res, Math.abs(l1.get(i) - l2.get(j)));
            if (l1.get(i) < l2.get(j)) {
                i++;
            } else {
                j++;
            }
        }
        return res;
    }
}
