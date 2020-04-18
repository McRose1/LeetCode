package HashTable;

/*  249. Group Shifted Strings
    Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd".
    We can keep "shifting" which forms the sequence:
    "abc" -> "bcd" -> ... -> "xyz"
    Given a list of strings which contains only lowercase alphabets,
    group all strings that belong to the same shifting sequence.

    For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
    A solution is:
    [
      ["abc","bcd","xyz"],
      ["az","ba"],
      ["acef"],
      ["a","z"]
    ]
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
/*  HashMap: Time = O(NK) Space = O(NK)
    将每个字符串都转换成与字符串首字符 ASCII码 相差的字符串：
    "abc" -> "0","1","2", "bcd" -> "0","1","2"
    "az" -> "0","25" "ba" -> "0,"25"
 */
public class GroupShiftedStrings {
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> res = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strings) {
            int offset = str.charAt(0) - 'a';
            StringBuilder key = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                char c = (char)(str.charAt(i) - offset);
                if (c < 'a') {
                    c += 26;
                }
                key.append(c);
            }
            if (!map.containsKey(key.toString())) {
                List<String> list = new ArrayList<>();
                map.put(key.toString(), list);
            }
            map.get(key.toString()).add(str);
        }
        for (String key : map.keySet()) {
            res.add(map.get(key));
        }
        return res;
    }
}
