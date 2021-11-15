package top.kkoishi.structure;

import top.kkoishi.Exceptions.LinerListOutOfBoundException;
import top.kkoishi.structure.trees.AbstractTree;
import top.kkoishi.structure.trees.ChildrenTree;

import java.util.Arrays;

public class LinerTreeList<T> {
    private int size;
    private int pointer;
    private AbstractTree<T>[] trees;

    public LinerTreeList () {
    }

    @SuppressWarnings("unchecked")
    private void expandSize () {
        size++;
        if (trees == null) {
            trees = new AbstractTree[size];
        }
    }

    public void add (AbstractTree<T> tree) {
        expandSize();
        trees = Arrays.copyOf(trees,size);

        trees[pointer] = tree;
        pointer++;
    }

    public void del (int index) {
        size--;
        pointer--;

        AbstractTree<T>[] temp0 = trees;
        trees = Arrays.copyOf(trees,size);

        for (int i = 0; i < size; i++) {
            if (size < index) {
                trees[i] = temp0[i];
            } else {
                trees[i] = temp0[i + 1];
            }
        }
    }

    @Deprecated
    public AbstractTree<T> get (int index) {
        return trees[index];
    }

    public ChildrenTree<T> getChildrenTree (int index) {
        return (ChildrenTree<T>) trees[index];
    }

    public void set (int index,AbstractTree<T> tree) throws LinerListOutOfBoundException {
        if (index < size) {
            this.trees[index] = tree;
            return;
        }
        throw new LinerListOutOfBoundException();
    }

    public int size () {
        return size;
    }
}
