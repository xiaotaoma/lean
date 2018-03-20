package com.mxt.tree;

/**
 * Created by mxt on 18-3-9.
 * 数组结构树
 * 常见的树有以下几种分类：
     二叉树
     平衡二叉树
     B 树
     B+ 树
     哈夫曼树
     堆
     红黑树
     
     
     
     
     
 */
public class TreeNode {
    private Object data;//存储的数据
    private int mParent;//父节点位置

    public TreeNode(Object data, int mParent) {
        this.data = data;
        this.mParent = mParent;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getmParent() {
        return mParent;
    }

    public void setmParent(int mParent) {
        this.mParent = mParent;
    }


}
