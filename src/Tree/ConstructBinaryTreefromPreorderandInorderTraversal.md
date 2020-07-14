# 105. Construct Binary Tree from Preorder and Inorder Traversal
给定前序遍历和中序遍历，还原二叉树的样子

国内笔试题经常做到这样的选择题

## 分而治之
- 结合“前序遍历序列”和“中序遍历序列”的定义
- 前序遍历的第 1 个结点一定是二叉树的根节点
- 在中序遍历中，根结点把中序遍历序列分成了 2 个部分，左边部分构成了二叉树的根结点的左子树，右边部分构成了二叉树的根结点的右子树

![#105](/src/images/%23105.png)

### 递归
只要我们在中序遍历中定位到根节点，那么我们就可以分别知道左子树和右子树中的节点数目。

由于同一颗子树的前序遍历和中序遍历的长度显然是相同的，因此我们就可以对应到前序遍历的结果中。

这样以来，我们就知道了左子树的前序遍历和中序遍历结果，以及右子树的前序遍历和中序遍历结果，我们就可以递归地对构造出左子树和右子树，再将这两颗子树接到根节点的左右位置

### 迭代
下次再回来看

https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/solution/cong-qian-xu-yu-zhong-xu-bian-li-xu-lie-gou-zao-9/
