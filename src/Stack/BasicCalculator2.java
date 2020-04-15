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
//  Stack
public class BasicCalculator2 {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int operand = 0;
        // 初始化 sign 值
        char sign = '+';

        for (int i = 0; i < s.length(); i++) {      // 3+2*2
            char c = s.charAt(i);
            // 处理数字，进行位数处理
            if (Character.isDigit(c)) {
                operand = operand * 10 + (c - '0'); // 3; 2
            }
            // 处理非数字，以及字符串的最后一个 char，因为 operand 依赖于上一轮的 sign
            if ((c != ' ' && !Character.isDigit(c)) || i == s.length() - 1) {
                if (sign == '+') {
                    stack.push(operand);    // 3; 3, 2
                } else if (sign == '-') {
                    // 将减号作为负号以 operand 的值的形式 push 到 stack 中
                    stack.push(-operand);
                } else if (sign == '*') {
                    stack.push(stack.pop() * operand);      // 3, 4
                } else if (sign == '/') {
                    stack.push(stack.pop() / operand);
                }
                // 给 sign 附上新值
                sign = c;     // +, *
                // 将 operand 重置
                operand = 0;
            }
        }
        int sum = 0;
        // 最后的 stack 只剩下已经处理好 sign 的 operand，直接相加即可
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }
        return sum;
    }
}
