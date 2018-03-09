package com.mxt.tree.binaryTree;

/**
 * Created by mxt on 18-3-9.
 * 二叉查找树 Binary Sort Tree
 *  1、若它的左子树不为空，则左子树上所有结点的值均小于它的根结点的值；
    2、若它的右子树不为空，则右子树上所有结点的值均大于它的根结点的值；
    3、它的左、右子树也分别为二叉查找树。
 */
public class BinarySearchTree<T extends Comparable> {
    private T data;
    private BinaryThreeNode<T> parent;
    private BinaryThreeNode<T> leftChild;
    private BinaryThreeNode<T> rightChild;

    public BinarySearchTree(T data, BinaryThreeNode<T> parent, BinaryThreeNode<T> leftChild, BinaryThreeNode<T> rightChild) {
        this.data = data;
        this.parent = parent;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BinaryThreeNode<T> getParent() {
        return parent;
    }

    public void setParent(BinaryThreeNode<T> parent) {
        this.parent = parent;
    }

    public BinaryThreeNode<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BinaryThreeNode<T> leftChild) {
        this.leftChild = leftChild;
    }

    public BinaryThreeNode<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(BinaryThreeNode<T> rightChild) {
        this.rightChild = rightChild;
    }

    /**
     * 二叉查找树的插入
     * 当插入一个节点时，判断此节点是否空，空则直接插入
     * 根节点非空时，需要与跟节点比较，小于根节点插入左子树，大于根节点插入右子树
     * @param subTree
     * @param insertNode
     */
    public boolean insert(BinaryThreeNode subTree, BinaryThreeNode insertNode) {
        if (subTree == null) {
            subTree = insertNode;
        }else {
            if (subTree.getLeftChild() == null) {
                subTree.setLeftChild(insertNode);
            }else if (subTree.getLeftChild() != null && subTree.getRightChild() == null){
                subTree.setRightChild(insertNode);
            }else {
                int i = insertNode.getData().compareTo(subTree.getLeftChild().getData());
                if (i < 0) {
                    return insert(subTree.getLeftChild(), insertNode);
                }else {
                    return insert(subTree.getRightChild(), insertNode);
                }
            }
        }
        return true;
    }

    /**
     * 二叉查找树查询
     * 与根节点比较，比根节点小向左子树查询，比根节点大向右子树查询
     * @param subTree
     * @param searchNode
     * @return
     */
    public BinaryThreeNode search(BinaryThreeNode subTree, BinaryThreeNode searchNode) {
        if (subTree == null) {
            return null;
        }
        int i = subTree.getData().compareTo(searchNode.getData());
        if (i > 0) {
            return search(subTree.getLeftChild(), searchNode);
        }else if(i < 0){
            return search(subTree.getRightChild(), searchNode);
        }else {
            return subTree;
        }
    }

    public void delete(BinaryThreeNode parentNode, BinaryThreeNode deleteNode) {
        if (parentNode == null) {
            if (deleteNode.getLeftChild() == null || deleteNode.getRightChild() == null) {
                //左右子树都空

            }else if (deleteNode.getLeftChild() != null || deleteNode.getRightChild() != null) {
                //存在左子树或右子树
                if (deleteNode.getLeftChild() != null) {
                    //存在左子树

                }else {//存在右子树

                }
            }else {

            }
        }
    }
}
