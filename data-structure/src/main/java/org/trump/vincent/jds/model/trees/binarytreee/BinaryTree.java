package org.trump.vincent.jds.model.trees.binarytreee;

import org.trump.vincent.jds.model.trees.Tree;

/**
 * Created by Vincent on 2017/11/30 0030.
 */

/**
 * Binary Tree 的操作，可分为前序、中序、后序 三种
 * 即，插入与遍历 均可以上三种方式进行，而且 二叉树的创建是调用插入方法实现的；
 * 当然，所有树的插入（构造/ 新建）和查询都可以上面的方式进行；
 * 另外，树的遍历还有一种 层次遍历方法（借助于队列，上层出列完成访问，其子节点入列）
 * @param <E>
 */
public interface BinaryTree<E> extends Tree<E> {

    interface Node<E>{
        E getData();
        Node<E> setData(E data);

        Node<E> getParent();
        Node<E> setParent(Node<E> parent);

        Node<E> getLeft();
        Node<E> setLeft(Node<E> left);

        Node<E> getRight();
        Node<E> setRight(Node<E> right);
    }


    void preOrderTraversal(Node<E> current);

    void inOrderTraversal(Node<E> current);

    void postOrderTraversal(Node<E> current);
}
