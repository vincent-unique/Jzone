package org.trump.vincent.jds.model.trees.impl.balance;

import org.trump.vincent.jds.model.trees.binarytreee.balance.AVLTree;

public abstract class AVLTreeImpl<E extends Comparable<E>> implements AVLTree<E> {

    public boolean isAVLTree(AVLNode<E> current){
        if(current==null)
            return true;
        else {
            boolean flag = false;
            flag = Math.abs(getHeight(current.getLeft())-getHeight(current.getRight()))<=1;
            if(flag){
                flag = flag
                        && isAVLTree(current.getLeft())
                        && isAVLTree(current.getRight());
            }
            return flag;
        }
    }

    public int getHeight(AVLNode<E> current){
        if(current==null)
            return 0;
        else {
            return 1+ Math.max(getHeight(current.getLeft()),
                    getHeight(current.getRight()));
        }
    }

    class AVLNodeImpl<E> implements AVLNode<E>{
        private E data;
        private AVLNode<E> parent;
        private AVLNode<E> left;
        private AVLNode<E> right;
        private int height;

        public E getData() {
            return data;
        }

        public AVLNode setData(E data) {
            this.data = data;
            return this;
        }

        public AVLNode<E> getParent() {
            return parent;
        }

        public AVLNode setParent(AVLNode<E> parent) {
            this.parent = parent;
            return this;
        }

        public AVLNode<E> getLeft() {
            return left;
        }

        public AVLNode setLeft(AVLNode<E> left) {
            this.left = left;
            return this;
        }

        public AVLNode<E> getRight() {
            return right;
        }

        public AVLNode setRight(AVLNode<E> right) {
            this.right = right;
            return this;
        }

        public int getHeight() {
            return height;
        }

        public AVLNode setHeight(int height) {
            this.height = height;
            return this;
        }
    }
}
