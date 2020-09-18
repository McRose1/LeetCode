# 398. Random Pick Index
从一个 size 很大的数组里选取数字，随机性地输出该数字的 index 之一

## Reservior Samlping
当不清楚物体总量但又想计算其被选中概率时，使用水塘采样

水塘抽样可以实现随机选取，因为我们想要的结果是 n 个数字每个取得的概率为 1/n

{1,2,3,3,3} with target 3, you want to select 2,3,4 with a probability of 1/3 each.

- 2 : It's probability of selection is 1 * (1/2) * (2/3) = 1/3
  - 当只有 1 个 3 的时候 -> 1
  - 当第 2 个 3 进来的时候不选它 -> 1 - 1/2
  - 当第 3 个 3 进来的时候不选它 -> 1 - 1/3
- 3 : It's probability of selection is (1/2) * (2/3) = 1/3
- 4 : It's probability of selection is just 1/3

![398](/src/images/%23398.png)
