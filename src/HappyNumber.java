/*
    Write an algorithm to determine if a number is "happy".

    A happy number is a number defined by the following process:
    Starting with any positive integer, replace the number by the sum of the squares of its digits,
    and repeat the process until the number equals 1 (where it will stay),
    or it loops endlessly in a cycle which does not include 1.
    Those numbers for which this process ends in 1 are happy numbers.

    Example:
    Input: 19
    Output: true
    1^2 + 9^2 = 82
    8^2 + 2^2 = 68
    6^2 + 8^2 = 100
    1^2 + 0^2 + 0^2 = 1
 */

import java.util.HashSet;

public class HappyNumber {
    public boolean isHappy(int n) {
        HashSet<Integer> seen = new HashSet<Integer>();
        while (seen.add(n)) {
            int current = n;
            int sum = 0;
            while (current != 0) {
                sum += (current % 10) * (current % 10);
                current /= 10;
            }
            if (n == 1) {
                return true;
            }
            n = sum;
        }
        return false;
    }
}
/*
    HashSet:
    boolean add(E e): 如果当前列表中不存在 e，则将 e 加入列表，否则 return false
 */
