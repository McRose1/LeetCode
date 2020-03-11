package String;

/*  38. Count and Say
    The count-and-say sequence is the sequence of integers with the first five terms as following:
    1. 1
    2. 11
    3. 21
    4. 1211
    5. 111221

    1 is read off as "one 1" or 11.
    11 is read off as "two 1s" or 21.
    21 is read off as "one 2, then one 1" or 1211.

    Given an integer n where 1 ≤ n ≤ 30, generate the nth term of the count-and-say sequence.
    You can do so recursively, in other words from the previous member read off the digits,
    counting the number of digits in groups of the same digit.

    Note: Each term of the sequence of integers will be represented as a string.

    Example 1:
    Input: 1
    Output: "1"

    Example 2:
    Input: 4
    Output: "1211"
 */
//  Iteration
public class CountandSay {
    public String countAndSay(int n) {
        int i = 1;
        String res = "1";
        while (i < n) {
            int count = 0;
            StringBuilder sb = new StringBuilder();
            char c = res.charAt(0);
            for (int j = 0; j <= res.length(); j++) {
                if (j != res.length() && res.charAt(j) == c) {
                    count++;
                } else {
                    sb.append(count);
                    sb.append(c);
                    if (j != res.length()) {
                        count = 1;
                        c = res.charAt(j);
                    }
                }
            }
            res = sb.toString();
            i++;
        }
        return res;
    }
}

/*  Recursion

        if (n == 1) return "1";

        StringBuilder sb = new StringBuilder();

        // 找到 n - 1 的结果
        String str = countAndSay(n - 1);

        // 对 n - 1 的结果进行表示
        char c = '1';
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            count = 1;
            while ((i + 1) < str.length() && str.charAt(i + 1) == c) {
                count++;
                i++;
            }
            sb.append(count + "" + c);
        }
        return sb.toString();

 */