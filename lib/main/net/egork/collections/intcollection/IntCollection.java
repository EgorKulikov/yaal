package net.egork.collections.intcollection;

import java.util.NoSuchElementException;

/**
 * @author egorku@yandex-team.ru
 */
public abstract class IntCollection {
	public abstract IntIterator iterator();
	public abstract int size();
	public abstract void add(int value);
	public abstract void remove(int value);

	public void forEach(IntTask task) {
		for (IntIterator iterator = iterator(); iterator.isValid(); iterator.advance())
			task.process(iterator.value());
	}

	public boolean contains(int value) {
		for (IntIterator iterator = iterator(); iterator.isValid(); iterator.advance()) {
			if (iterator.value() == value)
				return true;
		}
		return false;
	}

	public int count(int value) {
		int result = 0;
		for (IntIterator iterator = iterator(); iterator.isValid(); iterator.advance()) {
			if (iterator.value() == value)
				result++;
		}
		return result;
	}

	public int min() {
		if (size() == 0)
			throw new NoSuchElementException();
		int result = Integer.MAX_VALUE;
		for (IntIterator iterator = iterator(); iterator.isValid(); iterator.advance())
			result = Math.min(result, iterator.value());
		return result;
	}

	public int max() {
		if (size() == 0)
			throw new NoSuchElementException();
		int result = Integer.MIN_VALUE;
		for (IntIterator iterator = iterator(); iterator.isValid(); iterator.advance())
			result = Math.max(result, iterator.value());
		return result;
	}

	public IntCollection union(final IntCollection other) {
		return new IntCollection() {
			@Override
			public IntIterator iterator() {
				return new IntIterator() {
					private IntIterator first = IntCollection.this.iterator();
					private IntIterator second;

					public int value() throws NoSuchElementException {
						if (first.isValid())
							return first.value();
						return second.value();
					}

					public void advance() throws NoSuchElementException {
						if (first.isValid()) {
							first.advance();
							if (!first.isValid())
								second = other.iterator();
						} else
							second.advance();
					}

					public void removeAndAdvance() throws NoSuchElementException, UnsupportedOperationException {
						if (first.isValid()) {
							first.removeAndAdvance();
							if (!first.isValid())
								second = other.iterator();
						} else
							second.advance();
					}

					public boolean isValid() {
						return first.isValid() || second.isValid();
					}
				};
			}

			@Override
			public int size() {
				return IntCollection.this.size() + other.size();
			}

			@Override
			public void add(int value) {
				throw new UnsupportedOperationException();
			}

			@Override
			public void remove(int value) {
				throw new UnsupportedOperationException();
			}
		};
	}

	public IntCollection map(final IntFunction function) {
		return new IntCollection() {
			@Override
			public IntIterator iterator() {
				return new IntIterator() {
					private IntIterator iterator = IntCollection.this.iterator();

					public int value() throws NoSuchElementException {
						return function.value(iterator.value());
					}

					public void advance() throws NoSuchElementException {
						iterator().advance();
					}

					public void removeAndAdvance() throws NoSuchElementException, UnsupportedOperationException {
						iterator().removeAndAdvance();
					}

					public boolean isValid() {
						return iterator().isValid();
					}
				};
			}

			@Override
			public int size() {
				return IntCollection.this.size();
			}

			@Override
			public void add(int value) {
				throw new UnsupportedOperationException();
			}

			@Override
			public void remove(int value) {
				throw new UnsupportedOperationException();
			}
		};
	}
}
