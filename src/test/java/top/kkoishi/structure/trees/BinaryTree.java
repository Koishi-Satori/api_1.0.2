package top.kkoishi.structure.trees;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Vector;

public class BinaryTree<T> extends AbstractTree<T> {
    private T value;
    private BinaryTree<T> leftChild;
    private BinaryTree<T> rightChild;

    public BinaryTree () {
    }

    /**
     * Create a binary tree
     *
     * @param value      storage data
     * @param leftChild  left child tree
     * @param rightChild right child tree
     */
    public BinaryTree (T value, BinaryTree<T> leftChild, BinaryTree<T> rightChild) {
        this.value = value;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    /**
     * Create a binary tree
     *
     * @param value the storage data
     */
    public BinaryTree (T value) {
        this.value = value;
        this.leftChild = null;
        this.rightChild = null;
    }

    /**
     * Create a binary tree
     *
     * @param value     the storage data
     * @param leftChild left child tree
     */
    public BinaryTree (T value, BinaryTree<T> leftChild) {
        this.value = value;
        this.leftChild = leftChild;
        this.rightChild = null;
    }

    /**
     * Get the children of the binary tree.
     *
     * @return a vector list contains left and right child
     */
    public List<BinaryTree<T>> getChildren () {
        List<BinaryTree<T>> list = new Vector<>();
        list.add(leftChild);
        list.add(rightChild);
        return list;
    }

    /**
     * Set the left child of the binary tree.
     *
     * @param leftChild a binary tree
     */
    public void setLeftChild (BinaryTree<T> leftChild) {
        this.leftChild = leftChild;
    }

    /**
     * Set the right child of a binary tree.
     *
     * @param rightChild a binary tree.
     */
    public void setRightChild (BinaryTree<T> rightChild) {
        this.rightChild = rightChild;
    }

    /**
     * Set the value of the binary tree's node.
     *
     * @param value a value,which its type must be instanced of the trees'.
     */
    public void setValue (T value) {
        this.value = value;
    }

    /**
     * Get the value of the binary tree's node.
     *
     * @return a value.
     */
    public T getValue () {
        return value;
    }

    /**
     * Get the left child of the binary tree.
     *
     * @return a binary tree
     */
    public BinaryTree<T> getLeftChild () {
        return leftChild;
    }

    /**
     * Get the right tree of the binary tree.
     *
     * @return a binary tree
     */
    public BinaryTree<T> getRightChild () {
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
     * <pre>
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
     * </blockquote>
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

    /**
     * Build a binary tree from a pre-traversal array and a mid-traversal array.
     * The type of the array will be the type class of the result tree.
     * The start and end are replaced by "0" and "[array].length - 1"
     *
     * @param preTraversal a pre-traversal array
     * @param midTraversal a mid-traversal array
     * @return a binary tree which is structured by arrays
     * @throws RuntimeException point out the array is illegal.
     *                          Perhaps reasons:the array is null/length is zero/the lengths of arrays is not equal.
     */
    @ApiStatus.Experimental
    @Nullable
    @ApiStatus.NonExtendable
    public BinaryTree<T> buildFrom_midAndPre (T[] preTraversal, T[] midTraversal) {
        return buildFrom_midAndPre(preTraversal, 0, preTraversal.length - 1, midTraversal, 0, midTraversal.length - 1);
    }

    /**
     * Build a binary tree from a pre-traversal array and a mid-traversal array.
     * The type of the array will be the type class of the result tree.
     * The start and end is notnull.
     *
     * @param preTraversal a pre-traversal array
     * @param preStart     start pos
     * @param preEnd       end pos
     * @param midTraversal a mid-traversal array
     * @param midStart     start pos
     * @param midEnd       end pos
     * @return a binary tree which is structured by arrays
     * @throws RuntimeException point out the array is illegal.
     *                          Perhaps reasons:the array is null/length is zero/the lengths of arrays is not equal.
     */
    @ApiStatus.Experimental
    @Nullable
    @ApiStatus.NonExtendable
    @ApiStatus.AvailableSince("java6")
    public BinaryTree<T> buildFrom_midAndPre (T[] preTraversal, int preStart, int preEnd, T[] midTraversal, int midStart, int midEnd) {
        if (preTraversal == null || midTraversal == null) {
            throw new RuntimeException("illegal array input");
        }
        if (midTraversal.length == 0 || preTraversal.length == 0) {
            throw new RuntimeException("illegal array input");
        }
        if (preTraversal.length != midTraversal.length) {
            throw new RuntimeException("illegal array input");
        }

        if (preStart > preEnd && midStart > midEnd) {
            return null;
        }
        if (preStart == preEnd && midStart == midEnd) {
            if (!midTraversal[midStart].equals(preTraversal[preStart])) {
                throw new RuntimeException("illegal array input");
            }
            return new BinaryTree<>(midTraversal[midEnd]);
        }

        int pre, mid;
        for (mid = midStart; mid < midEnd; mid++) {
            if (midTraversal[mid].equals(preTraversal[preStart])) {
                break;
            }
        }
        BinaryTree<T> tree = new BinaryTree<>(preTraversal[mid]);
        pre = preStart + (mid - midStart);
        tree.setLeftChild(buildFrom_midAndPre(preTraversal, preStart + 1, pre, midTraversal, midStart, mid - 1));
        tree.setRightChild(buildFrom_midAndPre(preTraversal, pre + 1, preEnd, midTraversal, mid + 1, midEnd));
        return tree;
    }

    /**
     * Build a binary tree from a back-traversal array and a mid-traversal array.
     * The type of the array will be the type class of the result tree.
     * The start and end are replaced by "0" and "[array].length - 1"
     * @param backTraversal a back-traversal array
     * @param midTraversal a mid-traversal array
     * @return a binary tree which is structured by arrays
     * @throws RuntimeException point out the array is illegal.
     *                          Perhaps reasons:the array is null/length is zero/the lengths of arrays is not equal.
     */
    public BinaryTree<T> buildFrom_backAndMid (T[] backTraversal, T[] midTraversal) {
        return buildFrom_backAndMid(backTraversal, 0, backTraversal.length - 1, midTraversal, 0, midTraversal.length - 1);
    }

    /**
     * Build a binary tree from a back-traversal array and a mid-traversal array.
     * The type of the array will be the type class of the result tree.
     * The start and end are notnull.
     * @param backTraversal a back-traversal array
     * @param backStart start pos
     * @param backEnd end pos
     * @param midTraversal a mid-traversal array
     * @param midStart start pos
     * @param midEnd end pos
     * @return a binary tree which is structured by arrays
     * @throws RuntimeException point out the array is illegal.
     *                          Perhaps reasons:the array is null/length is zero/the lengths of arrays is not equal.
     */
    @ApiStatus.Experimental
    @Nullable
    @ApiStatus.NonExtendable
    @ApiStatus.AvailableSince("java6")
    public BinaryTree<T> buildFrom_backAndMid (T[] backTraversal, int backStart, int backEnd, T[] midTraversal, int midStart, int midEnd) {
        if (backTraversal == null || midTraversal == null) {
            throw new RuntimeException("illegal array input");
        }
        if (backTraversal.length == 0 || midTraversal.length == 0) {
            throw new RuntimeException("illegal array input");
        }
        if (backTraversal.length != midTraversal.length) {
            throw new RuntimeException("illegal array input");
        }

        if (backStart > backEnd && midStart > midEnd) {
            return null;
        }
        if (backStart == backEnd && midStart == midEnd) {
            return new BinaryTree<>(backTraversal[backEnd]);
        }

        int back, mid;
        for (mid = 0; mid < midEnd; mid++) {
            if (backTraversal[backEnd].equals(midTraversal[mid])) {
                break;
            }
        }
        BinaryTree<T> tree = new BinaryTree<>(midTraversal[mid]);
        back = backStart + (mid - midStart);
        tree.setLeftChild(buildFrom_backAndMid(backTraversal, backStart, back - 1, midTraversal, midStart, mid - 1));
        tree.setRightChild(buildFrom_backAndMid(backTraversal, back, backEnd - 1, midTraversal, mid + 1, midEnd));
        return tree;
    }
}
