package String;

/*
    246. Strobogrammatic Number
    A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
    Write a function to determine if a number is strobogrammatic.

    Example 1:
    Input: "69"
    Output: true

    Example 2:
    Input: "88"
    Output: true

    Example 3:
    Input: "962"
    Output: false
 */

//  Two Pointers + HashMap
import java.util.HashMap;

public class StrobogrammaticNumber {
    public boolean isStrobogrammatic(String num) {
        HashMap<Character, Character> map = new HashMap<>();
        map.put('6', '9');
        map.put('9', '6');
        map.put('0', '0');
        map.put('1', '1');
        map.put('8', '8');
        int left = 0;
        int right = num.length() - 1;
        while (left <= right) {
            if (!map.containsKey(num.charAt(left)) || map.get(num.charAt(left)) != num.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
