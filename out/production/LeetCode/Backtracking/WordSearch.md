# 79. Word Search

披着 matrix 皮的 backtracking 的狼

**需要一个 visited 数组 --> 其实不需要，直接修改 matrix 里的值就可以（先用 temp 保存原始值，用 '#' 代替），backtrack 的时候还原该值（把 temp 写回）**

**需要一个 dirs 数组 --> 也不需要，递归传参数的时候引入横纵坐标就可以，i+1, i-1, j+1, j-1 就可以遍历四个方向**

输入格式复杂的 backtrack 就需要在 main function 多下功夫

比如 for 循环，遍历每一个点为起点的可能性

**这题不要忘记回溯，因为两个 for 循环里面相当于找所有可能的起点，以一个位置为起点进行 DFS 结束之后，要将对矩阵做的标记还原，以供下一个起点 DFS 使用**

backtrack 函数里记得用一个 boolean 遍历来存结果
