package String;

/*  294. Flip Game 2
    You are playing the following Flip Game with your friend: Given a string that contains only these two characters:
    + and -, you and your friend takes turns to flip two consecutive "++" into "--".
    The game ends when a person can no longer make a move and therefore the other person will be the winner.

    Write a function to determine if the starting player can guarantee a win.

    Example:
    Input: s = "++++"
    Output: true
    Explanation: The starting player can guarantee a win by flipping the middle "++" to become "+--+".

    Follow up: Derive your algorithm's runtime complexity.
 */

// 递归分治，将博弈问题转化成重叠子问题
public class FlipGame2 {
    public boolean canWin(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (s.startsWith("++", i)) {
                String nextState = s.substring(0, i) + "--" + s.substring(i + 2);
                if (!canWin(nextState)) {
                    return true;
                }
            }
        }
        return false;
    }
}
