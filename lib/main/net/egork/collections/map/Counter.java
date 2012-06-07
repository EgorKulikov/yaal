package net.egork.collections.map;

import net.egork.misc.Factory;

/**
 * @author Egor Kulikov (egor@egork.net)
 */
public class Counter<K> extends CPPMap<K, Long> {
	public Counter() {
		super(new Factory<Long>() {
			public Long create() {
				return 0L;
			}
		});
	}

	public void add(K key) {
		put(key, get(key) + 1);
	}

	public void add(K key, long delta) {
		put(key, get(key) + delta);
	}
}
