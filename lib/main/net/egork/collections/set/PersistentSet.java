package net.egork.collections.set;

import java.util.NavigableSet;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public interface PersistentSet<K> extends NavigableSet<K> {
    public void markState(Object marker);

    public PersistentSet<K> getState(Object marker);
}
