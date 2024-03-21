package assign08;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type> {

    private int height;
    private BinaryNode root;

    public BinarySearchTree() {
        this.root = null;
        this.height = 0;
    }

    @Override
    public boolean add(Type item) {
        if(this.root == null) {
            this.root = new BinaryNode(item, null, null);
            return true;
        }
        BinaryNode temp = this.root;
        while(temp.hasChildren()) {
            if(temp.elem == item || temp.leftChild.elem == item || temp.rightChild.elem == item)
                return false;
            if(item.compareTo(temp.elem) < 0)
                temp = temp.leftChild;
            else
                temp = temp.rightChild;
        }

        if(item.compareTo(temp.elem) < 0)
            temp.leftChild = new BinaryNode(item, null, null);
        else
            temp.rightChild = new BinaryNode(item, null, null);

        height++;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends Type> items) {
       boolean modified = false;
        while (!items.isEmpty()) {
            if(add(items.iterator().next()))
                modified = true;
        }
        return modified;
    }

    @Override
    public void clear() {
        this.root = null;
    }

    @Override
    public boolean contains(Type item) {
        BinaryNode temp = this.root;
        while(temp.hasChildren()) {
            if(temp.elem == item || temp.leftChild.elem == item || temp.rightChild.elem == item)
                return true;
            if(item.compareTo(temp.elem) < 0)
                temp = temp.leftChild;
            else
                temp = temp.rightChild;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<? extends Type> items) {
        boolean modified = true;
        while (!items.isEmpty()) {
            if(!contains(items.iterator().next()))
                modified = false;
        }
        return modified;
    }

    @Override
    public Type first() throws NoSuchElementException {
        BinaryNode temp = root;
        if(temp == null)
            throw new NoSuchElementException("The collection has no elements");
        while(temp.leftChild != null)
            temp = temp.leftChild;

        return temp.elem;
    }

    @Override
    public boolean isEmpty() {
        return this.root.elem == null;
    }

    @Override
    public Type last() throws NoSuchElementException {
        BinaryNode temp = root;
        if(temp == null)
            throw new NoSuchElementException("The collection has no elements");
        while(temp.rightChild != null)
            temp = temp.rightChild;

        return temp.elem;
    }

    @Override
    public boolean remove(Type item) {

        return false;
    }

    @Override
    public boolean removeAll(Collection<? extends Type> items) {
        return false;
    }

    @Override
    public int size() {
        return this.height;
    }

    @Override
    public ArrayList<Type> toArrayList() {
        return null;
    }


    private class BinaryNode {
        public Type elem;
        public BinaryNode leftChild;
        public BinaryNode rightChild;

        public BinaryNode(Type element, BinaryNode leftChild, BinaryNode rightChild) {
            this.elem = element;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

//        public Type getData() {
//            return this.elem;
//        }

//        public BinaryNode getLeftChild() {
//            return this.leftChild;
//        }
//
//        public BinaryNode getRightChild() {
//            return this.rightChild;
//        }
//
//        public void setLeftChild(BinaryNode newNode) {
//            this.leftChild = newNode;
//        }
//
//        public void setRightChild(BinaryNode newNode) {
//            this.rightChild = newNode;
//        }

        public boolean hasChildren() {
            return this.leftChild != null || this.rightChild != null;
        }
    }
}
