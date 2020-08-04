# 385. Mini Parser
给定一个 String 形式的嵌套列表，以 NestedInteger 形式输出

## Stack
相当于用 stack 来实现整个遍历过程，最先 push 的是最外层的 NestedInteger，慢慢 push 进更深一层的 NestedInteger

碰到左括号，代表进入里面一层，新建一个 NestedInteger，并且 add 到外层 NestedInteger，最后入栈

碰到右括号，说明这一层的 NI 已经遍历完毕，直接出栈

碰到逗号，会有 2 种可能：
- 数字后面跟的逗号: [123,[456]]，把前面的数字 append 到上一层 NI 中
- 右括号后面跟的逗号: [[123,456],789]，直接忽略
