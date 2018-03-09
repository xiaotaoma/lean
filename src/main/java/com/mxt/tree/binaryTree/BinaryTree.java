package com.mxt.tree.binaryTree;

import java.util.Stack;

/**
 * Created by mxt on 18-3-9.
 * 二叉树
 *
 */
public class BinaryTree {
    /**
     * 链表存储的二叉树节点
     */
    private static class BinaryTreeNode {
        private int key;
        private String data;
        private boolean isVisited = false;
        private BinaryTreeNode leftChild;
        private BinaryTreeNode rightChild;

        public BinaryTreeNode(int key, String data) {
            this.key = key;
            this.data = data;
        }

        public BinaryTreeNode() {

        }
    }

    private BinaryTreeNode root;

    public BinaryTree(BinaryTreeNode root) {
        this.root = new BinaryTreeNode(1, "rootNode(A)");
    }

    /**
     * 树的高度
     * @return
     */
    private int height() {
        return height(root);
    }

    /**
     * 树的节点个数
     * @return
     */
    private int size() {
        return size(root);
    }
    /**
     * 求树的高度
     * @param treeNode
     * @return
     */
    private int height(BinaryTreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }else {
            int i = height(treeNode.leftChild);
            int j = height(treeNode.rightChild);
            return i>j ? i+1: j+1;
        }
    }

    /**
     * 树节点个数
     * @param treeNode
     * @return
     */
    private int size(BinaryTreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }else {
            return 1+size(treeNode.leftChild)+size(treeNode.rightChild);
        }
    }

    /**
     * 查询父节点
     * @param treeNode
     * @param element
     * @return
     */
    private BinaryTreeNode parent(BinaryTreeNode treeNode, BinaryTreeNode element) {
        if (treeNode == null) {
            return null;
        }
        if (treeNode.leftChild == element || treeNode.rightChild == element) {
            return treeNode;
        }else {
            BinaryTreeNode parent = parent(treeNode.leftChild, element);
            if (parent != null) {
                return parent;
            }else {
                return parent(treeNode.rightChild, element);
            }
        }
    }

    public BinaryTreeNode getLeftChild(BinaryTreeNode element) {
        return element == null ? null : element.leftChild;
    }
    public BinaryTreeNode getRightChild(BinaryTreeNode element) {
        return element == null ? null : element.rightChild;
    }
    public BinaryTreeNode getRoot() {
        return root;
    }
    public void visited(BinaryTreeNode binaryTreeNode){
        binaryTreeNode.isVisited = true;
        System.out.println("key:" + binaryTreeNode.key + "--name:"+binaryTreeNode.data);
    }

    /**
     * 二叉树前序遍历
     * 根节点->左子树->右子树
     * @param binaryTreeNode
     */
    public void preOrder(BinaryTreeNode binaryTreeNode) {
        if (binaryTreeNode != null) {
            visited(binaryTreeNode);
            preOrder(binaryTreeNode.leftChild);
            preOrder(binaryTreeNode.rightChild);
        }
    }

    /**
     * 前序遍历非递归实现
     * 根节点->左子树->右子树
     * @param binaryTreeNode
     */
    public void nonRecPreOrder(BinaryTreeNode binaryTreeNode) {
        Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
        BinaryTreeNode node = binaryTreeNode;
        while (node != null || stack.size() > 0) {
            visited(node);
            stack.push(node);
            node = binaryTreeNode.leftChild;
            if (stack.size() > 0) {
                node = stack.pop();
                node = node.leftChild;
            }
        }
    }

    /**
     * 二叉树中序遍历
     * 左子树->根节点->右子树
     */
    public void inOrder(BinaryTreeNode binaryTreeNode) {
        if (binaryTreeNode != null) {
            inOrder(binaryTreeNode.leftChild);
            visited(binaryTreeNode);
            inOrder(binaryTreeNode.rightChild);
        }
    }

    /**
     * 二叉树后序遍历
     * 左子树->右子树->根节点
     * @param binaryTreeNode
     */
    public void postOrder(BinaryTreeNode binaryTreeNode) {
        if (binaryTreeNode != null) {
            postOrder(binaryTreeNode.leftChild);
            postOrder(binaryTreeNode.rightChild);
            visited(binaryTreeNode);
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode binaryTreeNode = new BinaryTreeNode(1, "A");
        BinaryTreeNode binaryTreeNode1 = new BinaryTreeNode(2, "B");
        BinaryTreeNode binaryTreeNode2 = new BinaryTreeNode(3, "C");
        BinaryTreeNode binaryTreeNode3 = new BinaryTreeNode(4, "D");
        BinaryTreeNode binaryTreeNode4 = new BinaryTreeNode(5, "E");
        BinaryTreeNode binaryTreeNode5 = new BinaryTreeNode(6, "F");
        binaryTreeNode.leftChild = binaryTreeNode1;
        binaryTreeNode.rightChild = binaryTreeNode2;
        binaryTreeNode1.leftChild = binaryTreeNode3;
        binaryTreeNode1.rightChild = binaryTreeNode4;
        binaryTreeNode2.rightChild = binaryTreeNode5;

        BinaryTree binaryTree = new BinaryTree(binaryTreeNode);
        //binaryTree.postOrder(binaryTreeNode);

        //binaryTree.inOrder(binaryTreeNode);

        binaryTree.preOrder(binaryTreeNode);
    }

}
