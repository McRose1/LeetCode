# 289 Game of Life
矩阵中 1 代表 live，0 代表 dead，每个 cell 和它的 8 个邻居有关

1. live cell 如果 8 个邻居中少于 2 个邻居 live，它也 dead
2. live cell 如果 8 个邻居中有 2 个或 3 个邻居 live，它继续 live
3. live cell 如果 8 个邻居中有超过 3 个邻居 live，它 dead
4. dead cell 如果 8 个邻居中有 3 个邻居 live，它 live

## Extra Space
因为这一轮新更新的 cell 不会影响当前轮的 cell，所以创建一个 board 的 copy，每次根据这个 original board 去改变 cell

## In-place 
通过复合状态免去对于 board 的 copy

根据规则，复合状态有 2 种：
1. dead -> live：用 2 表示
2. live -> dead：用 -1 表示

所以可以归为 2 种最终状态：
1. live -> > 0
2. dead -> else <= 0
