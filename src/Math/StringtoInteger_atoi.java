package Math;

/*  8. String to Integer (atoi)
    Implement atoi which converts a string to an integer.
    The function first discards as many whitespace characters as necessary
    until the first non-whitespace character is found.
    Then, starting from this character,
    takes an optional initial plus or minus sign followed by as many numerical digits as possible,
    and interprets them as a numerical value.

    The string can contain additional characters after those that from the internal number,
    which are ignored and have no effect on the behavior of this function.

    If the first sequence of non-whitespace characters in str is not a valid integral number,
    or if no such sequence exists because either str is empty ot it contains only whitespace characters,
    no conversion is performed.

    If no valid conversion could be performed, a zero value is returned.

    Note:
    Only the space character ' ' is considered as whitespace character.
    Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range:
    [−231,  231 − 1].
    If the numerical value is out of the range of representable values, INT_MAX (231 − 1) or INT_MIN (−231) is returned.

    Example 1:
    Input: "42"
    Output: 42

    Example 2:
    Input: "    -42"
    Output: -42

    Example 3:
    Input: "4193 with words"
    Output: 4193

    Example 4:
    Input: "words and 987"
    Output: 0

    Example 5:
    Input: "-91283472332"
    Output: -2147483648
 */
/*
  Cases:
        1. valid "firstChar 是 '+' -'"
        2. not valid "firstChar 不是数字"
        3. 越界
 */
public class StringtoInteger_atoi {
    public int myAtoi(String str) {
        // delete trailing whitespace
        str = str.trim();
        // corner case
        if (str == null || str.length() == 0) return 0;

        char firstChar = str.charAt(0);
        int sign = 1;
        int idx = 0;
        long sum = 0;
        // positive number
        if (firstChar == '+') {
            sign = 1;
            idx++;
        // negative number
        } else if (firstChar == '-') {
            sign = -1;
            idx++;
        }
        for (int i = idx; i < str.length(); i++) {
            // if encounter non-digit, return the result
            if (!Character.isDigit(str.charAt(i))) {
                return (int) sum * sign;
            }
            sum = sum * 10 + str.charAt(i) - '0';       // 123 = (1 * 10 + 2) * 10 + 3
            // deal with the overflow
            if (sign == 1 && sum > Integer.MAX_VALUE) return Integer.MAX_VALUE;
            if (sign == -1 && sum > Integer.MAX_VALUE) return Integer.MIN_VALUE;
        }
        return (int) sum * sign;
    }
}
