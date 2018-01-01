package org.trump.vincent.jds.model.trees.impl;

import org.trump.vincent.jds.model.trees.binarytreee.BinarySortedTree;
import org.trump.vincent.jds.model.trees.binarytreee.BinaryTree;

/**
 * Created by Vincent on 2017/11/30 0030.
 */
public abstract class BinaryTreeImpl<E extends Comparable<E>> implements BinaryTree<E> {

    private Node<E> root;

    /**
     * Retrieve Implementation of BinaryTree
     * @param key
     * @return
     */
    public Node<E> find(E key){
        return find(getRoot(),key);
    }

    /**
     * BinaryTree Insertion may be implementated as Level Insertion
     * @param current
     * @param node
     */
    public abstract void insert(Node<E> current,Node<E> node);

    /**
     * Compute Height of the certain BinaryTree
     * @param root
     * @return
     */
    public Integer computeHeight(BinarySortedTree.Node<E> root){
        if(root==null){
            return 0;
        }else {
            return 1+Math.max(computeHeight(root.getLeft()),
                    computeHeight(root.getRight()));
        }
    }

    protected Node<E> find(Node<E> current,E key){
        if(current==null)
            return null;
        if(current.getData()==null){
            throw new NullPointerException("Null Node Key.");
        }else if(current.getData().equals(key)||current.getData().compareTo(key)==0){
            return current;
        }else if(current.getData().compareTo(key)>0){
            return find(current.getLeft(),key);
        }else if(current.getData().compareTo(key)<0){
            return find(current.getRight(),key);
        }
        return null;
    }
    public abstract void visit(BinaryTree.Node<E> node);

    /**
     * PreOrder Traversal for BinaryTree
     * @param current
     */
    public void preOrderTraversal(Node<E> current){
        if(current !=null){
            visit(current);
            preOrderTraversal(current.getLeft());
            preOrderTraversal(current.getRight());
        }
    }

    /**
     * InOrder Traversal for BinaryTree
     * @param current
     */
    public void inOrderTraversal(Node<E> current){
        if(current!=null){
            inOrderTraversal(current.getLeft());
            visit(current);
            inOrderTraversal(current.getRight());
        }
    }

    /**
     * PostOrder Traversal for BinaryTree
     * @param current
     */
    public void postOrderTraversal(Node<E> current){
        if(current!=null){
            postOrderTraversal(current.getLeft());
            postOrderTraversal(current.getRight());
            visit(current);
        }
    }

    /**
     * Compute the size of the binaryTree
     * @param current
     * @return
     */
    public int treeSize(Node<E> current){
        if(current==null)
            return 0;
        else {
            return treeSize(current.getLeft())
                    +1
                    +treeSize(current.getRight());
        }
    }

    /**
     * Compute the height of the binaryTree
     * @param current
     * @return
     */
    public int treeHeight(Node<E> current){
        if(current==null)
            return 0;
        else {
            return 1+ Math.max(treeHeight(current.getLeft()),
                    treeHeight(current.getRight()));
        }
    }

    public static class NodeImpl<E> implements BinaryTree.Node<E> {
        private E data;
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;

        public E getData() {
            return data;
        }

        public Node<E> setData(E data) {
            this.data = data;
            return this;
        }

        public Node<E> getParent() {
            return parent;
        }

        public Node<E> setParent(Node<E> parent) {
            this.parent = parent;
            return this;
        }

        public Node<E> getLeft() {
            return left;
        }

        public Node<E> setLeft(Node<E> left) {
            this.left = left;
            return this;
        }

        public Node<E> getRight() {
            return right;
        }

        public Node<E> setRight(Node<E> right) {
            this.right = right;
            return this;
        }
    }

    public Node<E> getRoot() {
        return root;
    }

    public BinaryTreeImpl setRoot(Node<E> root) {
        this.root = root;
        return this;
    }
}
