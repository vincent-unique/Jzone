package org.trump.vincent.jds.model.trees.impl;

import org.trump.vincent.jds.model.trees.BinaryTree;

/**
 * Created by Vincent on 2017/11/30 0030.
 */
public class BinaryTreeImpl<E> implements BinaryTree<E> {

    private Entry<E> root;

    public E treeIterate(Entry<E> root ,E element){

        return element;
    }

    public E treeInoderIterate(Entry<E> node,E element){
        if(node!=null) {
            treeIterate(node.left, element);
            if (node.element.equals(element)) {
                return element;
            }
            treeIterate(node.right, element);
        }
        return null;
    }

    public E treePreorderIterate(Entry<E> node,E element){
        if(node!=null){
            if(node.element.equals(element)){
                return element;
            }
            treePreorderIterate(node.left,element);
            treePreorderIterate(node.right,element);
        }
        return null;
    }

    public E treePostorderIterate(Entry<E> node,E element){
        if(node==null){
            treePostorderIterate(node.left,element);
            treePostorderIterate(node.right,element);
            if(node.element.equals(element)){
                return element;
            }
        }
        return null;
    }
    public static class Entry<E> {
        private E element;
        private Entry<E> parent;
        private Entry<E> left;
        private Entry<E> right;
    }
}
