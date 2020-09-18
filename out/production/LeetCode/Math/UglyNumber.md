# 263. Ugly Number
因子只有 2、3、5 的正数

num = 2^i * 3^j * 5^k

其实就是用 while 循环从 5、3、2 一个一个整除过来，一趟下来最后如果 num == 1，说明为 ugly number
