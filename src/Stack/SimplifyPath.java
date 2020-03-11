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

/*
    1) .
    2) .. -> level up -> stack
    3)
 */
public class SimplifyPath {
    public String simplifyPath(String path) {
        if (path == null || path.length() == 0) return "";
        Stack<String> stack = new Stack<>();
        // erase whitespace
        path = path.trim();
        String[] strArray = path.split("/");    // a,b,"","",c,d,"",.,.,"",..

        for (int i = 0; i < strArray.length; i++) {
            // ..
            if (strArray[i].equals("..") && !stack.isEmpty()) {
                stack.pop();                          // d
            // . , .. , "" 以外
            } else if (!strArray[i].equals(".") && !strArray[i].equals("..") && !strArray[i].equals("")) {
                stack.push(strArray[i]);        // a,b,c,d -> a,b,c
            }
        }
        // 组装
        List<String> list = new ArrayList<>(stack);     // bottom to top: a,b,c
        return "/" + String.join("/", list);    // /a/b/c
    }
}

/*
        Stack<String> stack = new Stack<>();
        String[] paths = path.split("/");
        for (String s : paths) {
            if (s.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (!s.equals(".") && !s.equals("")) {
                stack.push(s);
            }
        }
        String res = "";
        while (!stack.isEmpty()) {
            res = "/" + stack.pop() + res;  // 从右往左
        }
        if (res.length() == 0) {
            return "/";
        }
        return res;

 */