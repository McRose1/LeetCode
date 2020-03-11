/*
    Given an integer, write a function to determine if it is a power of two.

    Example 1:
    Input: 1
    Output: true

    Example 2:
    Input: 16
    Output: true

    Example 3:
    Input: 218
    Output: false
 */

public class PowerofTwo {
    public boolean isPowerOfTwo(int n) {
        if ((n & (n - 1)) == 0 && n > 0) return true;
        return false;
    }

}
