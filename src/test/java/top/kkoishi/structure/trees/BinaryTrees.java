package top.kkoishi.structure.trees;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import top.kkoishi.Exceptions.ErrorTypeException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;

public class BinaryTrees {
    /*
        Mode of level order traversal
                Nodes
                nextLevelNodes
     */

    public final int IN_LEVEL_ORDER = 1 << 1;



    /*
        Mode of first order traversal
        father left child right child
        children are trees
        repeat util null
     */

    public final int IN_FIRST_ORDER = 1 << 2;



    /*
        Mode of middle order traversal
        left child father right child
        children are trees
        repeat util null
     */

    public final int IN_MID_ORDER = 1 << 3;



    /*
        Mode of last order traversal
        left child right child father
        children are trees
        repeat util null
     */

    public final int IN_LAST_ORDER = 1 << 4;

    public BinaryTrees () {
    }

    @ApiStatus.Experimental
    public int getMaxDepth (BinaryTree tree) {
        if (tree == null) {
            return 0;
        }
        int left = getMaxDepth(tree.getLeftChild());
        int right = getMaxDepth(tree.getRightChild());

        return (1 + Math.max(left, right));
    }

    @ApiStatus.Experimental
    @ApiStatus.AvailableSince("java7")
    public int getMaxWidth (@NotNull BinaryTree tree) {
        List<List<String>> list = levelOrderTraversal(tree);
        int dp = Integer.MIN_VALUE;
        for (List<String> listStrings : list) {
            dp = Math.max(dp, listStrings.size());
        }
        return dp;
    }

    public int getSize (BinaryTree tree) {
        if (tree == null) {
            return 0;
        }
        int size = 0;
        size++;
        if (tree.getLeftChild() != null) {
            size += getSize(tree.getLeftChild());
        }
        if (tree.getRightChild() != null) {
            size += getSize(tree.getRightChild());
        }
        return size;
    }

    /**
     * Provides the method to overview a binary tree.
     * <p>The can't be too long,or a {@code StackOverFlowError} will be thrown.</p>
     * You should switch the traversal mode,or an exception will be thrown.
     *
     * @param mode the traversal mode(IN_LEVEL_ORDER,IN_MID_ORDER,IN_FIRST_ORDER,IN_LAST_ORDER)
     * @param tree src tree
     * @return traversal result
     * @throws ErrorTypeException
     */

    @NotNull
    @ApiStatus.Experimental
    @ApiStatus.AvailableSince("java7")
    @ApiStatus.NonExtendable
    public String traversal (int mode, @NotNull BinaryTree tree) throws ErrorTypeException {
        @NotNull String result;
        switch (mode) {
            /*
                use method to traversal,so promise encapsulation.
             */
            case IN_LEVEL_ORDER -> {
                List<List<String>> ans = levelOrderTraversal(tree);
                result = ans.stream().map(list -> "\t" + list + "\n")
                        .collect(Collectors.joining("", "BinaryTree:{\n", "}"));
            }
            case IN_MID_ORDER -> result = midOrderTraversal(tree);
            case IN_FIRST_ORDER -> result = firstOrderTraversal(tree);
            case IN_LAST_ORDER -> result = postOrderTraversal(tree);
            /*
                throw exception.
             */
            default -> throw new ErrorTypeException();
        }
        return result;
    }

    @ApiStatus.Experimental
    @NotNull
    private List<List<String>> levelOrderTraversal (BinaryTree tree) {
        List<List<String>> ans = new ArrayList<>();
        Queue<BinaryTree> queue = new top.kkoishi.structure.Queue<>(BinaryTree.class);
        queue.add(tree);
        if (tree == null) {
            return ans;
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<String> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                //poll element
                BinaryTree binaryTree = queue.peek();
                queue.poll();
                //Assert binary tree's value is not null
                assert Objects.requireNonNull(binaryTree).getValue() != null;
                list.add(binaryTree.getValue().toString());
                if (binaryTree.getLeftChild() != null) {
                    queue.add(binaryTree.getLeftChild());
                }
                if (binaryTree.getRightChild() != null) {
                    queue.add(binaryTree.getRightChild());
                }
            }
            ans.add(list);
        }

