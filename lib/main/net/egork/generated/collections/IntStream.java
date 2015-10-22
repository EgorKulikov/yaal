package net.egork.generated.collections;

import java.util.NoSuchElementException;
import java.util.Iterator;

import net.egork.generated.collections.iterator.*;
import net.egork.generated.collections.function.*;
import net.egork.generated.collections.list.*;
import net.egork.generated.collections.comparator.*;

public interface IntStream extends Iterable<Integer>, Comparable<IntStream> {
	//abstract
	public IntIterator intIterator();

	//base
	default public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {
			private IntIterator it = intIterator();

			public boolean hasNext() {
				return it.advance();
			}

			public Integer next() {
				return it.value();
			}

			public void remove() {
				it.remove();
			}
		};
	}

	default public int first() {
		return intIterator().value();
	}

	default public IntCollection compute() {
		return new IntArrayList(this);
	}

	default public int compareTo(IntStream c) {
		IntIterator it = intIterator();
		IntIterator jt = c.intIterator();
		while (it.isValid() && jt.isValid()) {
			int i = it.value();
			int j = jt.value();
			if (i < j) {
				return -1;
			} else if (i > j) {
				return 1;
			}
			it.advance();
			jt.advance();
		}
		if (it.isValid()) {
			return 1;
		}
		if (jt.isValid()) {
			return -1;
		}
		return 0;
	}

	//algorithms
	default public void forEach(IntTask task) {
		for (IntIterator it = intIterator(); it.isValid(); it.advance()) {
			task.process(it.value());
        }
	}

	default public boolean contains(int value) {
		for (IntIterator it = intIterator(); it.isValid(); it.advance()) {
			if (it.value() == value) {
				return true;
            }
		}
		return false;
	}

	default public boolean contains(IntFilter filter) {
		for (IntIterator it = intIterator(); it.isValid(); it.advance()) {
			if (filter.accept(it.value())) {
				return true;
            }
		}
		return false;
	}

	default public int count(int value) {
		int result = 0;
		for (IntIterator it = intIterator(); it.isValid(); it.advance()) {
			if (it.value() == value) {
				result++;
            }
		}
		return result;
	}

	default public int count(IntFilter filter) {
		int result = 0;
		for (IntIterator it = intIterator(); it.isValid(); it.advance()) {
			if (filter.accept(it.value())) {
				result++;
            }
		}
		return result;
	}

	default public int min() {
		int result = Integer.MAX_VALUE;
		for (IntIterator it = intIterator(); it.isValid(); it.advance()) {
			int current = it.value();
			if (current < result) {
				result = current;
			}
		}
		return result;
	}

	default public int min(IntComparator comparator) {
		int result = Integer.MIN_VALUE;
		for (IntIterator it = intIterator(); it.isValid(); it.advance()) {
			int current = it.value();
			if (result == Integer.MIN_VALUE || comparator.compare(result, current) > 0) {
				result = current;
			}
		}
		return result;
	}

	default public int max() {
		int result = Integer.MIN_VALUE;
		for (IntIterator it = intIterator(); it.isValid(); it.advance()) {
			int current = it.value();
			if (current > result) {
				result = current;
			}
		}
		return result;
	}

	default public int max(IntComparator comparator) {
		int result = Integer.MIN_VALUE;
		for (IntIterator it = intIterator(); it.isValid(); it.advance()) {
			int current = it.value();
			if (result == Integer.MIN_VALUE || comparator.compare(result, current) < 0) {
				result = current;
			}
		}
		return result;
	}

	default public long sum() {
		long result = 0;
		for (IntIterator it = intIterator(); it.isValid(); it.advance()) {
			result += it.value();
		}
		return result;
	}

	default public int[] qty(int bound) {
		int[] result = new int[bound];
		for (IntIterator it = intIterator(); it.isValid(); it.advance()) {
			result[(int)it.value()]++;
		}
		return result;
	}

	default public int[] qty() {
		return qty((int)(max() + 1));
	}

	default public boolean allOf(IntFilter f) {
		for (IntIterator it = intIterator(); it.isValid(); it.advance()) {
			if (!f.accept(it.value())) {
				return false;
			}
		}
		return true;
	}

	default public boolean anyOf(IntFilter f) {
		for (IntIterator it = intIterator(); it.isValid(); it.advance()) {
			if (f.accept(it.value())) {
				return true;
			}
		}
		return false;
	}

	default public boolean noneOf(IntFilter f) {
		for (IntIterator it = intIterator(); it.isValid(); it.advance()) {
			if (f.accept(it.value())) {
				return false;
			}
		}
		return true;
	}

	default boolean isPermutationOf(IntStream s) {
		return new IntArrayList(this).sort().equals(new IntArrayList(s).sort());
	}

	//views
    default public IntStream union(IntStream other) {
        return () -> new IntIterator() {
            private IntIterator first = IntStream.this.intIterator();
            private IntIterator second;

            public int value() throws NoSuchElementException {
                if (first.isValid()) {
                    return first.value();
                }
                return second.value();
            }

            public boolean advance() throws NoSuchElementException {
                if (first.isValid()) {
                    first.advance();
                    if (!first.isValid()) {
                        second = other.intIterator();
                    }
                    return second.isValid();
                } else {
                    return second.advance();
                }
            }

            public boolean isValid() {
                return first.isValid() || second.isValid();
            }

            public void remove() {
                if (first.isValid()) {
                    first.remove();
                } else {
                    second.remove();
                }
            }
        };
    }

	default public IntStream filter(IntFilter f) {
		return () -> new IntIterator() {
			private IntIterator it = IntStream.this.intIterator();

			{
				next();
			}

			private void next() {
				while (it.isValid() && !f.accept(it.value())) {
					it.advance();
				}
			}

			public int value() {
				return it.value();
			}

			public boolean advance() {
				it.advance();
				next();
				return isValid();
			}

            public boolean isValid() {
                return it.isValid();
            }

            public void remove() {
                it.remove();
            }
		};
	}

		default public DoubleStream map(IntToDoubleFunction function) {
		return () -> new DoubleIterator() {
            private IntIterator it = IntStream.this.intIterator();

            public double value() {
                return function.value(it.value());
            }

            public boolean advance() {
                return it.advance();
            }

            public boolean isValid() {
                return it.isValid();
            }

            public void remove() {
                it.remove();
            }
        };
	}

	default public IntStream map(IntToIntFunction function) {
		return () -> new IntIterator() {
            private IntIterator it = IntStream.this.intIterator();

            public int value() {
                return function.value(it.value());
            }

            public boolean advance() {
                return it.advance();
            }

            public boolean isValid() {
                return it.isValid();
            }

            public void remove() {
                it.remove();
            }
        };
	}

	default public LongStream map(IntToLongFunction function) {
		return () -> new LongIterator() {
            private IntIterator it = IntStream.this.intIterator();

            public long value() {
                return function.value(it.value());
            }

            public boolean advance() {
                return it.advance();
            }

            public boolean isValid() {
                return it.isValid();
            }

            public void remove() {
                it.remove();
            }
        };
	}

	default public CharStream map(IntToCharFunction function) {
		return () -> new CharIterator() {
            private IntIterator it = IntStream.this.intIterator();

            public char value() {
                return function.value(it.value());
            }

            public boolean advance() {
                return it.advance();
            }

            public boolean isValid() {
                return it.isValid();
            }

            public void remove() {
                it.remove();
            }
        };
	}
}