package String;

/*  68. Text Justification
    Given an array of words and a width maxWidth,
    format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.
    You should pack your words in a greedy approach; that is, pack as many words as you can in each line.
    Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.
    Extra spaces between words should be distributed as evenly as possible.
    If the number of spaces on a line do not divide evenly between words,
    the empty slots on the left will be assigned more spaces than the slots on the right.
    For the last line of text, it should be left justified and no extra space is inserted between words.

    Note:
    A word is defined as a character sequence consisting of non-space characters only.
    Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
    The input array words contains at least one word.

    Example 1:
    Input:
    words = ["This", "is", "an", "example", "of", "text", "justification."]
    maxWidth = 16
    Output:
    [
       "This    is    an",
       "example  of text",
       "justification.  "
    ]

    Example 2:
    Input:
    words = ["What","must","be","acknowledgment","shall","be"]
    maxWidth = 16
    Output:
    [
      "What   must   be",
      "acknowledgment  ",
      "shall be        "
    ]
    Explanation: Note that the last line is "shall be    " instead of "shall     be",
                 because the last line must be left-justified instead of fully-justified.
                 Note that the second line is also left-justified becase it contains only one word.

    Example 3:
    Input:
    words = ["Science","is","what","we","understand","well","enough","to","explain",
             "to","a","computer.","Art","is","everything","else","we","do"]
    maxWidth = 20
    Output:
    [
      "Science  is  what we",
      "understand      well",
      "enough to explain to",
      "a  computer.  Art is",
      "everything  else  we",
      "do                  "
    ]
 */

import java.util.ArrayList;
import java.util.List;

public class TextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {         // ["What","must","be","acknowledgment","shall","be"], 16
        List<String> res = new ArrayList<>();
        // 记录一共有几个单词
        int n = words.length;                               // 6
        // 遍历每一个单词
        int index = 0;

        while (index < n) {
            // 当前单词长度
            int totalChars = words[index].length();         // 4
            // 标记下一个单词
            int last = index + 1;                           // 1

            // 在一行中的遍历
            while (last < n) {             // 1; 3; 4
                // 当前单词字母数量 + 后一个单词数量 + 它们之间的空格（先默认 1 个） 如果已经超出范围，就跳出循环
                if (totalChars + words[last].length() + 1 > maxWidth) {     // 4+4+1=9<16; 9+2+1=12<16; 12+14+1>16->break
                    break;
                }
                // 总长度加上空格和后一个单词的长度
                totalChars += 1 + words[last].length();             // 4+4+1=9; 16
                last++;                                             // 2; 3
            }
            // 利用一行有几段间隔以此来判断是否出现一行只有一个单词的情况
            int gaps = last - index - 1;                            // 3-0-1=2
            StringBuilder sb = new StringBuilder();
            // 已经到最后一个单词，或者一个单词占了一行
            if (last == n || gaps == 0) {
                for (int i = index; i < last; i++) {
                    sb.append(words[i]);
                    sb.append(' ');
                }
                // 删去每一行中最后一个单词后面的空格
                sb.deleteCharAt(sb.length() - 1);
                // 用空格补全一行后面的空位
                while (sb.length() < maxWidth) {
                    sb.append(' ');
                }
            }
            // 最后一行以上部分以及一行有多个单词的情况
            else {
                // 通过 gap 的数量来算空格的长度，如果不能均分就让前面的单词后面空格为奇数长度
                int spaces = (maxWidth - totalChars) / gaps;        // (16-12)/2=2
                int rest = (maxWidth - totalChars) % gaps;          // 4%2=0

                for (int i = index; i < last - 1; i++) {            // i=0<3-1
                    // 先正常添加单词和后面紧跟的一个空格
                    sb.append(words[i]);                    // "What"; "What   must"
                    sb.append(' ');                         // "What "; "What  must "
                    // 再根据均匀对齐来填充空格
                    for (int j = 0; j < spaces + (i - index < rest ? 1 : 0); j++) { // j<2+0
                        sb.append(' ');                 // "What   "; "What   must   "
                    }
                }
                // 添加一行末尾的数字，后面没有空格
                sb.append(words[last - 1]);         // "What    must    be"
            }
            res.add(sb.toString());
            index = last;                   // index = 3
        }
        return res;
    }
}
