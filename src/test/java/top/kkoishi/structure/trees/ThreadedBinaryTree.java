package top.kkoishi.structure.trees;

public class ThreadedBinaryTree<T> extends AbstractTree<T> {
    private T value;
    private int typeLeft;
    private int typeRight;
    private ThreadedBinaryTree<T> leftChild;
    private ThreadedBinaryTree<T> rightChild;

    public ThreadedBinaryTree () {
    }

    public ThreadedBinaryTree (T value) {
        this.value = value;
        this.leftChild = null;
        this.rightChild = null;
    }

    @Override
    public void setValue (T value) {
        this.value = value;
    }

    public void setLeftChild (ThreadedBinaryTree<T> leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild (ThreadedBinaryTree<T> rightChild) {
        this.rightChild = rightChild;
    }

    @Override
    public T getValue () {
        return value;
    }

    @Override
    public ThreadedBinaryTree<T> getLeftChild () {
        return leftChild;
    }

    @Override
    public ThreadedBinaryTree<T> getRightChild () {
        return rightChild;
    }

    @Override
    public String toString () {
        return super.toString();
    }
}
