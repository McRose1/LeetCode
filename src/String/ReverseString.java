package String;

/*  344. Reverse String
    Write a function that reverses a string.
    The input strings is given as an array of characters char[].
    Do not allocate extra space for another array,
    you must do this by modifying the input array in-place with O(1) extra memory.
    You may assume all the characters consist of printable ascii characters.

    Example 1:
    Input: ["h","e","l","l","o"]
    Output: ["o","l","l","e","h"]

    Example 2:
    Input: ["H","a","n","n","a","h"]
    Output: ["h","a","n","n","a","H"]

    Hint: The entire logic for reversing a string is based on using the opposite directional two-pointer approach!
 */

/*  Two Pointers + Iteration: Time = O(N) Space = O(1)
    Set pointer left at index 0, and pointer right at index n-1
 */
public class ReverseString {
    public void reverseString(char[] s) {
        int left = 0, right = s.length - 1;
        while (left < right) {
            char temp = s[left];
            s[left++] = s[right];
            s[right--] = temp;
        }
    }
}
/*
    Recursion + In-place: Time = O(N) Space = O(N)
    Base case: if (left >= right) do nothing
    Recursion rule: swap s[left] and s[right] and call helper(left + 1, right - 1)

    public void reverseString(char[] s) {
        helper(s, 0, s.length - 1);
    }
    public void helper(char[] s, int left, int right) {
        if (left >= right) return;
        char tmp = s[left];
        s[left++] = s[right];
        s[right--] = tmp;
        helper(s, left, right);
    }
 */