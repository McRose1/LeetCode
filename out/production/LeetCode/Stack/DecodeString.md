# 394. Decode String
给定一个 String，里面只包含数字、字母、左右括号，数字代表后面跟的字符串的重复次数

3[a2[c]] -> accaccacc

## Stack
很明显是用 Stack 做，但是这题变态就变态在于需要 2 个 Stack
- 专门存重复次数的 Stack
- 专门存上一层（上一个括号）内的字符串

整个过程相当于就是碰到左括号就往 stack 中存储当前层的字符串，并且进入更深的一层

碰到右括号就往回退，从当前层退回浅的这一层，回退的过程中往结果里写

这题真的细节太复杂了，必须要自己写出来一次才能记住
