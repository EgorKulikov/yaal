package net.egork.generated.collections;

import java.util.NoSuchElementException;
import java.util.Iterator;

import net.egork.generated.collections.iterator.*;
import net.egork.generated.collections.function.*;
import net.egork.generated.collections.list.*;
import net.egork.generated.collections.comparator.*;

public interface LongStream extends Iterable<Long>, Comparable<LongStream> {
	public LongIterator longIterator();

	default public Iterator<Long> iterator() {
		return new Iterator<Long>() {
			private LongIterator it = longIterator();

			public boolean hasNext() {
				return it.advance();
			}

			public Long next() {
				return it.value();
			}

			public void remove() {
				it.remove();
			}
		};
	}

	default public long first() {
		return longIterator().value();
	}

	default public void forEach(LongTask task) {
		for (LongIterator it = longIterator(); it.isValid(); it.advance()) {
			task.process(it.value());
        }
	}

	default public boolean contains(long value) {
		for (LongIterator it = longIterator(); it.isValid(); it.advance()) {
			if (it.value() == value) {
				return true;
            }
		}
		return false;
	}

	default public boolean contains(LongFilter filter) {
		for (LongIterator it = longIterator(); it.isValid(); it.advance()) {
			if (filter.accept(it.value())) {
				return true;
            }
		}
		return false;
	}

	default public int count(long value) {
		int result = 0;
		for (LongIterator it = longIterator(); it.isValid(); it.advance()) {
			if (it.value() == value) {
				result++;
            }
		}
		return result;
	}

	default public int count(LongFilter filter) {
		int result = 0;
		for (LongIterator it = longIterator(); it.isValid(); it.advance()) {
			if (filter.accept(it.value())) {
				result++;
            }
		}
		return result;
	}

	default public long min() {
		long result = Long.MAX_VALUE;
		for (LongIterator it = longIterator(); it.isValid(); it.advance()) {
			long current = it.value();
			if (current < result) {
				result = current;
			}
		}
		return result;
	}

	default public long min(LongComparator comparator) {
		long result = Long.MIN_VALUE;
		for (LongIterator it = longIterator(); it.isValid(); it.advance()) {
			long current = it.value();
			if (result == Long.MIN_VALUE || comparator.compare(result, current) > 0) {
				result = current;
			}
		}
		return result;
	}

	default public long max() {
		long result = Long.MIN_VALUE;
		for (LongIterator it = longIterator(); it.isValid(); it.advance()) {
			long current = it.value();
			if (current > result) {
				result = current;
			}
		}
		return result;
	}

	default public long max(LongComparator comparator) {
		long result = Long.MIN_VALUE;
		for (LongIterator it = longIterator(); it.isValid(); it.advance()) {
			long current = it.value();
			if (result == Long.MIN_VALUE || comparator.compare(result, current) < 0) {
				result = current;
			}
		}
		return result;
	}

	default public long sum() {
		long result = 0;
		for (LongIterator it = longIterator(); it.isValid(); it.advance()) {
			result += it.value();
		}
		return result;
	}

	default public LongCollection compute() {
		return new LongArrayList(this);
	}

    default public LongStream union(LongStream other) {
        return () -> new LongIterator() {
            private LongIterator first = LongStream.this.longIterator();
            private LongIterator second;

            public long value() throws NoSuchElementException {
                if (first.isValid()) {
                    return first.value();
                }
                return second.value();
            }

            public boolean advance() throws NoSuchElementException {
                if (first.isValid()) {
                    first.advance();
                    if (!first.isValid()) {
                        second = other.longIterator();
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

	default public int[] qty(int bound) {
		int[] result = new int[bound];
		for (LongIterator it = longIterator(); it.isValid(); it.advance()) {
			result[(int)it.value()]++;
		}
		return result;
	}

	default public int[] qty() {
		return qty((int)(max() + 1));
	}

	default public int compareTo(LongStream c) {
		LongIterator it = longIterator();
		LongIterator jt = c.longIterator();
		while (it.isValid() && jt.isValid()) {
			long i = it.value();
			long j = jt.value();
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

	default public LongStream filter(LongFilter f) {
		return () -> new LongIterator() {
			private LongIterator it = LongStream.this.longIterator();

			{
				next();
			}

			private void next() {
				while (it.isValid() && !f.accept(it.value())) {
					it.advance();
				}
			}

			public long value() {
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

		default public DoubleStream map(LongToDoubleFunction function) {
		return () -> new DoubleIterator() {
            private LongIterator it = LongStream.this.longIterator();

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

	default public IntStream map(LongToIntFunction function) {
		return () -> new IntIterator() {
            private LongIterator it = LongStream.this.longIterator();

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

	default public LongStream map(LongToLongFunction function) {
		return () -> new LongIterator() {
            private LongIterator it = LongStream.this.longIterator();

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

	default public CharStream map(LongToCharFunction function) {
		return () -> new CharIterator() {
            private LongIterator it = LongStream.this.longIterator();

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