# 367. Valid Perfect Square 
不同 sqrt() 函数，判断输入的正数是否为完全平方数：1, 4, 9, 16, 25...

## 二分法
左边界为 2，右边界为 num/2

**注意 while(left <= right)**

还有就是 mid * mid == num 会溢出的问题，有 2 种解决方案：
1. 所有参数都设为 long
2. mid == num / mid && num % mid == 0

## 牛顿迭代法
X(k+1) = 1/2 (Xk + num/Xk)

- 取 num/2 作为初始近似值
- 当 x * x > num，用牛顿迭代法计算下一个近似值：x = 1/2 (x + num/x)
- 返回 x * x = num
