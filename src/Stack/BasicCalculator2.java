package Stack;

/*  227. Basic Calculator 2
    Implement a basic calculator to evaluate a simple expression string.
    The expression string contains only non-negative integers, +, -, *, / operators and empty spaces .
    The integer division should truncate toward zero.

    Example 1:
    Input: "3+2*2"
    Output: 7

    Example 2:
    Input: " 3/2 "
    Output: 1

    Example 3:
    Input: " 3+5 / 2 "
    Output: 5

    Note:
    You may assume that the given expression is always valid.
    Do not use the eval built-in library function.
 */

import java.util.Stack;

public class BasicCalculator2 {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int operand = 0;
        char sign = '+';

        for (int i = 0; i < s.length(); i++) {      // 3+2*2
            if (Character.isDigit(s.charAt(i))) {
                operand = operand * 10 + (int) (s.charAt(i) - '0'); // 3; 2
            }
            if (s.charAt(i) != ' ' && !Character.isDigit(s.charAt(i)) || i == s.length() - 1) {
                if (sign == '+') {
                    stack.push(operand);    // 3; 3, 2
                } else if (sign == '-') {
                    stack.push(-operand);
                } else if (sign == '*') {
                    stack.push(stack.pop() * operand);      // 3, 4
                } else if (sign == '/') {
                    stack.push(stack.pop() / operand);
                }
                sign = s.charAt(i);     // +, *
                operand = 0;
            }
        }
        int sum = 0;
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }
        return sum;
    }
}
