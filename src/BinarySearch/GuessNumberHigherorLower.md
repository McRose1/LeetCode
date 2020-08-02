# 374. Guess Number Higher or Lower
没什么好说的，很明显就是用二分法

#367 简化版

## 二分法
while (left <= right)

## 三分法
int mid1 = left + (right - left) / 3;
            
int mid2 = right - (right - left) / 3;

int res1 = guess(mid1);
            
int res2 = guess(mid2);

**看起来三分查找会比二分查找更快，但是为什么二分查找使用得更广泛？**

因为最坏情况下三分查找比较次数比二分查找最坏情况要多
