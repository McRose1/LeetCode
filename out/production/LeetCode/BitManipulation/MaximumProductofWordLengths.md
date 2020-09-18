# 318. Maximum Product of Word Lengths
给定一批单词，从中找出 2 个单词满足相互之间没有共同的字母，求这样 2 个单词长度乘积的最大值

## Bit Manipulation 
bitmask of 26 bits 

为每一个单词生成一个 bitmask，都存在 masks[] 数组里

**masks[i] |= 1 << (words[i].charAt(j) - 'a')**

比较 2 个单词其实很简单，就是写 2 个 for 循环，如果 bitmask 之间做与运算为 0 说明这 2 个单词没有共同的字母，就可以算长度乘积

## 暴力解
将每个单词与其他所有单词一一比较。如果两个单词没有公共字母，则更新 max

逐个检查第一个单词的每个字母是否出现在第二个单词中

贼慢，可以用位操作优化
