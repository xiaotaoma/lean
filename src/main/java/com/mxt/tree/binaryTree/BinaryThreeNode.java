package com.mxt.tree.binaryTree;

/**
 * Created by mxt on 18-3-9.
 * 二叉树的三叉链表存储结构
 * 多添加了一个指向父结点的域
 *
 */
public class BinaryThreeNode<T extends Comparable> {
    private T data;
    private BinaryThreeNode<T> parent;
    private BinaryThreeNode<T> leftChild;
    private BinaryThreeNode<T> rightChild;

    public BinaryThreeNode(T data, BinaryThreeNode<T> parent, BinaryThreeNode<T> leftChild, BinaryThreeNode<T> rightChild) {
        this.data = data;
        this.parent = parent;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public BinaryThreeNode(T data) {
        this.data = data;
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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("BinaryThreeNode{");
        stringBuilder.append(data);
        stringBuilder.append(", parent=");
        if (parent != null && parent.data != null) {
            stringBuilder.append(parent.data.toString());
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
