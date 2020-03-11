package BitManipulation;

/*  371. Sum of Two Integers
    Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.

    Example 1:
    Input: a = 1, b = 2
    Output: 3

    Example 2:
    Input: a = -2, b = 3
    Output: 1
 */
/*
    0 + 0 = 00  0 ^ 0
    1 + 0 = 01  1 ^ 0
    0 + 1 = 01  0 ^ 1
    1 + 1 = 10  (1 & 1) << 1
    sum(5, 6) -> sum(3, 8) -> ... -> sum(n, 0) 此时的n就是我们要的和
 */
public class SumofTwoIntegers {
    public int getSum(int a, int b) {
        // corner case
        if (a == 0) return b;
        if (b == 0) return a;

        while (b != 0) {
            // carry
            int carry = a & b;      // 01 & 10 = 00 -> no carry
            // sum without adding carry
            a = a ^ b;              // a = 01 ^ 10 = 11
            // carry to add in the next iteration
            b = carry << 1;         // b = 0
        }
        return a;
    }
}