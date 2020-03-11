package Math;

/*  258. Add Digits
    Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.

    Example:
    Input: 38
    Output: 2

    Follow up: Could you do it without any loop/recursion in O(1) runtime?
 */
//  Iteration
public class AddDigits {
    public int addDigits(int num) {
        if (num < 10) return num;
        while (num >= 10) {
            num = num % 10 + num / 10;
        }
        return num;
    }
}
/*  Digit Root
        if (num == 0) return 0;
        return (num % 9 == 0) ? 9 : num % 9;

        // return (num - 1) % 9 + 1;
 */