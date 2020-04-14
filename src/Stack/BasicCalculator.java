package Stack;

/*  224. Basic Calculator
    Implement a basic calculator to evaluate a simple expression string.
    The expression string may contain open ( and closing parentheses ),
    the plus + or minus sign -, non-negative integers and empty spaces .

    Example 1:
    Input: "1 + 1"
    Output: 2

    Example 2:
    Input: " 2-1 + 2 "
    Output: 3

    Example 3:
    Input: "(1+(4+5+2)-3)+(6+8)"
    Output: 23

    Note:
    You may assume that the given expression is always valid.
    Do not use the eval built-in library function.
 */

import java.util.Stack;

/*  Stack and No String Reversal: Time = O(n) Space = O(n)
    Whenever we encounter an operator such as + or - we first evaluate the expression to the left
    and then save this sign for the next evaluation.
 */
public class BasicCalculator {
    public int calculate(String s) {                    // 7 - (8 + 9)
        Stack<Integer> stack = new Stack<Integer>();
        int operand = 0;
        // For the on-going result
        int result = 0;
        // 1 means positive, -1 means negative
        int sign = 1;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);                  // 7; -; (; 8; +; 9; )
            if (Character.isDigit(ch)) {
                // Forming operand, since it could be more than one digit
                // 这一步是这题的关键
                operand = 10 * operand + (int) (ch - '0');  // 7; 8; 9
            } else if (ch == '+') {
                // Evaluate the expression to the left
                // with result, sign, operand
                result += sign * operand;       // res = 0+1*8=8;

                // Save the recently encountered '+' sign
                sign = 1;

                // Reset operand
                operand = 0;
            } else if (ch == '-') {
                result += sign * operand;       // 0+1*7=7
                sign = -1;
                operand = 0;
            } else if (ch == '(') {
                // Push the result and sign on to the stack, for later
                // We push the result first, then sign
                stack.push(result);     // 7
                stack.push(sign);       // 7,-1

                // Reset operand and result, as if new evaluation begins for the new sub-expression
                // 这一步也很关键
                sign = 1;               // 1
                result = 0;             // 0
            } else if (ch == ')') {
                // Evaluate the expression to the left
                // with result, sign and operand
                result += sign * operand;       // res = 8+1*9=17

                // ')' marks end of expression within a set of parenthesis
                // Its result is multiplied with sign on top of stack
                // as stack.pop() is the sign before the parenthesis
                result *= stack.pop();          // 17*-1=-17

                // Then add to the next operand on the top.
                // as stack.pop() is the result calculated before this parenthesis
                // (operand on stack) + (sign on stack * (result from parenthesis))
                result += stack.pop();          // -17+7=-10

                // Reset the operand
                operand = 0;
            }
        }
        return result + (sign * operand);       // -10+(1*0)=-10
    }
}

/*  Stack and String Reversal: Time = O(n) Space = O(n)
    Reversing a string helps since we put the elements into the stack from right to right
    and evaluation for the expression is done correctly from left to right.
    (7 - 8 + 9) -> )9 + 8 - 7(
    Push the elements of the expression one by one onto the stack until get a (.
    Pop the elements from the stack one by one and evaluate the expression till we find ).
    123 >> 120 + 3 >> 100 + 20 + 3

    public int calculate(String s) {
        int operand = 0;
        // 用 n 来表示遇到数字的位数
        int n = 0;
        Stack<Object> stack = new Stack<>();

        // 倒序遍历字符串
        for (int i = s.length() - 1; i >= 0; i--) {     // (7 - 8 + 9)
            char ch = s.charAt(i);
            // 如果是数字
            if (Character.isDigit(ch)) {
                // Forming the operand - in reverse order.
                operand = (int) Math.pow(10, n) * (ch - '0') + operand;   // 9; 8; 7
                // 遇到连续数字，位数要更新
                n += 1;
            } else if (ch != ' ') {
                // 说明当前有数字未处理
                if (n != 0) {
                    // Save the operand on the stack as we encounter some non-digit.
                    stack.push(operand);    // )9; )9+8; )9+8-7
                    // 将参数重置
                    n = 0;
                    operand = 0;
                }
                // 在倒转的字符串中，'('表示终止符，表明我们现在可以计算括号内的表达式
                if (ch == '(') {
                    int res = evaluateExpr(stack);  // )9+8-7   res = 8
                    // pop the ")"
                    stack.pop();

                    // Append the evaluated result to the stack.
                    // This result could be of a sub-expression within the parenthesis.
                    stack.push(res);                // 8
                } else {
                    // For other non-digits just push onto the stack.
                    stack.push(ch);     // ); )9+; )9+8-
                }
            }
        }
        // Push the last operand to stack, if any.
        if (n != 0) {
            stack.push(operand);
        }
        // Evaluate any left overs in the stack.
        return evaluateExpr(stack);
    }
    public int evaluateExpr(Stack<Object> stack) {
        int res = 0;
        if (!stack.isEmpty()) {     //  )9+8-7
            res = (int) stack.pop();    // 7
        }
        // Evaluate the expression till we get corresponding ')'
        while (!stack.isEmpty() && !((char) stack.peek() == ')')) {
            char sign = (char) stack.pop();
            if (sign == '+') {
                res += (int) stack.pop();   // -1+9=8
            } else {
                res -= (int) stack.pop();   // 7-8=-1
            }
        }
        return res;     // res = 8
    }
 */
