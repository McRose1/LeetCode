package String;

/*  293. Flip Game
    You are playing the following Flip Game with your friend: Given a string that contains only these two characters:
    + and -, you and your friend takes turns to flip two consecutive "++" into "--".
    The game ends when a person can no longer make a move and therefore the other person will be the winner.

    Write a function to compute all possible states of the string after one valid move.

    Example:
    Input: s = "++++"
    Output:
    [
      "--++"
      "+--+"
      "++--"
    ]

    Note: If there is no valid move, return an empty list [].
 */

//  Substring: Time = O(n) Space = O(n)
import java.util.ArrayList;
import java.util.List;

public class FlipGame {
    public List<String> generatePossibleNextMoves(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return res;
        }
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '+' && s.charAt(i - 1) == '+') {
                res.add(s.substring(0, i - 1) + "--" + s.substring(i + 1));
            }
        }
        return res;
    }
}
