package Math;

/*  67. Add Binary
    Given two binary strings, return their sum (also a binary string);
    The input strings are both non-empty and contains only characters 1 or 0.

    Example 1:
    Input: a = "11", b = "1"
    Output: "100"

    Example 2:
    Input: a = "1010", b = "1011"
    Output: "10101"
 */

public class AddBinary {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        while(i >= 0 || j >= 0) {
            int sum = carry;
            if(i >= 0) {
                sum += a.charAt(i--) - '0';
            }
            if(j >= 0) {
                sum += b.charAt(j--) - '0';
            }
            sb.append(sum % 2);
            carry = sum / 2;
        }
        if(carry > 0) {
            sb.append(1);
        }
        return sb.reverse().toString();
    }
}

/*
    StringBuilder sb = new StringBuilder();
    创建可以不断开辟空间的 string
 */

/*
    char -> integer: '9' -> 0
    '9' - '0' == 9
 */

/*
    StringBuilder.insert(int offset, char c)
 */