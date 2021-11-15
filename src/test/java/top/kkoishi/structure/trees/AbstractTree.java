package top.kkoishi.structure.trees;

import top.kkoishi.interfence.tree;

abstract public class AbstractTree<T> implements tree {
    private T value;
    private AbstractTree<T> leftChild;
    private AbstractTree<T> rightChild;

    public AbstractTree (T value) {
        this.value = value;
    }

    protected AbstractTree () {
    }

    public void setValue (T value) {
        this.value = value;
    }

    /**
     * Seek the left Child.
     * If tree is not binary tree,set first or deprecate
     * @param leftChild the left child
     */

    public void setLeftChild (AbstractTree<T> leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild (AbstractTree<T> rightChild) {
        this.rightChild = rightChild;
    }

    public T getValue () {
        return value;
    }

    public AbstractTree<T> getLeftChild () {
        return leftChild;
    }

    public AbstractTree<T> getRightChild () {
        return rightChild;
    }

    /**
     * Returns a string representation of the tree. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the trees value,and its children(if exist)
     * in a visual way.In other words, this method returns a string equal to the
     * value of:
     *
     * <blockquote>
     *      <pre>
     *          [Tree type name]:{
     *              value=[value nullable]
     *              leftChild:{
     *                  ...
     *              }
     *              rightChild:{
     *                  ...
     *              }
     *          }
     *      </pre>
     *  </blockquote>
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString () {
        StringBuilder builder = new StringBuilder("BinaryTree:{");
        builder.append("\nvalue=").append(value);
        if (leftChild != null) {
            builder.append("\nleftChild=").append(leftChild);
        }
        if (rightChild != null) {
            builder.append("\nrightChild=").append(rightChild);
        }
        builder.append("\n}");
        return builder.toString();
    }
}
