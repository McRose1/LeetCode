package Backtracking;

/*  320. Generalized Abbreviation
    Write a function to generate the generalized abbreviations of a word.

    Note: The order of the input does not matter.

    Example:
    Input: "word"
    Output:
    ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 */

import java.util.ArrayList;
import java.util.List;
/*  Backtracking
    When ew go through the word and check every character, in any character, we can do two things:
    1. Abbreviate this character.
    2. Do not abbreviate this character.
    In the first case, we just pass the current states to the next recursion.
    In the second case, we need to add the previously abbreviated count to the current path, with the current character,
    and pass it to the next recursion.
 */
public class GeneralizedAbbreviation {
    public List<String> generateAbbreviations(String word) {
        List<String> res = new ArrayList<>();
        backtrack(res, word, 0, "", 0);
        return res;
    }

    private void backtrack(List<String> res, String word, int pos, String cur, int count) {
        if (pos == word.length()) {
            if (count > 0) cur += count;
            res.add(cur);
        } else {
            // abbreviate this character
            backtrack(res, word, pos + 1, cur, count + 1);
            if (count > 0) {
                cur = cur + count + word.charAt(pos);
            } else {
                cur = cur + word.charAt(pos);
            }
            // do not abbreviate this character
            backtrack(res, word, pos + 1, cur, 0);
        }
    }
}
