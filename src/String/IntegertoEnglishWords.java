package String;

/*  273. Integer to English Words
    Convert a non-negative integer to its english words representation.
    Given input is guaranteed to be less than 2^31 - 1.

    Example 1:
    Input: 123
    Output: "One Hundred Twenty Three"

    Example 2:
    Input: 12345
    Output: "Twelve Thousand Three Hundred Forty Five"

    Example 3:
    Input: 1234567
    Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"

    Example 4:
    Input: 1234567891
    Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 */
/*  Recursion: Time = O(n) Space = O(1)
    1. 先把数字以 3 个为一组分成若干小组（从右往左）
    123, 456, 789, 012, 345...
    2. 在每个小组里单独处理三位数
    1) 特殊数：0-19
    2) 一般两位数：20-99
    3) 三位数：(百位上的数+后面两位)
 */
public class IntegertoEnglishWords {
    String[] less20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    String[] thousands = {"", "Thousand", "Million", "Billion"};
    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        // 先将数字从低位开始拆分成 3 个一组
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (num > 0) {
            // 处理 000 这种情况
            if (num % 1000 != 0) {
                // 从低位到高位，每次加到 sb 的头部
                sb.insert(0, helper(num % 1000) + thousands[i] + " ");
            }
            num /= 1000;
            // i 代表拆成 3 个一组的组数，帮助我们处理千位上的情况
            i++;
        }
        return sb.toString().trim();
    }
    // 递归处理每一组中的 3 个数字
    public String helper(int num) {
        if (num == 0) return "";
        // 1-19
        if (num < 20) {
            return less20[num] + " ";
        }
        // 20-99
        else if (num < 100) {
            return tens[num / 10] + " " + helper(num % 10);
        }
        // 100-999
        else {
            return less20[num / 100] + " Hundred " + helper(num % 100);
        }
    }
}
