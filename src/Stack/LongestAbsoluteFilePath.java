package Stack;

/*  388. Longest Absolute File Path
    Suppose we abstract our file system by a string in the following manner:
    The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:
    dir
        subdir1
        subdir2
            file.ext
    The directory dir contains an empty sub-directory subdir1 and a sub-directory subdir2 containing a file file.ext.
    The string "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" represents:
    dir
        subdir1
            file1.ext
            subsubdir1
        subdir2
            subsubdir2
                file2.ext
    The directory dir contains two sub-directories subdir1 and subdir2.
    subdir1 contains a file file1.ext and an empty second-level sub-directory subsubdir1.
    subdir2 contains a second-level sub-directory subsubdir2 containing a file file2.ext.

    We are interested in finding the longest (number of characters) absolute path to a file within our file system.
    For example, in the second example above, the longest absolute path is "dir/subdir2/subsubdir2/file2.ext",
    and its length is 32 (not including the double quotes).

    Given a string representing the file system in the above format,
    return the length of the longest absolute path to file in the abstracted file system.
    If there is no file in the system, return 0.

    Note:
    The name of a file contains at least a . and an extension.
    The name of a directory or sub-directory will not contain a ..

    Time complexity required: O(n) where n is the size of the input string.
    Notice that a/aa/aaa/file1.txt is not the longest file path,
    if there is another path aaaaaaaaaaaaaaaaaaaaa/sth.png.
 */

import java.util.Stack;

public class LongestAbsoluteFilePath {
    public int lengthLongestPath(String input) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int res = 0;
        for (String s : input.split("\n")) {    // "dir, \tsubdir1, \tsubdir2, \t\tfile.ext"
            int level = s.lastIndexOf("\t") + 1;  // dir=0; \tsubdir1 -> level = 1; 1; 2
            while (level + 1 < stack.size()) {        // 2<3
                stack.pop();                          // 12
            }// remove "\t\t" and add "/" as the delimiter
            int len = stack.peek() + s.length() - level + 1;    // len = 3-0+1=4; 4+8-1+1=12; 4+8=12; 12+10-2+1=21
            stack.push(len);                        // 0,4; 0,4,12; 0,4,12; 0,4,12,21
            // only update the res when a file is discovered
            if (s.contains(".")) {
                // remove the "/" at the end of file
                res = Math.max(res, len - 1);       // 21-1=20
            }
        }
        return res;
    }
}
