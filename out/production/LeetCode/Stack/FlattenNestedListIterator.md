# 341. Flatten Nested List Iterator
给定一个嵌套 list，实现一个迭代器将其摊平后输出

和 #339 是一个题目背景

## Stack & Queue
其实就是一个原则，满足先进先出，Stack 和 Queue 都能实现，只不过里面的元素需要存的是 Integer，这样迭代器调用函数时就很方便

如果是 Stack<NestedInteger>，那么 hasNext() 和 next() 就会比较复杂
