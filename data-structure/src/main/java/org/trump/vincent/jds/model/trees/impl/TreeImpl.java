package org.trump.vincent.jds.model.trees.impl;

import org.trump.vincent.jds.model.trees.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vincent on 2017/12/14 0014.
 */
public abstract class TreeImpl<E> implements Tree<E>{
    public TreeImpl setRoot(Node<E> root) {
        this.root = root;
        return this;
    }

    public Node<E> getRoot() {
        return root;
    }

    private Node<E> root;

    public abstract void visit(Node<E> node);

    /**
     * PreOrder Traversal Implementation for general Tree
     * @param current
     */
    @Override
    public void preOrderTraversal(Node<E> current){
        if(current!=null){
            visit(current);
            if(current.getSons()!=null&&current.getSons().size()>0){
                for (Node<E> node:current.getSons()){
                    preOrderTraversal(node);
                }
            }
        }
    }

    /**
     * PostOrder Traversal Implementation for general Tree
     * @param current
     */
    @Override
    public void postOrderTraversal(Node<E> current){
        if(current!=null){
            if(current.getSons()!=null&&current.getSons().size()>0){
                for (Node<E> node:current.getSons()){
                    preOrderTraversal(node);
                }
            }
            visit(current);
        }
    }

    /**
     * Level Traversal of Tree
     * @param current
     */
    @Override
    public void levelTraversal(Node<E> current){
        //TODO
    }

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

        public List<Node<E>> getSons() {
            return sons;
        }

        private List<Tree.Node<E>> sons;
    }
}
