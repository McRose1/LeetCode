# 1. Two Sum
经典题目：从数组中找出两个数字的加和为 target，只存在一个这样的解，返回这两个数的 index

用 HashMap，key 存（target - 遍历到的数字），value 存该数字在该数组的 index

循环内部每次先判断 map 里是否有这个 key，有的话说明存在一个数和它配对

没有的话，就把 target 和这个数的差值存入 map 中




