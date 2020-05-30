package String;

/*  271. Encode and Decode Strings
    Design an algorithm to encode a list of strings to a string.
    The encoded string is then sent over the network and is decoded back to the original list of strings.

    Machine 1 (sender) has the function:
    string encode(vector<string> strs) {
    // ... your code
    return encoded_string;
    }

    Machine 1 (receiver) has the function:
    vector<string> decode(string s){
    // ... your code
    return strs;
    }

    So Machine 1 does:
    string encoded_string = encode(strs);
    and Machine 2 does:
    vector<string> strs2 = decode(encoded_string)
    strs2 in Machine 2 should be the same as strs in Machine 1.
    Implement the encode and decode methods.

    Note:
    The string may contain any possible characters out of 256 valid ascii characters.
    Your algorithm should be generalized enough to work on any possible characters.
    Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
    Do not rely on any library method such as eval or serialize methods. You should implement your own encode/decode algorithm.
 */

import java.util.ArrayList;
import java.util.List;
/*  字符串长度 + '\'
    维护一个 StringBuilder，读出每个input string的长度，append 一个特殊字符例如'/'，再 append string
    这样再 decode 的时候我们就可以利用 String.indexOf(char, startIndex) 来算出自 startIndex 其第一个'/'的位置，
    同时计算出接下来读取的 string 长度，用 String.substring() 读出字符串以后我们更新 index，来进行下一次读取。
 */
public class EncodeandDecodeStrings {

    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str.length()).append('/').append(str);
        }
        return sb.toString();
    }

    public List<String> decode(String s) {
        List<String> res = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            int slashIdx = s.indexOf('/', i);
            int size = Integer.parseInt(s.substring(i, slashIdx));
            res.add(s.substring(slashIdx + 1, slashIdx + size + 1));
            i = slashIdx + size + 1;
        }
        return res;
    }
}
