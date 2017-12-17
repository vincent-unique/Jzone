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
       return find(getRoot(),value);
    }

    public Node<E> find(Node<E> current,E value){
        if(current==null){
            return null;
        }else {
            int compareResult = current.getData().compareTo(value);
            if(compareResult==0){
                return current;
            }else if(compareResult>0){
                return find(current.getRight(),value);
            }else if(compareResult<0){
                return find(current.getLeft(),value);
            }
            return null;
        }
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
    protected abstract void insert(Node<E> current,Node<E> newNode);

    /**
     * Create a BinarySortedTree from Array
     * @param nodes
     */
    public void create(Node<E>[] nodes){
        for(int i=0;i<nodes.length;i++){
            Node<E> node = nodes[i];
            if(node!=null){
                insert(nodes[i]);
            }
        }
    }

    /**
     * Compute
     * @param current
     * @return
     */
    public Integer computeHeight(Node<E> current){
        if(root==null){
            return 0;
        }else {
            return 1 + Math.max(computeHeight(current.getLeft()),
                    computeHeight(current.getRight()));
        }
    }

    /**
     * Determine whether the BinaryTree is BalancedTree
     * @param node
     * @return
     */
    @Override
    public boolean isBalanceTree(Node<E> node){
        if(node==null){
            return true;
        }else {
            int leftHeight = computeHeight(node.getLeft());
            int rightHeight = computeHeight(node.getRight());
            boolean flag = false;
            if(leftHeight==rightHeight){
                flag = true;
            }
            return flag
                    &&isBalanceTree(node.getLeft())
                    &&isBalanceTree(node.getRight());
        }
    }

    public void delete(Node<E> node){
        if(node==null){
            return;
        }else if(node.getLeft()==null&&node.getRight()==null){
            Node<E> parent = node.getParent();
            if(parent==null
                /*||parent.getData().compareTo(node.getData())==0*/){
                parent = null;
            }else if(parent.getData().compareTo(node.getData())>0){
                // Left leaf
               parent.setLeft(null);
            }else if(parent.getData().compareTo(node.getData())<0){
                //Right leaf
                parent.setRight(null);
            }
        }else if(node.getLeft()==null){
            Node<E> parent = node.getParent();
            if(parent==null){
                this.root = node.getRight();
            }
            else if(parent.getData().compareTo(node.getData())>0){
                parent.setLeft(node.getRight());
                node.getRight().setParent(parent);
            }else if(parent.getData().compareTo(node.getData())<0){
                parent.setRight(node.getRight());
                node.getRight().setParent(parent);
            }

        }else if(node.getRight()==null){
            Node<E> parent = node.getParent();
            if(parent==null){
                this.root = node.getLeft();
            }
            else if(parent.getData().compareTo(node.getData())>0){
                parent.setLeft(node.getLeft());
                node.getRight().setParent(parent);
            }else if(parent.getData().compareTo(node.getData())<0){
                parent.setRight(node.getLeft());
                node.getRight().setParent(parent);
            }
        }else {
            Node<E> parent = node.getParent();
            if(parent==null || this.root.getData().compareTo(node.getData())==0){
                Node rightMax = findMax(node.getRight());
                this.root = rightMax;
                this.root.setLeft(node.getLeft());
                this.root.setRight(node.getRight());
            }else if(parent.getData().compareTo(node.getData())>0){
                //Left node which has two sub nodes
                Node rightMax = findMax(node);

                delete(rightMax);

                rightMax.setLeft(node.getLeft());
                rightMax.setRight(node.getRight());
                rightMax.setParent(parent);
                parent.setLeft(rightMax);
            }else if(parent.getData().compareTo(node.getData())<0){
                // Right node which has two sub nodes
                Node rightMax = findMax(node);

                delete(rightMax);

                rightMax.setLeft(node.getLeft());
                rightMax.setRight(node.getRight());
                rightMax.setParent(parent);

                parent.setRight(rightMax);
            }
        }
    }


    public Node<E> findMin(Node<E> current){
        if(current==null||current.getLeft()==null){
            return current;
        }else {
            return findMin(current.getLeft());
        }
    }

    public Node<E> findMax(Node<E> current){
        if(current==null||current.getRight()==null){
            return current;
        }else {
            return findMax(current.getRight());
        }
    }
}
