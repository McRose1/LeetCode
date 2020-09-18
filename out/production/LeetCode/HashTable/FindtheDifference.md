# 389. Find the Difference
很简单的题目，给定 2 个字符串 s 和 t，t 为 s 随机打乱后多加了一个元素，找出这个元素

## HashTable 
用 size 为 26 的数组作为哈希表检查

## Bit Manipulation
XOR 所有的 character，注意初始化的时候有一个技巧，因为 t 的长度比 s 多 1，所以直接拿 t 的最后一个 character 作为初始值来 XOR 所有 s 的字符和 t 的剩余字符
