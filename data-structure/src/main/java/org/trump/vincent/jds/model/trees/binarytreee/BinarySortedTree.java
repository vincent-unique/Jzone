package org.trump.vincent.jds.model.trees.binarytreee;

/**
 * Created by Vincent on 2017/12/14 0014.
 */
public interface BinarySortedTree<E> {
    interface Node<E>{
        E getData();
        Node<E> setData(E data);

        Node<E> getParent();
        Node<E> setParent(BinaryTree.Node<E> parent);

        Node<E> getLeft();
        Node<E> setLeft(BinaryTree.Node<E> left);

        Node<E> getRight();
        Node<E> setRight(BinaryTree.Node<E> right);
    }

    Node<E> getRoot();

    Node<E> search(E value);

    void insert(Node<E> newNode);

    void create(Node<E>[] nodes);

    void delete(Node<E> node);

}
