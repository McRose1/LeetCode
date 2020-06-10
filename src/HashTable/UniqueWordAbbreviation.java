package HashTable;

import java.util.HashMap;

/*  288. Unique Word Abbreviation
    An abbreviation of a word follows the form <first letter><number><last letter>.
    Below are some examples of word abbreviations:
    a) it                      --> it    (no abbreviation)

         1
         ↓
    b) d|o|g                   --> d1g

                  1    1  1
         1---5----0----5--8
         ↓   ↓    ↓    ↓  ↓
    c) i|nternationalizatio|n  --> i18n

                  1
         1---5----0
         ↓   ↓    ↓
    d) l|ocalizatio|n          --> l10n

    Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary.
    A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.

    Example:
    Given dictionary = [ "deer", "door", "cake", "card" ]

    isUnique("dear") -> false
    isUnique("cart") -> true
    isUnique("cane") -> false
    isUnique("make") -> true
 */
//  HashMap: Time = O(n) Space = O(n)
public class UniqueWordAbbreviation {
    HashMap<String, String> map;
    /*
     * @param dictionary: a list of words
     */
    public UniqueWordAbbreviation(String[] dictionary) {
        map = new HashMap<>();
        for (String s : dictionary) {
            String abbr = getAbbr(s);
            if (map.containsKey(abbr)) {
                if (!map.get(abbr).equals(s)) {
                    map.put(abbr, "");
                }
            } else {
                map.put(abbr, s);
            }
        }
    }
    /*
     * @param word: a string
     * @return: true if its abbreviation is unique or false
     */
    public boolean isUnique(String word) {
        String abbr = getAbbr(word);
        // 如果 map 不存在该 key，或者用该 key 能找到唯一的 value 和 word 相等，就 return true
        return !map.containsKey(abbr) || map.get(abbr).equals(word);
    }

    private String getAbbr(String s) {
        StringBuilder sb = new StringBuilder();
        // 长度大于 2 才用压缩
        if (s.length() > 2) {
            sb.append(s.charAt(0));
            sb.append((s.length() - 2));
            sb.append(s.charAt(s.length() - 1));
            return sb.toString();
        } else {
            return s;
        }
    }
}