        return ans;
    }

    public boolean isEmpty (BinaryTree tree) {
        if (tree == null) {
            return true;
        }
        if (tree != null) {
            return false;
        }
        boolean ans = false;

        if (tree.getRightChild() != null) {
            ans = isEmpty(tree.getRightChild());
        }
        if (tree.getLeftChild() != null) {
            ans = isEmpty(tree.getLeftChild());
        }

        return ans;
    }

    @ApiStatus.Experimental
    private @NotNull String firstOrderTraversal (@NotNull BinaryTree tree) {
        StringBuilder builder = new StringBuilder();
        Stack<BinaryTree> stack = new Stack<>();
        BinaryTree binaryTree = tree;
        while (binaryTree != null || stack.size() > 0) {
            if (binaryTree != null) {
                builder.append(binaryTree.getValue()).append("\s");
                stack.push(binaryTree);
                binaryTree = binaryTree.getLeftChild();
            } else {
                binaryTree = stack.pop();
                binaryTree = binaryTree.getRightChild();
            }
        }
        return builder.toString();
    }

    @ApiStatus.Experimental
    private @NotNull String midOrderTraversal (@NotNull BinaryTree tree) {
        StringBuilder builder = new StringBuilder();
        Stack<BinaryTree> stack = new Stack<>();
        BinaryTree binaryTree = tree;
        while (binaryTree != null || stack.size() > 0) {
            if (binaryTree != null) {
                stack.push(binaryTree);
                binaryTree = binaryTree.getLeftChild();
            } else {
                binaryTree = stack.pop();
                builder.append(binaryTree.getValue()).append("\s");
                binaryTree = binaryTree.getRightChild();
            }
        }
        return builder.toString();
    }

    @ApiStatus.Experimental
    private @NotNull String postOrderTraversal (@NotNull BinaryTree tree) {
        StringBuilder builder = new StringBuilder();
        Stack<BinaryTree> stack = new Stack<>();
        BinaryTree curTree, lastTree;
        curTree = tree;
        lastTree = null;

        while (curTree != null) {
            stack.push(curTree);
            curTree = curTree.getLeftChild();
        }

        while (!stack.empty()) {
            curTree = stack.pop();
            if (curTree.getRightChild() != null && curTree.getRightChild() != lastTree) {
                stack.push(curTree);
                curTree = curTree.getRightChild();
                //redirect to the end of left child trees in binary tree.
                while (curTree != null) {
                    stack.push(curTree);
                    curTree = curTree.getLeftChild();
                }
            } else {
                builder.append(curTree.getValue()).append("\s");
                lastTree = curTree;
            }
        }

        return builder.toString();
    }

    @Deprecated
    public BinaryTree<String> buildFrom_midAndPre (String[] preTraversal,int preStart
            , int preEnd,String[] midTraversal,int midStart,int midEnd) {
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

        int pre,mid;
        for (mid = midStart;mid < midEnd;mid++) {
            if (midTraversal[mid].equals(preTraversal[preStart])) {
                break;
            }
        }
        BinaryTree<String> tree = new BinaryTree<>(preTraversal[mid]);
        pre = preStart + (mid - midStart);
        tree.setLeftChild(buildFrom_midAndPre(preTraversal,preStart + 1
                ,pre,midTraversal,midStart,mid - 1));
        tree.setRightChild(buildFrom_midAndPre(preTraversal,pre + 1,
                preEnd,midTraversal,mid + 1,midEnd));
        return tree;
    }

    @Deprecated
    public BinaryTree<String> buildFrom_backAndMid (String[] backTraversal, int backStart, int backEnd
            , String[] midTraversal, int midStart, int midEnd) {
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

        int back,mid;
        for (mid = 0;mid <midEnd;mid++) {
            if (backTraversal[backEnd].equals(midTraversal[mid])) {
                break;
            }
        }
        BinaryTree<String> tree = new BinaryTree<>(midTraversal[mid]);
        back = backStart + (mid - midStart);
        tree.setLeftChild(buildFrom_backAndMid(backTraversal,backStart,back - 1
                ,midTraversal,midStart,mid - 1));
        tree.setRightChild(buildFrom_backAndMid(backTraversal,back,backEnd - 1
                ,midTraversal,mid + 1,midEnd));
        return tree;
    }
}
