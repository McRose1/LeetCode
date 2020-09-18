# 17. Letter Combinations of a Phone Number
关于手机的回溯题

首先需要构建一个 map

2 -> abc

3 -> def

4 -> ghi

5 -> jkl

6 -> mno

7 -> pqrs

8 -> tuv

9 -> wxyz

本题 dfs 函数中最重要的参数是遍历输入 String 的下标，也就是 index，初始化为 0 传入，每次调用 dfs 函数时 +1，退出条件是 index == String.length()

中间态用 StringBuilder 来传递，最后存入结果再转换成 String
