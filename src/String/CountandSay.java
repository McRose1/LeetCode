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
    public String countAndSay(int n) {      // 4
        int i = 1;
        String res = "1";
        while (i < n) {
            int count = 0;
            StringBuilder sb = new StringBuilder();
            char c = res.charAt(0);                             // 1; 1; 2
            for (int j = 0; j <= res.length(); j++) {           //          0; 1; 2
                if (j != res.length() && res.charAt(j) == c) {
                    count++;                                    // 1; 1; 2; 1
                // j == res.length() -> 遍历到尾部开始清算结果
                } else {
                    sb.append(count);                           // 1; 2;    1;  1
                    sb.append(c);                               // 11; 21;  12; 1221
                    // res 里面有多个数字的情况
                    if (j != res.length()) {                            // j = 1
                        // 初始化下一个数字的个数为 1
                        count = 1;
                        // 更新下一个比对值
                        c = res.charAt(j);                              // c = "21".charAt(1) = 1
                    }
                }
            }
            res = sb.toString();        // res = "11"; "21"; "1221"
            i++;                        // i = 2; 3; 4
        }
        return res;
    }
}

/*  Recursion

        if (n == 1) return "1";

        // 找到 n - 1 的结果
        String res = countAndSay(n - 1);

        StringBuilder sb = new StringBuilder();
i
        // 对 n - 1 的结果进行表示
        for (int i = 0; i < res.length(); i++) {    // res = "1"; "11"; "21"
            char c = res.charAt(i);                      // 1; 1; 2; 1
            int count = 1;
            // 判断是否有多个连续重复数字
            while ((i + 1) < res.length() && res.charAt(i + 1) == c) {  // 0+1=1<2
                count++;    // 2
                i++;        // 1
            }
            sb.append(count + "" + c);      // 11; 21; 12; 1211
        }
        return sb.toString();

 */