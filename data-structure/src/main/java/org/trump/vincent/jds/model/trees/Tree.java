package org.trump.vincent.jds.model.trees;

import org.trump.vincent.jds.model.trees.binarytreee.BinaryTree;
import org.trump.vincent.jds.model.trees.impl.BinaryTreeImpl;

/**
 * Created by Vincent on 2017/11/30 0030.
 */

/**
 * 一般树的遍历：前序、后序，层次 [ 无中序遍历]
 * @param <E>
 */
public interface Tree<E> {

    void preOrderTraversal(Node<E> current);

    void postOrderTraversal(Node<E> current);


    interface Node<E>{
        E getData();
        Node setData(E data);

        Node<E> addSon(Tree.Node<E> node);
    }

}
