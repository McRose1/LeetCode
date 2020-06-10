package Stack;

/*  71. Simplify Path
    Given an absolute path for a file (Unix-style), simplify it. Or in other words, convert it to the canonical path.
    In a UNIX-style file system, a period . refers to the current directory.
    Furthermore, a double period .. moves the directory up a level.
    Note that the returned canonical path must always begin with a slash /, and there must be only a single slash / between two directory names.
    The last directory name (if exists) must not end with a trailing /.
    Also, the canonical path must be the shortest string representing the absolute path.

    Example 1:
    Input: "/home/"
    Output: "/home"
    Explanation: Note that there is no trailing slash after the last directory name.

    Example 2:
    Input: "/../"
    Output: "/"
    Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.

    Example 3:
    Input: "/home//foo/"
    Output: "/home/foo"
    Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.

    Example 4:
    Input: "/a/./b/../../c/"
    Output: "/c"

    Example 5:
    Input: "/a/../../b/../c//.//"
    Output: "/c"

    Example 6:
    Input: "/a//b////c/d//././/.."
    Output: "/a/b/c"
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*  Stack: Time = O(n) Space = O(n)
    1) . -> 忽略
    2) .. -> level up -> stack
    3) "" -> 忽略
    先将斜杠去掉，取出路径名和'.'以及'..'，形成一个 string 数组
    遍历这个 string 数组；遇到'.'忽略，遇到'..'如果 stack 不为空，就 pop；遇到路径名，就 push
    stack 里最后剩下的路径就是合法路径，pop 出来的时候顺序是相反的，所以可以用 sb.insert 不断从头部插入栈顶元素
 */
public class SimplifyPath {
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] strs = path.split("/");
        for (String s : strs) {
            if (s.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (!s.equals(".") && !s.equals("")) {
                stack.push(s);
            }
        }
        // 防止出现：/../
        if (stack.isEmpty()) {
            return "/";
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, "/" + stack.pop());  // 从右往左
        }
        return sb.toString();
        /*  也可以用 List 来存
        List<String> list = new ArrayList<>(stack);     // bottom to top: a,b,c
        return "/" + String.join("/", list);    // /a/b/c
         */
    }
}