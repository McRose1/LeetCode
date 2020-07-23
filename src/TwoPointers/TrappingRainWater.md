# 42. Trapping Rain Water
给定一个数组代表柱形图，求该柱形图的蓄水量

所有的方法利用的都是同一个算法思想，也就是：

当前位置可以蓄水的量，是由当前位置左边最长和右边最长的柱子中的较短者决定的，这个长度减去当前位置柱子的长度，就是这个单元的蓄水量

## Brute Force 
算法：
1. 初始化 ans = 0
2. 从左到右遍历 height；
  - 初始化 left_max = 0, right_max = 0
  - 从 height[0] 到当前位置寻找最大值 left_max = max(height[j], left_max)
  - 从当前位置到 height 末端寻找最大值 right_max = max(height[j], right_max)
  - ans = ans + min(left_max, right_max) - height[i]

## Stack
积水只能在低洼处形成，当后面的柱子高度比前面的低时，是无法接雨水的。所以使用单调递减栈储存可能储水的柱子，当找到一根比前面高的柱子，就可以计算接到的雨水

算法：
1. 使用栈 st 来存储柱子的索引下标
2. 从左到右遍历 height
  - 当栈非空且 height[i] > height[st.peek()]
    - 意味着栈中元素可以被弹出，弹出栈顶元素。top = st.pop()
    - 计算积水宽度，即当前元素和栈顶元素的距离，准备进行填充操作。distance = i - st.peek() - 1
    - 找出积水高度。bounded_height = min(height[i], height[st.peek()] - height[top])
    - 往答案中累加积水量。ans += distance * bounded_height
  - 将当前索引下标入栈
  - 将 i 移动到下个位置

## DP
提前储存每个位置上所有左边柱子高度的最大值和所有右边柱子高度的最大值

## Two Pointers 
DP 方法的空间复杂度优化，从 DP 方法我们注意到只要 left_max_arr[i] > right_max_arr[i]，积水的高度将由 right_max_arr 决定，同理如果 right_max_arr[i] > left_max_arr[i]，积水的高度将由 right_max_arr 决定

所以我们可以认为如果一端有更高的条形块（例如右端），积水的高度依赖于当前方向的高度（从左到右）。

当我们发现另一侧（右侧）的条形块高度不是最高的，我们则开始从方向遍历（从右到左）。

算法：
1. 初始化 2 个指针 left = 0 和 right = height.length - 1
2. 当 left < right 时向中间移动 2 个指针：
  - 如果 height[left] < height[right] 说明储水量依赖于 height[left] 高度（可能构成低洼的右边界很大）
    - 如果 height[left] > left_max 说明不构成低洼，left_max = height[left]
    - 如果 height[left] <= left_max 说明构成低洼，往答案中累加积水量。ans += left_max - height[left] 
    - 前进 left：left++
  - 如果 height[left] >= height[right] 说明储水量依赖于 height[right] 高度（可能构成低洼的左边界很大）
    - 如果 height[right] > right_max 说明不构成低洼，right_max = height[right]
    - 如果 height[right] <= right_max 说明构成低洼，往答案中累加积水量。ans += right_max - height[right] 
    - 前进 right：left--


