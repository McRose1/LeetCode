package Math;

/*  43. Multiply Strings
    Given two non-negative integers num1 and num2 represented as strings,
    return the product of num1 and num2, also represented as a string.

    Example 1:
    Input: num1 = "2", num2 = "3"
    Output: "6"

    Example 2:
    Input: num1 = "123", num2 = "456"
    Output: "56088"

    Note:
    1. The length of both num1 and num2 is < 110.
    2. Both num1 and num2 contain only digits 0-9.
    3. Both num1 and num2 do not contain any leading zero, except the number 0 itself.
    4. You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */

/*  竖式计算（从右到左）
    num1的第i位乘上num2的第j位，结果会分别对应pos的第i+j位和第i+j+1位
 */
public class MultiplyStrings {
    public String multiply(String num1, String num2) {              // num1 = "123", num2 = "456"
        if (num1 == null || num2 == null || num1.length() == 0 || num2.length() == 0) {
            return "";
        }

        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        int n1 = num1.length();
        int n2 = num2.length();
        // 两数相乘结果的位数最小为两数位数之和减 1，最大为两数位数之和
        int[] pos = new int[n1 + n2];

        for (int i = n1 - 1; i >= 0; i--) {
            for (int j = n2 - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                // 加上 pos[i+j+1] 之前轮次中已经累加的结果
                int sum = mul + pos[i + j + 1];
                // 确定该位的值，以后不会再遍历到，所以直接赋值
                pos[i + j + 1] = sum % 10;
                // 更新这一轮的进位，加入前一位的数字供下一轮使用
                pos[i + j] += sum / 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pos.length; i++) {
            // 判断最高位是不是 0
            if (i == 0 && pos[i] == 0) continue;
            sb.append(pos[i]);
        }
        return sb.toString();
    }
}

/*  Brute Force

    public String multiply(String num1, String num2) {      // num1 = "123", num2 = "456"
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        String ans = "0";
        int index = 0;
        for (int i = num2.length() - 1; i >= 0; i--) {
            int carry = 0;
            // 直接用字符串保存每位乘出来的数
            String ans_part = "";
            int m = num2.charAt(i) - '0';                           // 6; 5
            // 乘上每一位
            for (int j = num1.length() - 1; j >= 0; j--) {
                int n = num1.charAt(j) - '0';             // 3; 2; 1
                int mul = m * n + carry;                  // 18; 13; 7     15; 11; 6
                ans_part = mul % 10 + ans_part;           // 8; 38; 738    5; 15; 615
                carry = mul / 10;                         // 1; 1; 0       1; 1; 0
            }
            if (carry > 0) {
                ans_part = carry + ans_part;
            }
            // 补0
            for (int k = 0; k < index; k++) {
                ans_part = ans_part + "0";                                // 6150
            }
            index++;                            // 1; 2
            // 和之前的结果相加
            ans = sumString(ans, ans_part);     // sumString("738", "6150")
        }
        return ans;
    }
    // 大数相加
    private String sumString(String num1, String num2) {
        int carry = 0;
        int num1_index = num1.length() - 1;             // 2
        int num2_index = num2.length() - 1;             // 3
        String ans = "";
        while (num1_index >= 0 || num2_index >= 0) {
            int n1 = num1_index >= 0 ? num1.charAt(num1_index) - '0' : 0;   // 8; 3; 7; 0
            int n2 = num2_index >= 0 ? num2.charAt(num2_index) - '0' : 0;   // 0; 5; 1; 6
            int sum = n1 + n2 + carry;                                      // 8; 8; 8; 6
            carry = sum / 10;                                               // 0; 0; 0; 0
            ans = sum % 10 + ans;                                           // 8; 88; 888; 6888
            num1_index--;                                                   // 1; 0; -1; -2
            num2_index--;                                                   // 2; 1; 0; -1
        }
        if (carry > 0) {
            ans = carry + ans;
        }
        return ans;
    }

 */