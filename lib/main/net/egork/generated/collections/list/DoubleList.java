package net.egork.generated.collections.list;

import java.util.NoSuchElementException;
import net.egork.generated.collections.*;
import net.egork.generated.collections.iterator.*;
import net.egork.generated.collections.function.*;
import net.egork.generated.collections.comparator.*;

/**
 * @author Egor Kulikov
 */
public interface DoubleList extends DoubleReversableCollection {
    public static final DoubleList EMPTY = new DoubleArray(new double[0]);

    public abstract double get(int index);
    public abstract void set(int index, double value);
    public abstract void addAt(int index, double value);
    public abstract void removeAt(int index);

    default public double first() {
        return get(0);
    }

    default public double last() {
    	return get(size() - 1);
    }

    default public void swap(int first, int second) {
    	if (first == second) {
    		return;
    	}
		double temp = get(first);
		set(first, get(second));
		set(second, temp);
    }

    default public DoubleIterator doubleIterator() {
        return new DoubleIterator() {
            private int at;
            private boolean removed;

            public double value() {
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

    default public DoubleIterator reverseIterator() {
        return new DoubleIterator() {
            private int at = size() - 1;
            private boolean removed;

            public double value() {
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
    default public void add(double value) {
        addAt(size(), value);
    }

    default public void popLast() {
        removeAt(size() - 1);
    }

    default public void popFirst() {
        removeAt(0);
    }

	default public int minIndex() {
		double result = Double.POSITIVE_INFINITY;
	    int size = size();
	    int at = -1;
		for (int i = 0; i < size; i++) {
			double current = get(i);
			if (current < result) {
				result = current;
				at = i;
			}
		}
		return at;
	}

	default public int minIndex(DoubleComparator comparator) {
		double result = Double.MIN_NORMAL;
	    int size = size();
	    int at = -1;
		for (int i = 0; i < size; i++) {
			double current = get(i);
			if (result == Double.MIN_NORMAL || comparator.compare(result, current) > 0) {
				result = current;
				at = i;
			}
		}
		return at;
	}

	default public int maxIndex() {
		double result = Double.NEGATIVE_INFINITY;
	    int size = size();
	    int at = -1;
		for (int i = 0; i < size; i++) {
			double current = get(i);
			if (current > result) {
				result = current;
				at = i;
			}
		}
		return at;
	}

	default public int maxIndex(DoubleComparator comparator) {
		double result = Double.MIN_NORMAL;
	    int size = size();
	    int at = -1;
		for (int i = 0; i < size; i++) {
			double current = get(i);
			if (result == Double.MIN_NORMAL || comparator.compare(result, current) < 0) {
				result = current;
				at = i;
			}
		}
		return at;
	}

	default public void sort() {
		sort(DoubleComparator.DEFAULT);
	}

	default public void sort(DoubleComparator comparator) {
	    Sorter.sort(this, comparator);
	}

	default public int find(double value) {
	    int size = size();
	    for (int i = 0; i < size; i++) {
	        if (get(i) == value) {
	            return i;
	        }
	    }
	    return -1;
	}

	default public int find(DoubleFilter filter) {
	    int size = size();
	    for (int i = 0; i < size; i++) {
	        if (filter.accept(get(i))) {
	            return i;
	        }
	    }
	    return -1;
	}

	default public int findLast(double value) {
	    for (int i = size() - 1; i >= 0; i--) {
	        if (get(i) == value) {
	            return i;
	        }
	    }
	    return -1;
	}

	default public int findLast(DoubleFilter filter) {
	    for (int i = size() - 1; i >= 0; i--) {
	        if (filter.accept(get(i))) {
	            return i;
	        }
	    }
	    return -1;
	}

	default public boolean nextPermutation() {
		return nextPermutation(DoubleComparator.DEFAULT);
	}

	default public boolean nextPermutation(DoubleComparator comparator) {
		int size = size();
		double last = get(size - 1);
		for (int i = size - 2; i >= 0; i--) {
			double current = get(i);
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

	default public DoubleList subList(final int from, final int to) {
	    return new DoubleList() {
    	    private final int shift;
	        private final int size;

	        {
	            if (from < 0 || from > to || to > DoubleList.this.size()) {
	                throw new IndexOutOfBoundsException("from = " + from + ", to = " + to + ", size = " + size());
	            }
	            shift = from;
	            size = to - from;
	        }

            public int size() {
                return size;
            }

            public double get(int at) {
                if (at < 0 || at >= size) {
                    throw new IndexOutOfBoundsException("at = " + at + ", size = " + size());
                }
                return DoubleList.this.get(at + shift);
            }

            public void addAt(int index, double value) {
                throw new UnsupportedOperationException();
            }

            public void removeAt(int index) {
                throw new UnsupportedOperationException();
            }

            public void set(int at, double value) {
                if (at < 0 || at >= size) {
                    throw new IndexOutOfBoundsException("at = " + at + ", size = " + size());
                }
                DoubleList.this.set(at + shift, value);
            }

            public DoubleList compute() {
                return new DoubleArrayList(this);
            }
	    };
	}

	default DoubleList unique() {
	    double last = Double.MIN_NORMAL;
	    DoubleList result = new DoubleArrayList();
	    int size = size();
	    for (int i = 0; i < size; i++) {
	        double current = get(i);
	        if (current != last) {
	            result.add(current);
	            last = current;
	        }
	    }
	    return result;
	}
}
