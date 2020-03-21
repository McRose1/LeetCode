package Math;

/*  66. Plus One
    Given a non-empty array of digits representing a non-negative integer, plus one to the integer.

    The digits are stored such that the most significant digit is at the head of the list, and each element
    in the array contain a single digit.

    You may assume the integer does not contain any leading zero, except the number 0 itself.

    Example 1:
    Input: [1,2,3]
    Output: [1,2,4]

    Example 2:
    Input: [4,3,2,1]
    Output: [4,3,2,2]
 */

/*
    Case1: 1011 -> 1012
    Case2: 1009 -> 1010
    Case3: 9999 -> 10000
 */
public class PlusOne {
    public int[] plusOne(int[] digits) {
        for(int i = digits.length - 1; i >= 0; i--) {
            if(digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        int[] result = new int[digits.length + 1];  // default value is 0.
        result[0] = 1;
        return result;
    }
}

/*  my version（把问题复杂化了。。。）

        int len = digits.length;
        int carry = 0;
        int[] temp = new int[len];

        if (digits[len - 1] == 9) {
                temp[len - 1] = 0;
                carry = 1;
            } else {
                temp[len - 1] = digits[len - 1] + 1;
            }

        for (int i = len - 2; i >= 0; i--) {
            int digit = (digits[i] + carry) % 10;
            carry = (digits[i] + carry) / 10;
            temp[i] = digit;

        }
        if (carry == 1) {
            if (temp[0] == 0) {
                int[] plus = new int[len + 1];
                plus[0] = 1;
                int num = 1;
                for (int d : temp) {
                    plus[num++] = d;
                }
                return plus;
            } else {
                temp[0] += 1;
                return temp;
            }
        }
        return temp;
 */