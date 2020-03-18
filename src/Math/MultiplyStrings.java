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
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int n1 = num1.length();                                     // 3
        int n2 = num2.length();                                     // 3
        // 保存最后的结果
        int[] pos = new int[n1 + n2];                               // [0, 0, 0, 0, 0, 0]
        for (int i = n1 - 1; i >= 0; i--) {                         // 2;       1;        0
            for (int j = n2 - 1; j >= 0; j--) {                     // 2; 1; 0
                // 相乘的结果
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');  // 18; 15; 12/ 12; 10; 8/ 6; 5; 4
                // 加上 pos[i+j+1] 之前已经累加的结果
                int sum = mul + pos[i + j + 1];      // 18; 15+p[4]=16; 12+p[3]=13/ 12+p[4]=18; 10+p[3]=14; 8+p[2]=10/ 6+p[3]=10; 5+p[2]=6; 4+p[1]=5
                // 更新 pos[i + j] -> carry
                pos[i + j] += sum / 10;              // p[4]=1; p[3]=1; p[2]=1/     p[3]=4; p[2]=2; p[1]=1/           p[2]=1; p[1]=1; p[0]=0
                // 更新 pos[i + j + 1]
                pos[i + j + 1] = sum % 10;           // p[5]=(8); p[4]=6; p[3]=3/   p[4]=(8); p[3]=4; p[2]=0/      p[3]=(0); p[2]=(6); p[1]=(5)
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pos.length; i++) {          // [0, 5, 6, 0, 8, 8]
            // 判断最高位是不是 0
            if (i == 0 && pos[i] == 0) {
                continue;
            }
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