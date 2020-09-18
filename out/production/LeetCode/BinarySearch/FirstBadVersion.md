# 278. First Bad Version
很简单的二分法题

直接用 while (left < right)

拿到 mid 以后判断 mid 是否是 bad version
- 如果是，right = mid
- 如果不是，left = mid + 1

因为是 first bad version，那肯定是找最前面的，那么最后就是 return left
