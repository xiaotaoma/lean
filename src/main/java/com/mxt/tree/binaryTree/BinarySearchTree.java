package com.mxt.tree.binaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by mxt on 18-3-9.
 * 二叉查找树 Binary Sort Tree
 *  1、若它的左子树不为空，则左子树上所有结点的值均小于它的根结点的值；
    2、若它的右子树不为空，则右子树上所有结点的值均大于它的根结点的值；
    3、它的左、右子树也分别为二叉查找树。
 */
public class BinarySearchTree {
    /**
     * 二叉查找树的插入
     * 当插入一个节点时，判断此节点是否空，空则直接插入
     * 根节点非空时，需要与跟节点比较，小于根节点插入左子树，大于根节点插入右子树
     * @param subTree
     * @param insertNode
     */
    public static BinaryThreeNode insert(BinaryThreeNode subTree, BinaryThreeNode insertNode) {
        if (subTree == null) {
            subTree = insertNode;
        }else {
            int i = insertNode.getData().compareTo(subTree.getData());
            if (i < 0) {
                BinaryThreeNode insert = insert(subTree.getLeftChild(), insertNode);
                insert.setParent(subTree);
                subTree.setLeftChild(insert);
            }else {
                BinaryThreeNode insert = insert(subTree.getRightChild(), insertNode);
                insert.setParent(subTree);
                subTree.setRightChild(insert);
            }
        }
        return subTree;
    }

    /**
     * 二叉查找树查询
     * 与根节点比较，比根节点小向左子树查询，比根节点大向右子树查询
     * @param subTree
     * @param searchNode
     * @return
     */
    public static BinaryThreeNode search(BinaryThreeNode subTree, BinaryThreeNode searchNode) {
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

    /**
     * 二叉查找树的删除
     * 1、删除的节点没有左子树和右子树，直接删除
     * 2、删除的节点有左子树或右子树，将节点的左子树或右子树指向父节点
     * 3、删除的节点有左子树和右子树，搜索节点下边的最小节点，放入删除节点的位置
     * @param root
     * @param deleteNode
     */
    public static BinaryThreeNode delete(BinaryThreeNode root, BinaryThreeNode deleteNode) {
        BinaryThreeNode rightChild = deleteNode.getRightChild();
        BinaryThreeNode leftChild = deleteNode.getLeftChild();
        BinaryThreeNode parent = deleteNode.getParent();
        if (leftChild == null && rightChild == null) {
            //删除的节点没有左子树和右子树，直接删除
            if (parent == null) {//删除的节点是根节点
                root = null;
            }else if (parent.getLeftChild() == deleteNode){
                parent.setLeftChild(null);
            }else {
                parent.setRightChild(null);
            }
        }else if ((leftChild != null && rightChild == null) || (rightChild != null && leftChild == null)) {
            //删除的节点有左子树或右子树，将节点的左子树或右子树指向父节点
            if (parent == null) {//删除的节点是根节点
                if (leftChild != null) {
                    root = leftChild;
                    root.setParent(null);
                }else {
                    root = rightChild;
                    root.setParent(null);
                }
            }else if (parent.getLeftChild() == deleteNode){//删除的节点是父节点的左孩子
                if (leftChild != null) {
                    parent.setLeftChild(leftChild);
                    leftChild.setParent(parent);
                }else {
                    parent.setLeftChild(rightChild);
                    rightChild.setParent(parent);
                }
            }else if (parent.getRightChild() == deleteNode){
                if (leftChild != null) {
                    parent.setRightChild(leftChild);
                    leftChild.setParent(parent);
                }else {
                    parent.setRightChild(rightChild);
                    rightChild.setParent(parent);
                }
            }
        }else {
            //删除的节点有左子树和右子树，搜索节点下边的最小节点，放入删除节点的位置
            BinaryThreeNode min = getMin(deleteNode);

            min.setRightChild(rightChild);
            rightChild.setParent(min);
            min.setLeftChild(leftChild);
            leftChild.setParent(min);

            BinaryThreeNode parent1 = min.getParent();
            if (parent1.getLeftChild().equals(min)) {
                parent1.setLeftChild(null);
            }else {
                parent1.setRightChild(null);
            }

            if (parent.getLeftChild() == deleteNode) {
                parent.setLeftChild(min);
            }else {
                parent.setRightChild(min);
            }
        }
        return root;
    }

    /**
     * 二叉查找树的删除
     * 1、删除的节点没有左子树和右子树，直接删除
     * 2、删除的节点有左子树或右子树，将节点的左子树或右子树指向父节点
     * 3、删除的节点有左子树和右子树，搜索节点下边的最小节点，放入删除节点的位置
     * @param root
     * @param
     */
    private static BinaryThreeNode deleteNode(BinaryThreeNode root, BinaryThreeNode delete) {
        if (delete == null) {
            return root;
        }
        BinaryThreeNode leftChild = delete.getLeftChild();
        BinaryThreeNode rightChild = delete.getRightChild();
        BinaryThreeNode parent = delete.getParent();
        if (leftChild == null && rightChild == null) {
            //删除的节点没有左子树和右子树，直接删除
            if (parent.getLeftChild() == delete) {
                //删除节点是父节点的左孩子，父节点设置左孩子为null，删除
                parent.setLeftChild(null);
            }else if (parent.getRightChild() == delete) {
                //删除节点是父节点的右孩子，父节点设置右孩子为null，删除
                parent.setRightChild(null);
            }
        }else if (leftChild == null && rightChild != null) {
            //删除节点有右子树，无左子树，将右孩子指向父节点
            rightChild.setParent(parent);
            if (delete == parent.getLeftChild()) {
                //删除节点是父节点的左孩子，
                parent.setLeftChild(rightChild);
            }else if (delete == parent.getRightChild()) {
                //删除节点是父节点的右孩子，
                parent.setRightChild(rightChild);
            }
        }else if (rightChild == null && leftChild != null) {
            //删除节点有左子树，无右子树，将左孩子指向父节点
            leftChild.setParent(parent);
            if (delete == parent.getLeftChild()) {
                parent.setLeftChild(leftChild);
            }else if (delete == parent.getRightChild()) {
                parent.setRightChild(leftChild);
            }
        }else {
            //删除节点有左子树和右子树，查找最小节点
            BinaryThreeNode min = getMin(delete);

            BinaryThreeNode parent1 = min.getParent();
            if (min == parent1.getLeftChild()) {
                parent1.setLeftChild(null);
            }else if (min == parent1.getRightChild()) {
                parent1.setRightChild(null);
            }

            min.setParent(parent);
            if (delete == parent.getRightChild()) {
                parent.setRightChild(min);
            }else if (delete == parent.getLeftChild()) {
                parent.setLeftChild(min);
            }
            min.setLeftChild(leftChild);
            min.setRightChild(rightChild);
        }
        return root;
    }

    /**
     * 查询最小节点
     * @param binaryThreeNode
     * @return
     */
    public static BinaryThreeNode getMin(BinaryThreeNode binaryThreeNode) {
        if (binaryThreeNode == null) {
            return null;
        }
        if (binaryThreeNode.getLeftChild() != null) {
            BinaryThreeNode min = getMin(binaryThreeNode.getLeftChild());
            if (min != null) {
                return min;
            }
        }
        return binaryThreeNode;
    }

    /**
     * 二叉查找树的前序遍历
     * 根节点->左子树->右子树
     *
     * @param root
     */
    public static void preOrder(BinaryThreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root);
        preOrder(root.getLeftChild());
        preOrder(root.getRightChild());
    }

