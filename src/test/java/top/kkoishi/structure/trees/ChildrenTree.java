package top.kkoishi.structure.trees;

import top.kkoishi.Exceptions.LinerListOutOfBoundException;
import top.kkoishi.structure.LinerTreeList;

public class ChildrenTree<T> extends AbstractTree<T> {
    private T value;
    private final LinerTreeList<T> children;

    public ChildrenTree (T value) {
        this.value = value;
        children = new LinerTreeList<>();
    }

    public ChildrenTree () {
        this.value = null;
        children = new LinerTreeList<>();
    }

    public void addChild (ChildrenTree<T> tree) {
        children.add(tree);
    }

    @Override
    public void setValue (T value) {
        this.value = value;
    }

    public void setChild (int childIndex, ChildrenTree<T> tree)
            throws LinerListOutOfBoundException {
        children.set(childIndex, tree);
    }

    @Override
    public T getValue () {
        return value;
    }

    public ChildrenTree<T> getChild (int index) {
        return children.getChildrenTree(index);
    }

    public String[] getChildren () {
        int size = children.size();
        String[] ans = new String[size];
        for (int i = 0; i < size; i++) {
            ans[i] = children.getChildrenTree(i).getValue().toString();
        }
        return ans;
    }

    public LinerTreeList<T> getLinerTreeList () {
        return children;
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
     *              children:{
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
        if (children == null) {
            return "ChildrenTree:{\n\tvalue=" + value + "\n}";
        }
        StringBuilder builder = new StringBuilder("ChildrenTree:{");
        builder.append("\n\tvalue:").append(value);
        if (children.size() != 0) {
            int size = children.size();
            builder.append("\n\tChildren:{");
            for (int i = 0; i < size; i++) {
                builder.append("\n\t").append(children.getChildrenTree(i).toString());
            }
            builder.append("\n\t}");
        }
        builder.append("\n}");
        return builder.toString();
    }
}
