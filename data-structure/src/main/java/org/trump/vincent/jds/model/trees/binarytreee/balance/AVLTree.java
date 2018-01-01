package org.trump.vincent.jds.model.trees.binarytreee.balance;

import org.trump.vincent.jds.model.trees.binarytreee.BinarySortedTree;

/**
 * Created by Vincent on 2017/12/14 0014.
 */
public interface AVLTree<E extends Comparable<E>> extends BinarySortedTree<E>{

    boolean isAVLTree(AVLNode<E> current);

    interface AVLNode<E>{
        E getData();

        AVLNode setData(E data);

        AVLNode<E> getParent();

        AVLNode setParent(AVLNode<E> parent);

        AVLNode<E> getLeft();
        AVLNode setLeft(AVLNode<E> left);

        AVLNode<E> getRight();

        AVLNode setRight(AVLNode<E> right);

        int getHeight();

        AVLNode setHeight(int height);
    }
}
