package org.trump.vincent.jds.model.trees.binarytreee;

/**
 * Created by Vincent on 2017/12/14 0014.
 */
public interface BinarySortedTree<T> {
    interface Node<T>{

    }

    Node<T> getRoot();

    Node<T> search(T value);

    void insert(Node<T> newNode);

    void create(Node<T>[] nodes);

    void delete(Node<T> node);

}
