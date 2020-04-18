package String;

/*  241. Different Ways to Add Parentheses
    Given a string of numbers and operators,
    return all possible results from computing all the different possible ways to group numbers and operators.
    The valid operators are +, - and *.

    Example 1:
    Input: "2-1-1"
    Output: [0, 2]
    Explanation:
    ((2-1)-1) = 0
    (2-(1-1)) = 2

    Example 2:
    Input: "2*3-4*5"
    Output: [-34, -14, -10, -10, 10]
    Explanation:
    (2*(3-(4*5))) = -34
    ((2*3)-(4*5)) = -14
    ((2*(3-4))*5) = -10
    (2*((3-4)*5)) = -10
    (((2*3)-4)*5) = 10
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*  Divide and Conquer (Recursion with memo)
    每个 string 都会对应一个 List<Integer> 存所有可能的运算结果
    divide 就是把整一个 string 通过不同的分割点一分为二，子 string 再继续分，直到分到只剩数字为止，开始退栈
    往上返回结果的过程相当于是两个 List 做笛卡尔积：[1, 2, 3] U* [3, 2, 1] = [3, 2, 1, 6, 4, 2, 9, 6, 3]
 */
public class DifferentWaystoAddParentheses {
    public List<Integer> diffWaysToCompute(String input) {  // "2-1-1"
        Map<String, List<Integer>> memo = new HashMap<>();
        if (memo.containsKey(input)) {
            return memo.get(input);
        }
        List<Integer> res = new ArrayList<>();
        // 实现所有的 divide
        for (int i = 0; i < input.length(); i++) {      // 2(skip); -; 1(skip); -; 1(skip)
            char op = input.charAt(i);                  // op = '-'; '-'
            if (op == '-' || op == '+' || op == '*') {
                String a = input.substring(0, i);       // a = "2";     "1";        a = "2-1"
                String b = input.substring(i + 1);      // b = "1-1";   "1";        b = "1"
                List<Integer> al = diffWaysToCompute(a); // al = {2};   {1}
                List<Integer> bl = diffWaysToCompute(b); // bl = {0};   {1}
                // 笛卡尔积运算
                for (Integer i1 : al) {     // 1;   2
                    for (Integer i2 : bl) { // 1;   0
                        int result = 0;
                        switch (op) {
                            case '+':
                                result = i1 + i2;
                                break;
                            case '-':
                                result = i1 - i2;   // 1-1=0; 2-0=2
                                break;
                            case '*':
                                result = i1 * i2;
                                break;
                        }
                        res.add(result);        // {0};        {2}
                        memo.put(input, res);   // {"1-1", {0}}; {"2-1-1", {2}}
                    }
                }
            }
        }
        // input 只有数字的情况
        if (res.size() == 0) {
            res.add(Integer.valueOf(input));    // {2};     {1} {1}
            memo.put(input, res);               // {"2", {2}}; {"1", {1}}
        }
        return res;
    }
}