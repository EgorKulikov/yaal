package net.egork.collections.sequence;

import net.egork.collections.ReadOnlyIterator;
import net.egork.io.IOUtils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public abstract class AbstractSequence<T> implements Sequence<T> {
	public Sequence<T> subSequence(int from) {
		return subSequence(from, size());
	}

	public Sequence<T> subSequence(int from, int to) {
		int size = size();
		if (from < 0)
			from += size;
		if (to < 0)
			to += size;
		checkIndices(from, to, size);
		return new SubSequence(from, to);
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	protected void checkIndices(int from, int to, int size) {
		if (from < 0 || to < 0 || from > size || to > size || from > to)
			throw new IndexOutOfBoundsException();
	}

	public Iterator<T> iterator() {
		return new ReadOnlyIterator<T>() {
			private int index = 0;

			public boolean hasNext() {
				return index != size();
			}

			public T next() {
				if (!hasNext())
					throw new NoSuchElementException();
				return get(index++);
			}
		};
	}

	@Override
	public String toString() {
		StringWriter writer = new StringWriter();
		IOUtils.printCollection(this, new PrintWriter(writer), ",");
		return "[" + writer.toString().substring(0, writer.toString().length() - 1) + "]";
	}

	protected class SubSequence implements Sequence<T> {
		protected final int from;
		protected final int to;

		public SubSequence(int from, int to) {
			this.from = from;
			this.to = to;
		}

		public Sequence<T> subSequence(int from) {
			return subSequence(from, to);
		}

		public Sequence<T> subSequence(int from, int to) {
			int size = size();
			if (from < 0)
				from += size;
			if (to < 0)
				to += size;
			checkIndices(from, to, size);
			return new SubSequence(from + this.from, to + this.from);
		}

		public int size() {
			return to - from;
		}

		public boolean isEmpty() {
			return size() == 0;
		}

		public T get(int index) {
			if (index < 0 || index >= size())
				throw new IndexOutOfBoundsException();
			return AbstractSequence.this.get(from + index);
		}

		public Iterator<T> iterator() {
			return new ReadOnlyIterator<T>() {
				private int index = from;

				public boolean hasNext() {
					return index != to;
				}

				public T next() {
					if (index == to)
						throw new NoSuchElementException();
					return AbstractSequence.this.get(index++);
				}
			};
		}
	}
}
