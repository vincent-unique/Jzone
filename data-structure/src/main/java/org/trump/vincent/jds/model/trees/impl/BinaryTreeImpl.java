package org.trump.vincent.jds.model.trees.impl;

import org.trump.vincent.jds.model.trees.binarytreee.BinaryTree;

/**
 * Created by Vincent on 2017/11/30 0030.
 */
public abstract class BinaryTreeImpl<E> implements BinaryTree<E> {

    private Node<E> root;

    public abstract void visit(BinaryTree.Node<E> node);

    public void preOrderTraversal(Node<E> current){
        if(current !=null){
            visit(current);
            preOrderTraversal(current.getLeft());
            preOrderTraversal(current.getRight());
        }
    }

    public void inOrderTraversal(Node<E> current){
        if(current!=null){
            inOrderTraversal(current.getLeft());
            visit(current);
            inOrderTraversal(current.getRight());
        }
    }

    public void postOrderTraversal(Node<E> current){
        if(current!=null){
            postOrderTraversal(current.getLeft());
            postOrderTraversal(current.getRight());
            visit(current);
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
