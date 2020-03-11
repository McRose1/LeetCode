/*
    Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.
    Note:
    Thee length of both num1 and num2 is < 5100.
    Both num1 and num2 contains only digits 0-9.
    Both num1 and num2 does not contain any leading zero.
    You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */

public class AddStrings {
    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;
        StringBuilder st = new StringBuilder();
        while (i >= 0 || j >= 0 || carry == 1) {
            if (i >= 0) {
                carry += num1.charAt(i) - '0';
                i--;
            }
            if (j >= 0) {
                carry += num2.charAt(j) - '0';
                j--;
            };
            st.append(carry % 10);
            carry /= 10;
        }
        return st.reverse().toString();
    }
}
