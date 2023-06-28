package org.test.suiblogexample.specialization;

public interface TreeNode<T> {
    T getContent();

    Iterable<TreeNode<T>> getChildren();
}
