# 829. Consecutive Numbers Sum
就是纯数学题，LC 那个方法太复杂了没看懂

最简单的：
N = k + k+1 + k+2 +⋯+ k+(i-1) -> N = k*i + i(i-1)/2 -> N - i(i-1)/2 = k * i

N > i(i-1)/2; (N - i(i-1)/2) 是 i 的 k 倍
