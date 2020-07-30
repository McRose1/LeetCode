# 339. Nested List Weight Sum
给定一个嵌套 list，求 数字 x 嵌套深度 的加和

还提供了一个接口叫 NestedInteger，结果发现给个接口就不知道怎么做了。。。

看了答案才想起来

主要就是要知道哪里使用接口提供的 3 个 function

- isInteger()
- getInteger()
- getList()

## DFS
在 DFS 循环中，遍历 nestedList 里的 nestedInteger
- 如果 isInteger，那么没有下一层的 DFS，直接把深度和当前值相乘加入总和
- 如果 isList，那么要进入下一层的 DFS，

**要记得每次 DFS 的开头一定要 reset sum = 0！！！**

