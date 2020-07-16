package Backtracking;

/*  17. Letter Combinations of a Phone Number
    Given a string containing digits from 2-9 inclusive,
    return all possible letter combinations that the number could represent.
    A mapping of digit to letters (just like on the telephone buttons)
    Note that 1 does not map to any letters.

    Example:
    Input: "23"
    Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

    Note:
    Although the above answer is in lexicographical order, your answer could be in any order you want.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*  Backtracking: Time = O(branch factor ^ depth) < O(4^n) Space = O(n)
    dfs recursion tree:
    How many levels of recursion tree?  n levels. n is string length.
    What does each level represent?     Each level represent the corresponding possible character of s[level]
 */
public class LetterCombinationsofaPhoneNumber {
    Map<Character, String> map = new HashMap<>() {{
        put('2', "abc");
        put('3', "def");
        put('4', "ghi");
        put('5', "jkl");
        put('6', "mno");
        put('7', "pqrs");
        put('8', "tuv");
        put('9', "wxyz");
    }};

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        // corner case
        if (digits == null || digits.length() == 0) {
            return res;
        }
        StringBuilder sb = new StringBuilder();
        dfs(digits, 0, sb, res);
        return res;
    }

    public void dfs(String digits, int index, StringBuilder sb, List<String> res) {
        // base case
        // if there is no more digits to check
        if (index == digits.length()) {
            // the combination is done
            res.add(sb.toString());
            return;
        }
        // recursive rule
        // if there are still digits to check, iterate over all letters which map the next available digit
        String values = map.get(digits.charAt(index));
        for (char c : values.toCharArray()) {
            sb.append(c);
            dfs(digits, index + 1, sb, res);
            // backtrack
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}

/*  DFS: Time = O(3^N x 4^M) Space = O(3^N x 4^M)

    Map<String, String> phone = new HashMap<>() {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};

    List<String> output = new ArrayList<String>();

    public void dfs(String combination, String next_digits) {
        // if there is no more digits to check
        if (next_digits.length() == 0) {
            // the combination is done
            output.add(combination);
        }
        // if there are still digits to check
        else {
            // iterate over all letters which map the next available digit
            String digit = next_digits.substring(0, 1);
            String letters = phone.get(digit);
            for (int i = 0; i < letters.length(); i++) {
                String letter = phone.get(digit).substring(i, i + 1);
                // append the current letter to the combination and proceed to the next digits
                dfs(combination + letter, next_digits.substring(1));
            }
        }
    }

    public List<String> letterCombinations(String digits) {
        if (digits.length() != 0) {
            dfs("", digits);
        }
        return output;
    }
 */