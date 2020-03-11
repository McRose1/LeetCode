/*
    Given a string of numbers and operators,
    return all possible results from computing all the different possible ways to group numbers and operators.
    The valid operators are +, - and *.

    Example 1:
    Input: "2-1-1"
    Output: [0, 2]

    Example 2:
    Input: "2*3-4*5"
    Output: [-34, -14, -10, -10, 10]
 */

import java.util.ArrayList;
import java.util.List;

public class DifferentWaystoAddParentheses {
    public List<Integer> diffWaysToCompute(String input) {

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {      // 实现所有的 divide
            char c = input.charAt(i);
            if (c == '-' || c == '+' || c == '*') {
                String a = input.substring(0, i);
                String b = input.substring(i + 1);
                List<Integer> al = diffWaysToCompute(a);
                List<Integer> bl = diffWaysToCompute(b);
                for (Integer i1 : al) {
                    for (Integer i2 : bl) {
                        int r = 0;
                        switch (c) {
                            case '+':
                                r = i1 + i2;
                                break;
                            case '-':
                                r = i1 - i2;
                                break;
                            case '*':
                                r = i1 * i2;
                                break;
                        }
                        res.add(r);
                    }
                }
            }
        }
        if (res.size() == 0) {
            res.add(Integer.valueOf(input));
        }
        return res;
    }
}
/*
    Divide and Conquer
 */