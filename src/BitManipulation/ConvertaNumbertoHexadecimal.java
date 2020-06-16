package BitManipulation;/*
    Given an integer, write an algorithm to convert it to hexadecimal.
    For negative integer, two's compliment method is used.
    Note:
    All letters in hexadecimal (a-f) must be in lowercase.

    The hexadecimal string must not contain extra leading 0s.
    If the number is zero, it is represented by a single zero character '0'; otherwise,
    the first character in the hexadecimal string will not be the zero character.

    The given number is guaranteed to fit within the range of a 32-bit signed integer.
    You must not use any method provided by the library which converts/formats the number to hex directly.

    Example 1:
    Input:
    26
    Output:
    "1a"

    Example 2:
    Input:
    -1
    Output:
    "ffffffff"
 */
/*  Bit Manipulation
    将原来的 bit representation 分隔成为 4 个为一个大格，这个大格由和 1111 进行与运算得到
 */
public class ConvertaNumbertoHexadecimal {
    public String toHex(int num) {
        char[] map = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        if (num == 0) return "0";
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            // 相当于对 16 取余，考虑到负数的情况用与运算
            sb.append(map[num & 15]);   // 26 & 15 = 10 -> a
            // 除以 16，考虑到负数的情况用移位计算
            num = num >>> 4;        // 26 >>> 4 = 1
        }
        return sb.reverse().toString();
    }
}

/*  附赠一个十进制到八进制的转换

    public String toOctal(int num) {
        char[] map = {'0', '1', '2', '3', '4', '5', '6', '7'};
        if (num == 0) return "0";
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            sb.append(map[num & 7]);
            num = num >>> 3;
        }
        return sb.reverse().toString();
    }
 */
