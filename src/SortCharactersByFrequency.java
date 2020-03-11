/*
    Given a string, sort it in decreasing order based on the frequency of characters.

    Example 1:
    Input:
    "tree"
    Output:
    "eert"

    Example 2:
    Input:
    "cccaaa"
    Output:
    "cccaaa"

    Example 3:
    Input:
    "Aabb"
    Output:
    "bbAa"
 */
// HashMap + PriorityQueue + String
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class SortCharactersByFrequency {
    public String frequency(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {    // String -> Char[]
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Character> maxHeap = new PriorityQueue<>((a, b) -> map.get(b) - map.get(a));  // comparator
        maxHeap.addAll(map.keySet());

        StringBuilder res = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            char cur = maxHeap.remove();    // remove the first element of priority queue, which in maxHeap is the max
            for (int i = 0; i < map.get(cur); i++) {
                res.append(cur);
            }
        }
        return res.toString();
    }
}
/*
    Bucket Sort

        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);

        List<Character> [] bucket = new List[s.length() + 1];
        for (char key : map.keySet()) {
            int frequency = map.get(key);
            if (bucket[frequency] == null) bucket[frequency] = new ArrayList<>();
            bucket[frequency].add(key);
        }

        StringBuilder sb = new StringBuilder();
        for (int pos = bucket.length - 1; pos >= 0; pos--)
            if (bucket[pos] != null)
                for (char c : bucket[pos])
                    for (int i = 0; i < map.get(c); i++)
                        sb.append(c);

        return sb.toString();
 */
