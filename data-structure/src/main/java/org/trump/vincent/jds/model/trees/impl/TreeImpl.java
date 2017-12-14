package org.trump.vincent.jds.model.trees.impl;

import org.trump.vincent.jds.model.trees.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vincent on 2017/12/14 0014.
 */
public class TreeImpl<E> implements Tree{
    public TreeImpl setRoot(Node<E> root) {
        this.root = root;
        return this;
    }

    public Node<E> getRoot() {
        return root;
    }

    private Node<E> root;


    public class NodeImpl<E> implements Tree.Node<E>{
        public E getData() {
            return data;
        }

        public Node setData(E data) {
            this.data = data;
            return this;
        }

        public Node<E> addSon(Tree.Node<E> node){
            if(this.sons==null){
                this.sons = new ArrayList<>();
            }
            this.sons.add(node);
            return this;
        }
        private E data;
        private List<Tree.Node<E>> sons;
    }
}
