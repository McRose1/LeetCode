package String;

/*  468. Validate IP Address
    Write a function to check whether an input string is a valid IPv4 address or IPv6 address or neither.
    IPv4 addresses are canonically represented in dot-decimal notation, which consists of four decimal numbers,
    each ranging from 0 to 255, separated by dots ("."), e.g., 172.16.254.1;
    Besides, leading zeros in the IPv4 is invalid. For example, the address 172.16.254.01 is invalid.
    IPv6 addresses are represented as eight groups of four hexadecimal digits, each group representing 16 bits.
    The groups are separated by colons (":"). For example, the address 2001:0db8:85a3:0000:0000:8a2e:0370:7334 is a valid one.
    Also, we could omit some leading zeros among four hexadecimal digits and some low-case characters in the address to upper-case ones,
    so 2001:db8:85a3:0:0:8A2E:0370:7334 is also a valid IPv6 address (Omit leading zeros and using upper cases).
    However, we don't replace a consecutive group of zero value with a single empty group using two consecutive colons (::) to pursue simplicity.
    For example, 2001:0db8:85a3::8A2E:0370:7334 is an invalid IPv6 address.
    Besides, extra leading zeros in the IPv6 is also invalid. For example, the address 02001:0db8:85a3:0000:0000:8a2e:0370:7334 is invalid.

    Example 1:
    Input: IP = "172.16.254.1"
    Output: "IPv4"
    Explanation: This is a valid IPv4 address, return "IPv4".

    Example 2:
    Input: IP = "2001:0db8:85a3:0:0:8A2E:0370:7334"
    Output: "IPv6"
    Explanation: This is a valid IPv6 address, return "IPv6".

    Example 3:
    Input: IP = "256.256.256.256"
    Output: "Neither"
    Explanation: This is neither a IPv4 address nor a IPv6 address.

    Constraints:
        o IP consists only of English letters, digits and the characters "." and ":".
 */

/*  Divide and Conquer
    o 对于 IPv4 地址，通过界定符 . 将地址分为四块；对于 IPv6 地址，通过界定符 : 将地址分为八块。
    o 对于 IPv4 地址的每一块，检查它们是否在 0 - 255 内，且没有前置零
    o 对于 IPv6 地址的每一块，检查其长度是否为 1 - 4 位的十六进制数。
 */
public class ValidateIPAddress {
    public String validIPAddress(String IP) {
        if (IP.chars().filter(ch -> ch == '.').count() == 3) {
            return validateIPv4(IP);
        } else if (IP.chars().filter(ch -> ch == ':').count() == 7) {
            return validateIPv6(IP);
        } else {
            return "Neither";
        }
    }
    // "172.16.254.1"
    private String validateIPv4(String IP) {
        String[] nums = IP.split("\\.", -1);
        for (String x : nums) {
            // Validate integer in range (0, 255)
            // 1. length of chunk is between 1 and 3
            if (x.length() == 0 || x.length() > 3) {
                return "Neither";
            }
            // 2. no extra leading zeros
            if (x.charAt(0) == '0' && x.length() != 1) {
                return "Neither";
            }
            // 3. only digits are allowed
            for (char ch : x.toCharArray()) {
                if (!Character.isDigit(ch)) {
                    return "Neither";
                }
            }
            // 4. less than 255
            if (Integer.parseInt(x) > 255) {
                return "Neither";
            }
        }
        return "IPv4";
    }
    // "2001:0db8:85a3:0:0:8A2E:0370:7334"
    private String validateIPv6(String IP) {
        String[] nums = IP.split(":", -1);
        String hexdigits = "0123456789abcdefABCDEF";
        for (String x : nums) {
            // Validate hexadecimal in range (0, 2^16)
            // 1. at least 1 and not more than 4 hexdigits in one chunk
            if (x.length() == 0 || x.length() > 4) {
                return "Neither";
            }
            // 2. only hexdigits are allowed: 0-9, a-f, A-F
            for (char ch : x.toCharArray()) {
                if (hexdigits.indexOf(ch) == -1) {
                    return "Neither";
                }
            }
        }
        return "IPv6";
    }
}

/*  Regex: Time = O(1) Space = O(1)

        String chunkIPv4 = "([0-9] | [1-9][0-9] | 1[0-9][0-9] | 2[0-4][0-9] | 25[0-5])";
        Pattern patternIPv4 = Pattern.compile("^(" + chunkIPv4 + "\\.){3}" + chunkIPv4 + "$");

        String chunkIPv6 = "([0-9a-fA-F]{1,4})";
        Pattern patternIPv6 = Pattern.compile("^(" + chunkIPv6 + "\\.){7}" + chunkIPv6 + "$");

        public String validIPAddress(String IP) {
            if (IP.contains(".")) {
                return (patternIPv4.matcher(IP).matches()) ? "IPv4" : "Neither";
            } else if (IP.contains(":")) {
                return (patternIPv6.matcher(IP).matches()) ? "IPv6" : "Neither";
            }
            return "Neither";
        }
 */
