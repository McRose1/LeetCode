package String;

/*  722. Remove Comments
    Given a C++ program, remove comments from it. The program source is an array where source[i] is the i-th line of the source code.
    This represents the result of splitting the original source code string by the newline character \n.
    In C++, there are two types of comments, line comments, and block comments.
    The string // denotes a line comment, which represents that it and rest of the characters to the right of it in the same line should be ignored.
    The string /* denotes a block comment, which represents that all characters until the next (non overlapping) occurrences of / * should be ignored.
    (Here, occurrences happen in reading order: line by line from left to right).
    To be clear, the string /* / does not yet end the block comment, as the ending would be overlapping the beginning.
    The first effective comment takes precedence over others: if the string // occurs in a block comment,
    it is ignored. Similarly, if the string /* occurs in a line or block comment, it is also ignored.
    If a certain line of conde is empty after removing comments, you must not output that line:
    each string in the answer list will be non-empty.
    There will be no control characters, single quote, or double quote characters.
    For example, source = "string = "/* Not a comment. * /;" will not be a test case.
    (Also, nothing else such as defines or macros will interface with comments.)
    It is guaranteed that every open block comment will eventually be closed, so /* outside of a line or block comment always starts a new comment.
    Finally, implicitly newline characters can be deleted by block comments.
    Please see the examples below for details.
    After removing the comments from the source code, return the source code in the same format.

    Example 1:
    Input:
    source = ["/*Test program * /", "int main()", "{ ", "  // variable declaration ", "int a, b, c;", "/* This is a test", "   multiline  ", "   comment for ", "   testing * /", "a = b + c;", "}"]

    The line by line code is visualized as below:
    /*Test program * /
    int main() {
        // variable declaration
        int a, b, c;
        /* This is a test multiline comment for testing * /
        a = b + c;
    }

    Output: ["int main()","{ ","  ","int a, b, c;","a = b + c;","}"]

    The line by line code is visualized as below:
    int main() {
        int a, b, c;
        a = b + c;
    }

    Explanation:
    The string /* denotes a block comment, including line 1 and lines 6-9. The string // denotes line 4 as comments.

    Note:
    o The length of source is in the range [1, 100].
    o The length of source[i] is in the range [0, 80].
    o Every open block comment is eventually closed.
    o There are no single-quote, double-quote, or control characters in the source code.

    Hint: Carefully parse each line according to the following rules:
    * If we start a block comment and we aren't in a block, then we will skip over the next two characters and change our state to be in a block.
    * If we end a block comment and we are in a block, then we will skip over the next two characters and change our state to be *not* in a block.
    * If we start a line comment and we aren't in a block, then we will ignore the rest of the line.
    * if we aren't in a block comment (and it wasn't the start of the comment), we will record the character we are at.
    * At the end of each line, if we aren't in a block, we will record the line.
 */

import java.util.ArrayList;
import java.util.List;

/*  Parsing
    我们需要逐行分析源代码。有 2 种情况，要么在一个注释内或者不在。
    1. 如果我们遇到注释块符号，而我们不在注释中，那么我们将跳过接下来的 2 个字符，并将状态更改为注释中。
    2. 如果我们遇到注释块符号并且我们在注释中，那么我们将跳过接下来的 2 个字符并将状态更改为不在注释中。
    3. 如果我们遇到一个行注释且我们不在注释中，那么我们将忽略该行的其余部分。
    4. 如果我们不在注释中（并且它不是注释的开头），我们将记录所遇到的字符。
    在每行的末尾，如果我们不在注释中，我们将记录该行。
 */
public class RemoveComments {
    public List<String> removeComments(String[] source) {
        boolean inBlock = false;
        StringBuilder newline = new StringBuilder();
        List<String> res = new ArrayList<>();
        for (String line : source) {
            int i = 0;
            char[] chars = line.toCharArray();
            if (!inBlock) {
                newline = new StringBuilder();
            }
            while (i < line.length()) {
                // 1.
                if (!inBlock && i + 1 < line.length() && chars[i] == '/' && chars[i + 1] == '*') {
                    inBlock = true;
                    i++;
                }
                // 2.
                else if (inBlock && i + 1 < line.length() && chars[i] == '*' && chars[i + 1] == '/') {
                    inBlock = false;
                    i++;
                }
                // 3.
                else if (!inBlock && i + 1 < line.length() && chars[i] == '/' && chars[i + 1] == '/') {
                    break;
                }
                // 4.
                else if (!inBlock) {
                    newline.append(chars[i]);
                }
                i++;
            }
            if (!inBlock && newline.length() > 0) {
                res.add(new String(newline));
            }
        }
        return res;
    }
}
