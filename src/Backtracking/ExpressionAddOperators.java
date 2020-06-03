package Backtracking;

/*  282. Expression Add Operators
    Given a string that contains only digits 0-9 and a target value,
    return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.

    Example 1:
    Input: num = "123", target = 6
    Output: ["1+2+3", "1*2*3"]

    Example 2:
    Input: num = "232", target = 8
    Output: ["2*3+2", "2+3*2"]

    Example 3:
    Input: num = "105", target = 5
    Output: ["1*0+5","10-5"]

    Example 4:
    Input: num = "00", target = 0
    Output: ["0+0", "0-0", "0*0"]

    Example 5:
    Input: num = "3456237490", target = 9191
    Output: []
 */

import java.util.ArrayList;
import java.util.List;
/*  Backtracking: Time = O(N*(4^N)) Space = O(N)
    At every step, we have exactly 4 different recursive calls.
    The NO OP call simply extends the current_operand by the current digit and moves ahead.
    Rest of the recursive call correspond to +, -, and *.
    乘法有优先级，需要特殊处理；还需要处理例如 05 这种情况
    Instead of just keeping track of what the expression string is, we will also keep track of its value along the way.
    We need to keep track of the last operand in our expression and how it modified the expression's value overall
    so that when we consider the * operator, we can reverse the effects of the previous operand and consider it for multiplication.
 */
public class ExpressionAddOperators {
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        if (num == null || num.length() == 0) {
            return res;
        }
        dfs(num, target, res, "", 0, 0, 0);
        return res;
    }

    private void dfs(String num, int target, List<String> res, String path, int pos, long cal, long mul) {
        // 递归出口：走完整个 num && 结果==target => add to res
        if (pos == num.length()) {
            if (cal == target) {
                res.add(path);
            }
            return;
        }
        // 拆解：数字的可能性 * 运算符号可能性
        for (int i = pos; i < num.length(); i++) {
            // To avoid cases where we have 1 + 05 or 1 * 05 since 05 won't be a valid operand
            if (i != pos && num.charAt(pos) == '0') break;
            // num = "3456237490", target = 9191 -> 防止整型溢出
            long cur = Long.parseLong(num.substring(pos, i + 1));
            // 列等式：1.头一个；2.不是头一个
            if (pos == 0) {
                dfs(num, target, res, path + cur, i + 1, cur, cur);
            } else {
                dfs(num, target, res, path + "+" + cur, i + 1, cal + cur, cur);
                dfs(num, target, res, path + "-" + cur, i + 1, cal - cur, -cur);
                // 2 + 3 * 2 -> cal = 5, mul = 3, cur = 2 -> (5 - 3) + (3 * 2)
                dfs(num, target, res, path + "*" + cur, i + 1, (cal - mul) + mul * cur, mul * cur);
            }
        }
    }
}

/*  LC

    public String digits;
    public long target;

    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        if (num == null || num.length() == 0) {
            return res;
        }
        dfs(num, target, res, 0, 0, 0, 0, new ArrayList<>());
        return res;
    }

    private void dfs(String num, int target, List<String> res, int idx, long prevOperand, long curOperand, long val, List<String> ops) {
        // Done processing all the digits in num
        if (idx == num.length()) {
            // If the final value == target expected AND no operand is left unprocessed
            if (val == target && curOperand == 0) {
                StringBuilder sb = new StringBuilder();
                ops.subList(1, ops.size()).forEach(v -> sb.append(v));
                res.add(sb.toString());
            }
            return;
        }

        // Extending the current operand by one digit
        curOperand = curOperand * 10 + Character.getNumericValue(num.charAt(idx));
        String cur_val_rep = Long.toString(curOperand);
        int length = num.length();

        // To avoid cases where we have 1 + 05 or 1 * 05 since 05 won't be a valid operand. Hence this check:
        if (curOperand > 0) {
            // NO OP recursion
            dfs(num , target, res, idx + 1, prevOperand, curOperand, val, ops);
        }

        // Addition
        ops.add("+");
        ops.add(cur_val_rep);
        dfs(num, target, res, idx + 1, curOperand, 0, val + curOperand, ops);
        ops.remove(ops.size() - 1);
        ops.remove(ops.size() - 1);

        if (ops.size() > 0) {

            // Subtraction
            ops.add("-");
            ops.add(cur_val_rep);
            dfs(num, target, res, idx + 1, -curOperand, 0, val - curOperand, ops);
            ops.remove(ops.size() - 1);
            ops.remove(ops.size() - 1);

            // Multiplication
            ops.add("*");
            ops.add(cur_val_rep);
            dfs(num, target, res, idx + 1, curOperand * prevOperand, 0, val - prevOperand * (curOperand * prevOperand), ops);
            ops.remove(ops.size() - 1);
            ops.remove(ops.size() - 1);
        }
    }
 */
