package top.kkoishi.structure.trees;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Vector;

public class BrotherTree<T> extends AbstractTree<T> {
    private T value;
    private BrotherTree<T> firstChild;
    private BrotherTree<T> nextBrother;

    public BrotherTree (T value) {
        this.value = value;
    }

    public BrotherTree () {
    }

    public void setFirstChild (BrotherTree<T> tree) {
        this.firstChild = tree;
    }

    public BrotherTree<T> getFirstChild () {
        return this.firstChild;
    }

    public void setNextBrother (BrotherTree<T> tree) {
        this.nextBrother = tree;
    }

    public BrotherTree<T> getNextBrother () {
        return this.nextBrother;
    }

    public List<T> getChildren () {
        if (firstChild != null) {
            List<T> ans = new Vector<>();
            BrotherTree<T> temp = this.firstChild;
            while (temp != null) {
                ans.add(temp.getValue());
                temp = temp.nextBrother;
            }
            return ans;
        }
        throw new NullPointerException();
    }

    @Override
    public T getValue () {
        return value;
    }

    @Override
    public void setValue (T value) {
        this.value = value;
    }

    @Override
    @Deprecated
    public void setLeftChild (AbstractTree<T> leftChild) {
        super.setLeftChild(leftChild);
    }

    @Override
    @Deprecated
    public void setRightChild (AbstractTree<T> rightChild) {
        super.setRightChild(rightChild);
    }

    @Override
    @Deprecated
    public AbstractTree<T> getLeftChild () {
        return super.getLeftChild();
    }

    @Override
    @Deprecated
    public AbstractTree<T> getRightChild () {
        return super.getRightChild();
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
     *              FirstChild={
     *                  ...
     *              }
     *              NextBrother={
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
        if (firstChild == null && nextBrother == null) {
            return ("BrotherTree:{\n\tvalue=" + value + "\n}");
        }
        if (firstChild == null) {
            return ("BrotherTree:{\n\tvalue=" + value + "\n\tnextBrother=" + nextBrother + "\n}");
        }
        if (nextBrother == null) {
            return ("BrotherTree:{\n\tvalue=" + value + "\n\tfirstChild=" + firstChild + "\n}");
        }
        StringBuilder builder = new StringBuilder("BrotherTree:{");
        builder.append("\n\tvalue=").append(value);
        builder.append("\n\tfirstChild={").append("\n\t");
        builder.append(this.firstChild.toString());
        builder.append("\n\t}");
        builder.append("\n\tNextBrother={").append("\n\t").append(nextBrother.toString());
        builder.append("\n\t}");
        builder.append("\n}");
        return builder.toString();
    }

    /**
     * Creates and returns a copy of this object.  The precise meaning
     * of "copy" may depend on the class of the object. The general
     * intent is that, for any object {@code x}, the expression:
     * <blockquote>
     * <pre>
     * x.clone() != x</pre></blockquote>
     * will be true, and that the expression:
     * <blockquote>
     * <pre>
     * x.clone().getClass() == x.getClass()</pre></blockquote>
     * will be {@code true}, but these are not absolute requirements.
     * While it is typically the case that:
     * <blockquote>
     * <pre>
     * x.clone().equals(x)</pre></blockquote>
     * will be {@code true}, this is not an absolute requirement.
     * <p>
     * By convention, the returned object should be obtained by calling
     * {@code super.clone}.  If a class and all of its superclasses (except
     * {@code Object}) obey this convention, it will be the case that
     * {@code x.clone().getClass() == x.getClass()}.
     * <p>
     * By convention, the object returned by this method should be independent
     * of this object (which is being cloned).  To achieve this independence,
     * it may be necessary to modify one or more fields of the object returned
     * by {@code super.clone} before returning it.  Typically, this means
     * copying any mutable objects that comprise the internal "deep structure"
     * of the object being cloned and replacing the references to these
     * objects with references to the copies.  If a class contains only
     * primitive fields or references to immutable objects, then it is usually
     * the case that no fields in the object returned by {@code super.clone}
     * need to be modified.
     * <p>
     * The method {@code clone} for class {@code Object} performs a
     * specific cloning operation. First, if the class of this object does
     * not implement the interface {@code Cloneable}, then a
     * {@code CloneNotSupportedException} is thrown. Note that all arrays
     * are considered to implement the interface {@code Cloneable} and that
     * the return type of the {@code clone} method of an array type {@code T[]}
     * is {@code T[]} where T is any reference or primitive type.
     * Otherwise, this method creates a new instance of the class of this
     * object and initializes all its fields with exactly the contents of
     * the corresponding fields of this object, as if by assignment; the
     * contents of the fields are not themselves cloned. Thus, this method
     * performs a "shallow copy" of this object, not a "deep copy" operation.
     * <p>
     * The class {@code Object} does not itself implement the interface
     * {@code Cloneable}, so calling the {@code clone} method on an object
     * whose class is {@code Object} will result in throwing an
     * exception at run time.
     *
     * @return a clone of this instance.
     * @throws CloneNotSupportedException if the object's class does not
     *                                    support the {@code Cloneable} interface. Subclasses
     *                                    that override the {@code clone} method can also
     *                                    throw this exception to indicate that an instance cannot
     *                                    be cloned.
     * @see Cloneable
     */
    @Override
    @SuppressWarnings("unchecked")
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    @NotNull
    public BrotherTree<T> clone () throws CloneNotSupportedException {
        BrotherTree<T> clone = (BrotherTree<T>) super.clone();
        BrotherTree<T> agent = new BrotherTree<>(this.value);
        agent.firstChild = this.firstChild;
        agent.nextBrother = this.nextBrother;
        return agent;
    }
}
