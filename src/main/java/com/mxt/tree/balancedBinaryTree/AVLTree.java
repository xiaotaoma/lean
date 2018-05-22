package com.mxt.tree.balancedBinaryTree;

/**
 * 平衡二叉树 AVL实现 (红黑树、替罪羊树、Treap、伸展树)
 * 参考：https://blog.csdn.net/javazejian/article/details/53892797
 * 一棵AVL树是其每个结点的左子树和右子树的高度最多相差1的二叉查找树(空树的高度为-1)，这个差值也称为平衡因子
 *
 * 结点数量变化的操作可能导致平衡被改变
 * 无论是插入还是删除，只有那些从插入或者删除点到根结点的路径上的结点的平衡才有可能被改变
 * 因为只有这些结点的子树才可能发生变化，所以最终也只需针对这些点进行平衡修复操作即可。
 *
 *
 * 平衡因子：结点左子树的深度-结点右子树的深度
 *
 * 失衡的集中情形
 * 1、在结点X的左孩子结点的左子树中插入元素
 * 2、
 * 3、
 * 4、
 *
 */
public class AVLTree {


    /**
     * AVL树节点
     * @param <T>
     */
    private class AVLNode<T extends Comparable> {
        private AVLNode<T> left;//左节点
        private AVLNode<T> right;//右节点
        public T data;
        public int height;//当前节点高度,高度是指当前结点到叶子结点的最长路径

        public AVLNode(T data) {
            this(null,null,data);
        }

        public AVLNode(AVLNode<T> left, AVLNode<T> right, T data) {
            this(left,right,data,0);
        }

        public AVLNode(AVLNode<T> left, AVLNode<T> right, T data, int height) {
            this.left=left;
            this.right=right;
            this.data=data;
            this.height = height;
        }

        /**
         *
         * 插入一个新的结点到根结点的左子树的左子树，导致根结点的平衡
         * 左左单旋转
         *
         * @param x
         * @return
         */
        private AVLNode<T> singleRotateLeft(AVLNode<T> x) {
            return null;
        }

        private int height(AVLNode<T> node) {
            return node == null ? -1:node.height;
        }


    }
}