    /**
     * 二叉查找树的中序遍历
     * 左子树->根节点->右子树
     * @param root
     */
    public static void inOrder(BinaryThreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.getLeftChild());
        System.out.println(root);
        inOrder(root.getRightChild());
    }

    /**
     * 二叉查找树的后序遍历
     * 左子树->右子树->根节点
     * @param root
     */
    public static void postOrder(BinaryThreeNode root) {
        if (root == null) {
            return;
        }
        preOrder(root.getLeftChild());
        preOrder(root.getRightChild());
        System.out.println(root);
    }

    public static void main(String[] args) {
        /*BinaryThreeNode<Integer> root = new BinaryThreeNode<Integer>(1);
        BinaryThreeNode<Integer> binaryThreeNode = new BinaryThreeNode<Integer>(8);
        insert(root, binaryThreeNode);

        BinaryThreeNode<Integer> binaryThreeNode1 = new BinaryThreeNode<Integer>(56);
        insert(root, binaryThreeNode1);

        BinaryThreeNode<Integer> binaryThreeNode2 = new BinaryThreeNode<Integer>(34);
        insert(root, binaryThreeNode2);

        BinaryThreeNode<Integer> binaryThreeNode3 = new BinaryThreeNode<Integer>(76);
        insert(root, binaryThreeNode3);

        BinaryThreeNode<Integer> binaryThreeNode4 = new BinaryThreeNode<Integer>(23);
        insert(root, binaryThreeNode4);

        BinaryThreeNode<Integer> binaryThreeNode5 = new BinaryThreeNode<Integer>(45);
        insert(root, binaryThreeNode5);

        BinaryThreeNode<Integer> binaryThreeNode6 = new BinaryThreeNode<Integer>(11);
        insert(root, binaryThreeNode6);

        BinaryThreeNode<Integer> binaryThreeNode7 = new BinaryThreeNode<Integer>(88);
        insert(root, binaryThreeNode7);*/
        Random random = new Random();
        BinaryThreeNode<Integer> root = new BinaryThreeNode(random.nextInt(100));

        List<BinaryThreeNode> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            int i1 = random.nextInt(100);
            BinaryThreeNode<Integer> node = new BinaryThreeNode(i1);
            insert(root, node);
            list.add(node);
        }
        inOrder(root);

        BinaryThreeNode binaryThreeNode = list.get(random.nextInt(list.size()));
        System.out.println("delete:"+binaryThreeNode);
        BinaryThreeNode delete = deleteNode(root, binaryThreeNode);

        inOrder(delete);
    }
}
