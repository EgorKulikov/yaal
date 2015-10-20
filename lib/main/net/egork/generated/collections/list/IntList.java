package net.egork.generated.collections.list;

import java.util.NoSuchElementException;
import net.egork.generated.collections.*;
import net.egork.generated.collections.iterator.*;
import net.egork.generated.collections.function.*;
import net.egork.generated.collections.comparator.*;

/**
 * @author Egor Kulikov
 */
public interface IntList extends IntReversableCollection {
    public static final IntList EMPTY = new IntArray(new int[0]);

    public abstract int get(int index);
    public abstract void set(int index, int value);
    public abstract void addAt(int index, int value);
    public abstract void removeAt(int index);

    default public int first() {
        return get(0);
    }

    default public int last() {
    	return get(size() - 1);
    }

    default public void swap(int first, int second) {
    	if (first == second) {
    		return;
    	}
		int temp = get(first);
		set(first, get(second));
		set(second, temp);
    }

    default public IntIterator intIterator() {
        return new IntIterator() {
            private int at;
            private boolean removed;

            public int value() {
                if (removed) {
                    throw new IllegalStateException();
                }
                return get(at);
            }

            public boolean advance() {
                at++;
                removed = false;
                return isValid();
            }

            public boolean isValid() {
                return !removed && at < size();
            }

            public void remove() {
                removeAt(at);
                at--;
                removed = true;
            }
        };
    }

    default public IntIterator reverseIterator() {
        return new IntIterator() {
            private int at = size() - 1;
            private boolean removed;

            public int value() {
                if (removed) {
                    throw new IllegalStateException();
                }
                return get(at);
            }

            public boolean advance() {
                at--;
                removed = false;
                return isValid();
            }

            public boolean isValid() {
                return !removed && at >= 0;
            }

            public void remove() {
                removeAt(at);
                removed = true;
            }
        };
    }

	@Override
    default public void add(int value) {
        addAt(size(), value);
    }

    default public void popLast() {
        removeAt(size() - 1);
    }

    default public void popFirst() {
        removeAt(0);
    }

	default public int minIndex() {
		int result = Integer.MAX_VALUE;
	    int size = size();
	    int at = -1;
		for (int i = 0; i < size; i++) {
			int current = get(i);
			if (current < result) {
				result = current;
				at = i;
			}
		}
		return at;
	}

	default public int minIndex(IntComparator comparator) {
		int result = Integer.MIN_VALUE;
	    int size = size();
	    int at = -1;
		for (int i = 0; i < size; i++) {
			int current = get(i);
			if (result == Integer.MIN_VALUE || comparator.compare(result, current) > 0) {
				result = current;
				at = i;
			}
		}
		return at;
	}

	default public int maxIndex() {
		int result = Integer.MIN_VALUE;
	    int size = size();
	    int at = -1;
		for (int i = 0; i < size; i++) {
			int current = get(i);
			if (current > result) {
				result = current;
				at = i;
			}
		}
		return at;
	}

	default public int maxIndex(IntComparator comparator) {
		int result = Integer.MIN_VALUE;
	    int size = size();
	    int at = -1;
		for (int i = 0; i < size; i++) {
			int current = get(i);
			if (result == Integer.MIN_VALUE || comparator.compare(result, current) < 0) {
				result = current;
				at = i;
			}
		}
		return at;
	}

	default public void sort() {
		sort(IntComparator.DEFAULT);
	}

	default public void sort(IntComparator comparator) {
	    Sorter.sort(this, comparator);
	}

	default public int find(int value) {
	    int size = size();
	    for (int i = 0; i < size; i++) {
	        if (get(i) == value) {
	            return i;
	        }
	    }
	    return -1;
	}

	default public int find(IntFilter filter) {
	    int size = size();
	    for (int i = 0; i < size; i++) {
	        if (filter.accept(get(i))) {
	            return i;
	        }
	    }
	    return -1;
	}

	default public int findLast(int value) {
	    for (int i = size() - 1; i >= 0; i--) {
	        if (get(i) == value) {
	            return i;
	        }
	    }
	    return -1;
	}

	default public int findLast(IntFilter filter) {
	    for (int i = size() - 1; i >= 0; i--) {
	        if (filter.accept(get(i))) {
	            return i;
	        }
	    }
	    return -1;
	}

	default public boolean nextPermutation() {
		return nextPermutation(IntComparator.DEFAULT);
	}

	default public boolean nextPermutation(IntComparator comparator) {
		int size = size();
		int last = get(size - 1);
		for (int i = size - 2; i >= 0; i--) {
			int current = get(i);
			if (comparator.compare(last, current) > 0) {
				for (int j = size - 1; j > i; j--) {
					if (comparator.compare(get(j), current) > 0) {
						swap(i, j);
						subList(i + 1, size).inPlaceReverse();
						return true;
					}
				}
			}
			last = current;
		}
		return false;
	}

	default public void inPlaceReverse() {
	    for (int i = 0, j = size() - 1; i < j; i++, j--) {
	        swap(i, j);
	    }
	}

	default public IntList subList(final int from, final int to) {
	    return new IntList() {
    	    private final int shift;
	        private final int size;

	        {
	            if (from < 0 || from > to || to > IntList.this.size()) {
	                throw new IndexOutOfBoundsException("from = " + from + ", to = " + to + ", size = " + size());
	            }
	            shift = from;
	            size = to - from;
	        }

            public int size() {
                return size;
            }

            public int get(int at) {
                if (at < 0 || at >= size) {
                    throw new IndexOutOfBoundsException("at = " + at + ", size = " + size());
                }
                return IntList.this.get(at + shift);
            }

            public void addAt(int index, int value) {
                throw new UnsupportedOperationException();
            }

            public void removeAt(int index) {
                throw new UnsupportedOperationException();
            }

            public void set(int at, int value) {
                if (at < 0 || at >= size) {
                    throw new IndexOutOfBoundsException("at = " + at + ", size = " + size());
                }
                IntList.this.set(at + shift, value);
            }

            public IntList compute() {
                return new IntArrayList(this);
            }
	    };
	}

	default IntList unique() {
	    int last = Integer.MIN_VALUE;
	    IntList result = new IntArrayList();
	    int size = size();
	    for (int i = 0; i < size; i++) {
	        int current = get(i);
	        if (current != last) {
	            result.add(current);
	            last = current;
	        }
	    }
	    return result;
	}
}
