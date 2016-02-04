package net.egork.collections.map;

/**
 * @author Egor Kulikov (egor@egork.net)
 */
public class Indexer<K> extends EHashMap<K, Integer> {
    private int index = 0;

    @Override
    public Integer get(Object key) {
        if (!containsKey(key)) {
            put((K) key, index++);
        }
        return super.get(key);
    }
}
