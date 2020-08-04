package Stack;

/*  385. Mini Parser
    Given a nested list of integers represented as a string, implement a parser to deserialize it.
    Each element is either an integer, or a list -- whose elements may also be integers or other lists.

    Note: You may assume that the string is well-formed:
    String is non-empty.
    String does not contain white spaces.
    String contains only digits 0-9, [, -, ,, ].

    Example 1:
    Given s = "324",
    You should return a NestedInteger object which contains a single integer 324.

    Example 2:
    Given s = "[123,[456,[789]]]",
    Return a NestedInteger object containing a nested list with 2 elements:

    1. An integer containing value 123.
    2. A nested list containing two elements:
        i.  An integer containing value 456.
        ii. A nested list with one element:
             a. An integer containing value 789.
 */

import java.util.Stack;

/*  Stack
    '[': push current NestedInteger to stack and start a new one
    ']': end current NestedInteger and pop a NestedInteger from stack to continue
    ',': append a new number to current NestedInteger, if this ',' in not right after a brackets
    0-9: continue
 */
public class MiniParser {
    public NestedInteger deserialize(String s) {    // [123,[456,[789]]]
        // corner case: 不以左括号开头，说明必定没有嵌套，只是一个纯数字
        if (!s.startsWith("[")) {
            return new NestedInteger(Integer.parseInt(s));
        }
        // [123,[456,[789]]]
        Stack<NestedInteger> stack = new Stack<>();
        // res 为最外层的括号
        NestedInteger res = new NestedInteger();
        stack.push(res);
        // start 用来标识数字的首 index
        int start = 1;
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            // 开始当前 NestedInteger
            if (c == '[') {
                NestedInteger ni = new NestedInteger();
                // 在外层 NI 加入当前层 ni
                stack.peek().add(ni);
                // 进栈以便进入下一层
                stack.push(ni);
                start = i + 1;
            } else if (c == ',' || c == ']') {
                // 说明逗号前面是数字
                if (i > start) {
                    int val = Integer.parseInt(s.substring(start, i));
                    stack.peek().add(new NestedInteger(val));
                }
                start = i + 1;
                // 结束当前 NestedInteger
                if (c == ']') {
                    stack.pop();
                }
            }
        }
        return res;
    }
}