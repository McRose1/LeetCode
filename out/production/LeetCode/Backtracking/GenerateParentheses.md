# 22. Generate Parentheses
给定一个整数 n，输出所有可能的括号组合

这题挺有意思的，虽然是回溯题的壳子，但是有自己特殊要求

其实回溯题的模板都一样，唯一有区别的就是 dfs 函数的传入参数

这题特有的 2 个参数，open(left) 和 close(right)，分别记录左括号和右括号的数量

并且左右括号匹配会有一些约束条件：

1. 顺序一定是先加左括号，再加考虑右括号 

2. 左括号数量 <= n，如果还没有到 n，就可以继续加左括号

3. 右括号数量 <= 左括号数量，最终是等于，所以如果右括号数量比左括号数量少，就可以继续加右括号

