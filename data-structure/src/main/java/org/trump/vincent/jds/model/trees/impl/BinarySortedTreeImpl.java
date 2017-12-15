package org.trump.vincent.jds.model.trees.impl;

import org.trump.vincent.jds.model.trees.binarytreee.BinarySortedTree;

/**
 * Created by Vincent on 2017/12/15 0015.
 */
public abstract class BinarySortedTreeImpl<E> implements BinarySortedTree<E> {
    private Node<E> root;

    public Node<E> getRoot(){
        return root;
    }

    public Node<E> search(E value){
        return null;
    }

    public void insert(Node<E> newNode){

    }

    public void create(Node<E>[] nodes){

    }

    public void delete(Node<E> node){

    }
}
