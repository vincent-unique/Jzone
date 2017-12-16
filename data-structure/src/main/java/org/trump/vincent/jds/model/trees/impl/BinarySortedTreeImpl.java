package org.trump.vincent.jds.model.trees.impl;

import org.trump.vincent.jds.model.trees.binarytreee.BinarySortedTree;

/**
 * Created by Vincent on 2017/12/15 0015.
 */
public abstract class BinarySortedTreeImpl<E extends Comparable<E>> implements BinarySortedTree<E> {
    private Node<E> root;

    public Node<E> getRoot(){
        return root;
    }

    public Node<E> search(E value){
        return null;
    }

    /**
     * Insert For Sorted Binary Tree
     * @param newNode
     */
    public void insert(Node<E> newNode){
        insert(root,newNode);
    }

    /**
     * Real Insertion Algorithm Implemetation
     * @param current
     * @param newNode
     */
    protected void insert(Node<E> current,Node<E> newNode){
        if(current==null){
            current = newNode;
        }else if(current.getData().compareTo(newNode.getData())<0){
            insert(current.getLeft(),newNode);
        }else {
            insert(current.getRight(),newNode);
        }
    }

    public void create(Node<E>[] nodes){
        for(int i=0;i<nodes.length;i++){
            Node<E> node = nodes[i];
            if(node!=null){
                insert(nodes[i]);
            }
        }
    }

    public void delete(Node<E> node){

    }
}
