/*
    Write a program to check whether a given number is an ugly number.

    Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.

    Example 1:
    Input: 6
    Output: true

    Example 2:
    Input: 8
    Output: true

    Example 3:
    Input: 14
    Output: false

    Note:
        1 is typically treated as an ugly number.
        Input is within the 32-bit signed integer range: [−2^31,  2^31 − 1].
 */
// num = 2^i * 3^j * 5^k
public class UglyNumber {
    public boolean isUgly(int num) {
        if (num == 0) return false;
        if (num == 1) return true;
        while (num % 2 == 0) num /= 2;
        while (num % 3 == 0) num /= 3;
        while (num % 5 == 0) num /= 5;
        return num == 1;
    }
}