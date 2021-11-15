package top.kkoishi.interfence;

import top.kkoishi.structure.trees.AbstractTree;
import top.kkoishi.structure.trees.BinaryTree;

import java.util.List;

public interface forest<T extends AbstractTree<T>> {
    List getForest ();
    AbstractTree<T> createTree ();
    void setForest (AbstractTree<T> tree);
}
