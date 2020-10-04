# 1427. Perform String Shifts 
简单题，算法层面没有难度

只需要记录最后的位移即可，负数就是右移，正数就是左移。

有一个技巧，用 pos % len + len 来表示右移时数值为负数的情况，因为右移x相当于左移len - x

然后在外面再套一个 % len 来避免越界

当然也可以分类讨论：
- 正数：pos % len
- 负数：pos % len + len
