# 153. Find Minimum in Rotated Sorted Array

哈哈这个题也太熟悉了吧，从旋转过的无 duplicate 元素里用二分法找元素

略微不同的是以前那题是找 target，这类题是找 minimum

其实比找 target 那题还要简单

但是还是有点把握不住细节 -> 其实压根就不用看后面的详细解释

**这个题的套路就是每次拿中值和最右值进行比较，因为最右值永远小于最左值

相当于每次二分将数组分为 [left, mid] 和 [mid + 1, right]

中值大于最右值，就取 [mid + 1, right]

小于，就取 [left, mid]**

LC 官方：

3 种情况：

1. 左<中，中<右：没有旋转，最小值在最左边，收缩右边界

           右
         
         中
        
       左

2. 左>中，中<右：有旋转，最小值在左半边，收缩右边界

        左
        
              右
           
          中
    
3. 左<中，中>右：有旋转，最小值在右半边，收缩左边界

            中

        左

                右

4. 左>中，中>右：单调递减，不可能出现
        
        左

             中
    
                  右
        
我们发现：

如果中<右，则最小值在左半边，收缩右边界

如果中>右，则最小值在右半边，收缩左边界

通过比较中值和右值，可以确定最小值的位置范围，从而决定边界收缩的方向。

而情况 1 和情况 3 都是左<中，但是最小值位置范围却不同，这说明如果只比较左值和中值，不能确定最小值的位置范围。

while循环里的细节问题：

循环不变式是 left < right，并且要保证左闭右闭区间里面始终套住最小值












