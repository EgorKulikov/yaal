package net.egork.collections.sequence;

import java.util.List;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class ListWrapper<T> extends AbstractWritableSequence<T> {
	private final List<T> list;

	public static<T> WritableSequence<T> wrap(List<T> list) {
		return new ListWrapper<T>(list);
	}

	private ListWrapper(List<T> list) {
		this.list = list;
	}

	public void set(int index, T value) {
		list.set(index, value);
	}

	public int size() {
		return list.size();
	}

	public T get(int index) {
		return list.get(index);
	}
}
