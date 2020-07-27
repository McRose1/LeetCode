package BitManipulation;

/*  318. Maximum Product of Word Lengths
    Given a string array words, find the maximum value of length(word[i]) * length(word[j])
    where the two words do not share common letters.
    You may assume that each word will contain only lower case letters.
    If no such two word exist, return 0.

    Example 1:
    Input: ["abcw","baz","foo","bar","xtfn","abcdef"]
    Output: 16
    Explanation: The two words can be "abcw", "xtfn".

    Example 2:
    Input: ["a","ab","abc","d","cd","bcd","abcd"]
    Output: 4
    Explanation: The two words can be "ab", "cd".

    Example 3:
    Input: ["a","aa","aaa","aaaa"]
    Output: 0
    Explanation: No such pair of words.
 */

/*  Bit Manipulation: Time = O(n^2) Space = O(n)
    通过（位操作+预计算）来优化比较过程（noCommonLetters 函数）
 */
public class MaximumProductofWordLengths {
    public int maxProduct(String[] words) {
        if (words == null || words.length == 0) return 0;
        int n = words.length;
        // we only need 26 bits for every String, int is 32 bits so it's enough
        int[] masks = new int[n];

        // 遍历 words 字符串数组，为每一个字符串生成专属的 bitmask
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                // 26 bits to mark the letter，本题的精华！！！
                masks[i] |= 1 << (words[i].charAt(j) - 'a');
            }
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // 没有共同的字母
                if ((masks[i] & masks[j]) == 0) {
                    max = Math.max(max, words[i].length() * words[j].length());
                }
            }
        }
        return max;
    }
}

/*  +HashMap (优化比较次数)
    这种方法单词比较次数可能会减少，从而提高 Python 解法的效率。由于 Java 中 HashMap 的性能问题，这种方法不会改善 Java 解法的效率 (LC 上反而更慢)。
    一些单词具有相同的字符集，则只保留这些单词中最长的一个单词即可。例如：(ab, aaaaabaabaaabb, bbabbabba) 只保留aaaaabaabaaabb 即可。
    使用 HashMap 代替方法一中长度为 n 的数组（masks），存储结构为：<bitmask, 该 bitmask 对应的 max_length>

        if (words == null || words.length == 0) return 0;
        int n = words.length;

        HashMap<Integer, Integer> map = new HashMap<>();

        for (String word : words) {
            int bitmask = 0;
            for (char c : word.toCharArray()) {
                bitmask |= 1 << (c - 'a');
            }
            // there could be different words with the same bitmask, e.g. ab and aabb
            map.put(bitmask, Math.max(map.getOrDefault(bitmask, 0), word.length()));
        }

        int max = 0;
        for (int x : map.keySet()) {
            for (int y : map.keySet()) {
                if ((x & y) == 0) {
                    max = Math.max(max, map.get(x) * map.get(y));
                }
            }
        }
        return max;
 */


/*  Brute Force

    public int maxProduct(String[] words) {
        int n = words.length;

        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (noCommonLetters(words[i], words[j])) {
                    max = Math.max(max, words[i].length() * words[j].length());
                }
            }
        }
        return max;
    }

    private boolean noCommonLetters(String s1, String s2) {
        for (char c : s1.toCharArray()) {
            if (s2.indexOf(c) != -1) {
                return false;
            }
        }
        return true;
    }

    private boolean
 */