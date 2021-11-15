package top.kkoishi.structure.forest;

import top.kkoishi.interfence.forest;
import top.kkoishi.structure.trees.AbstractTree;
import top.kkoishi.structure.trees.BinaryTree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Forest<T extends AbstractTree<T>> implements forest<T> {
    private List<BinaryTree<T>> data;

    public Forest (List<BinaryTree<T>> data) {
        this.data = data;
    }

    public Forest () {
    }

    public void setData (List<BinaryTree<T>> data) {
        this.data = data;
    }

    @Deprecated
    public List<BinaryTree<T>> getForestData () {
        return data;
    }

    @Override
    public List<BinaryTree<T>> getForest () {
        return data;
    }

    @Override
    public BinaryTree<T> createTree () {
        BinaryTree<T> tree = new BinaryTree<>();
        Queue<BinaryTree<T>> queue = new LinkedList<>(data);
        if (data == null) {
            return null;
        }
        tree = queue.peek();


        return null;
    }

    @Override
    public void setForest (AbstractTree<T> tree) {

    }
}
