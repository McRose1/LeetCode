package HashTable;

/*  202. Happy Number
    Write an algorithm to determine if a number is "happy".

    A happy number is a number defined by the following process:
    Starting with any positive integer, replace the number by the sum of the squares of its digits,
    and repeat the process until the number equals 1 (where it will stay),
    or it loops endlessly in a cycle which does not include 1.
    Those numbers for which this process ends in 1 are happy numbers.

    Example:
    Input: 19
    Output: true
    Explanation:
    1^2 + 9^2 = 82
    8^2 + 2^2 = 68
    6^2 + 8^2 = 100
    1^2 + 0^2 + 0^2 = 1
 */

import java.util.HashSet;
/*  HashSet:
    只要检测是否会进入循环，如果有循环存在，那肯定是 false
    116->38->73->58->89->145->42->20->4->16->37->58
                 ^                                ^
 */
public class HappyNumber {
    public boolean isHappy(int n) {
        HashSet<Integer> seen = new HashSet<Integer>();
        // 判断是否存在循环
        while (seen.add(n)) {
            int current = n;
            int sum = 0;
            while (current != 0) {
                sum += (current % 10) * (current % 10);
                current /= 10;
            }
            if (sum == 1) {
                return true;
            } else {
                n = sum;
            }
        }
        return false;
    }
}

