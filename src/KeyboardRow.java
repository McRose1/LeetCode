/*
    Given a List of words, return the words that can be typed using letters of alphabet
    on only one row's of American keyboard.

    Example:
    Input: ["Hello", "Alaska", "Dad", "Peace"]
    Output: ["Alaska", "Dad"]

    Note:
        You may use one character in the keyboard more than once.
        You may assume the input string will only contain letters of alphabet.
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class KeyboardRow {
    public String[] findWords(String[] words) {
        String[] rows = {"QWERTYUIOP", "ASDFGHJKL", "ZXCVBNM"};
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < rows.length; i++) {
            for (char c : rows[i].toCharArray()) {
                map.put(c, i);
            }
        }
        List<String> res = new LinkedList<>();
        for (String w : words) {
            if (w.equals("")) continue;
            int index = map.get(w.toUpperCase().charAt(0)); // decide which row the string belongs to
            for (char c : w.toUpperCase().toCharArray()) {
                if (map.get(c) != index) {
                    index = -1;
                    break;
                }
            }
            if (index != -1) {
                res.add(w);
            }
        }
        return res.toArray(new String[0]);  // LinkedList<String> -> String[]
    }
}
