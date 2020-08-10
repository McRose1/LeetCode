# 409. Longest Palindrome 
给定一串可能包含大小写的字符串，求从中选取字符所能形成的最长回文字符串。

**This is case sensitive, for example "Aa" is not considered a palindrome here.**

思路是对的，如果字符出现次数是偶数，那么长度可以直接加上这个次数，如果出现次数是奇数，从中找到偶数个加入长度，最后整体再加上 1 放在中间
