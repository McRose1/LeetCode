package Stack;

/*  150. Evaluate Reverse Polish Notation
    Evaluate the value of an arithmetic expression in Reverse Polish Notation.
    Valid operators are +, -, *, /. Each operand may be an integer or another expression.

    Note:
    Division between two integers should truncate toward zero.

    The given RPN expression is always valid.
    That means the expression would always evaluate to a result and there won't be any divide by zero operation.

    Example 1:
    Input: ["2", "1", "+", "3", "*"]
    Output: 9
    Explanation: ((2 + 1) * 3) = 9

    Example 2:
    Input: ["4", "13", "5", "/", "+"]
    Output: 6
    Explanation: (4 + (13 / 5)) = 6

    Example 3:
    Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
    Output: 22
    Explanation:
      ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
    = ((10 * (6 / (12 * -11))) + 17) + 5
    = ((10 * (6 / -132)) + 17) + 5
    = ((10 * 0) + 17) + 5
    = (0 + 17) + 5
    = 17 + 5
    = 22
 */

import java.util.Stack;
//  逆波兰式可以用 stack 来实现
public class EvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {
        int a, b;
        Stack<Integer> s = new Stack<>();
        for (String token : tokens) {
            if (token.equals("+")) {
                s.add(s.pop() + s.pop());
            } else if (token.equals("*")) {
                s.add(s.pop() * s.pop());
            } else if (token.equals("-")) {
                b = s.pop();    // 减数
                a = s.pop();    // 被减数
                s.add(a - b);
            } else if (token.equals("/")) {
                b = s.pop();    // 除数
                a = s.pop();    // 被除数
                s.add(a / b);
            } else {
                s.add(Integer.parseInt(token));
            }
        }
        return s.pop();
    }
}
