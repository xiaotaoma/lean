package com.mxt.tree;

/**
 * Created by mxt on 18-3-9.
 * 链表树实现
 */
public class LinkedTreeNode {
    private Object data;//存储的数据
    private LinkedTreeNode mParent;
    private LinkedTreeNode mChild;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public LinkedTreeNode getmParent() {
        return mParent;
    }

    public void setmParent(LinkedTreeNode mParent) {
        this.mParent = mParent;
    }

    public LinkedTreeNode getmChild() {
        return mChild;
    }

    public void setmChild(LinkedTreeNode mChild) {
        this.mChild = mChild;
    }
}
