package net.egork.collections.set;

import net.egork.collections.map.EHashMap;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * @author egor@egork.net
 */
public class EHashSet<E> extends AbstractSet<E> {
    private static final Object VALUE = new Object();
    private final Map<E, Object> map;

    public EHashSet() {
        this(4);
    }

    public EHashSet(int maxSize) {
        map = new EHashMap<E, Object>(maxSize);
    }

    public EHashSet(Collection<E> collection) {
        this(collection.size());
        addAll(collection);
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public boolean add(E e) {
        if (e == null) {
            return false;
        }
        return map.put(e, VALUE) == null;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            return false;
        }
        return map.remove(o) != null;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public int size() {
        return map.size();
    }
}
