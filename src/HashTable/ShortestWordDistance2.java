package HashTable;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/*  HashMap + Two Pointers: Time = O(n) Space = O(n)
    Multiple query with unlimited times -> Pre-computation: go through the array to build invertedIndexMap
 */
public class ShortestWordDistance2 {
    private HashMap<String, List<Integer>> map = new HashMap<>();

    // 预处理，拿到两个目标单词的 index list
    public ShortestWordDistance2(String[] words) {
        for (int i = 0; i < words.length; i++) {
            if (map.containsKey(words[i])) {
                // 加入新的 index
                map.get(words[i]).add(i);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(words[i], list);
            }
        }
    }
    // Two Pointers
    public int shortest(String word1, String word2) {
        List<Integer> l1 = map.get(word1);
        List<Integer> l2 = map.get(word2);
        int res = Integer.MAX_VALUE;
        int i = 0, j = 0;
        while (i < l1.size() && j < l2.size()) {
            res = Math.min(res, Math.abs(l1.get(i) - l2.get(j)));
            // 如果 word1 的 index 小于 word2 的 index，就让 word1 的 index 增大
            if (l1.get(i) < l2.get(j)) {
                i++;
            } else {
                j++;
            }
        }
        return res;
    }
}
