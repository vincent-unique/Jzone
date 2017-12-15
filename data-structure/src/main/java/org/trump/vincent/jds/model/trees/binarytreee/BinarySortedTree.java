package org.trump.vincent.jds.model.trees.binarytreee;

/**
 * Created by Vincent on 2017/12/14 0014.
 */
public interface BinarySortedTree<E> {
    interface Node<E>{

    }

    Node<E> getRoot();

    Node<E> search(E value);

    void insert(Node<E> newNode);

    void create(Node<E>[] nodes);

    void delete(Node<E> node);

}
