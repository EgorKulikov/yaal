package net.egork.generated.collections;

import java.util.NoSuchElementException;
import java.util.Iterator;

import net.egork.generated.collections.iterator.*;
import net.egork.generated.collections.function.*;
import net.egork.generated.collections.list.*;
import net.egork.generated.collections.comparator.*;

public interface DoubleStream extends Iterable<Double>, Comparable<DoubleStream> {
	public DoubleIterator doubleIterator();

	default public Iterator<Double> iterator() {
		return new Iterator<Double>() {
			private DoubleIterator it = doubleIterator();

			public boolean hasNext() {
				return it.advance();
			}

			public Double next() {
				return it.value();
			}

			public void remove() {
				it.remove();
			}
		};
	}

	default public double first() {
		return doubleIterator().value();
	}

	default public void forEach(DoubleTask task) {
		for (DoubleIterator it = doubleIterator(); it.isValid(); it.advance()) {
			task.process(it.value());
        }
	}

	default public boolean contains(double value) {
		for (DoubleIterator it = doubleIterator(); it.isValid(); it.advance()) {
			if (it.value() == value) {
				return true;
            }
		}
		return false;
	}

	default public boolean contains(DoubleFilter filter) {
		for (DoubleIterator it = doubleIterator(); it.isValid(); it.advance()) {
			if (filter.accept(it.value())) {
				return true;
            }
		}
		return false;
	}

	default public int count(double value) {
		int result = 0;
		for (DoubleIterator it = doubleIterator(); it.isValid(); it.advance()) {
			if (it.value() == value) {
				result++;
            }
		}
		return result;
	}

	default public int count(DoubleFilter filter) {
		int result = 0;
		for (DoubleIterator it = doubleIterator(); it.isValid(); it.advance()) {
			if (filter.accept(it.value())) {
				result++;
            }
		}
		return result;
	}

	default public double min() {
		double result = Double.POSITIVE_INFINITY;
		for (DoubleIterator it = doubleIterator(); it.isValid(); it.advance()) {
			double current = it.value();
			if (current < result) {
				result = current;
			}
		}
		return result;
	}

	default public double min(DoubleComparator comparator) {
		double result = Double.MIN_NORMAL;
		for (DoubleIterator it = doubleIterator(); it.isValid(); it.advance()) {
			double current = it.value();
			if (result == Double.MIN_NORMAL || comparator.compare(result, current) > 0) {
				result = current;
			}
		}
		return result;
	}

	default public double max() {
		double result = Double.NEGATIVE_INFINITY;
		for (DoubleIterator it = doubleIterator(); it.isValid(); it.advance()) {
			double current = it.value();
			if (current > result) {
				result = current;
			}
		}
		return result;
	}

	default public double max(DoubleComparator comparator) {
		double result = Double.MIN_NORMAL;
		for (DoubleIterator it = doubleIterator(); it.isValid(); it.advance()) {
			double current = it.value();
			if (result == Double.MIN_NORMAL || comparator.compare(result, current) < 0) {
				result = current;
			}
		}
		return result;
	}

	default public double sum() {
		double result = 0;
		for (DoubleIterator it = doubleIterator(); it.isValid(); it.advance()) {
			result += it.value();
		}
		return result;
	}

	default public DoubleCollection compute() {
		return new DoubleArrayList(this);
	}

    default public DoubleStream union(DoubleStream other) {
        return () -> new DoubleIterator() {
            private DoubleIterator first = DoubleStream.this.doubleIterator();
            private DoubleIterator second;

            public double value() throws NoSuchElementException {
                if (first.isValid()) {
                    return first.value();
                }
                return second.value();
            }

            public boolean advance() throws NoSuchElementException {
                if (first.isValid()) {
                    first.advance();
                    if (!first.isValid()) {
                        second = other.doubleIterator();
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
		for (DoubleIterator it = doubleIterator(); it.isValid(); it.advance()) {
			result[(int)it.value()]++;
		}
		return result;
	}

	default public int[] qty() {
		return qty((int)(max() + 1));
	}

	default public int compareTo(DoubleStream c) {
		DoubleIterator it = doubleIterator();
		DoubleIterator jt = c.doubleIterator();
		while (it.isValid() && jt.isValid()) {
			double i = it.value();
			double j = jt.value();
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

	default public DoubleStream filter(DoubleFilter f) {
		return () -> new DoubleIterator() {
			private DoubleIterator it = DoubleStream.this.doubleIterator();

			{
				next();
			}

			private void next() {
				while (it.isValid() && !f.accept(it.value())) {
					it.advance();
				}
			}

			public double value() {
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

		default public DoubleStream map(DoubleToDoubleFunction function) {
		return () -> new DoubleIterator() {
            private DoubleIterator it = DoubleStream.this.doubleIterator();

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

	default public IntStream map(DoubleToIntFunction function) {
		return () -> new IntIterator() {
            private DoubleIterator it = DoubleStream.this.doubleIterator();

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

	default public LongStream map(DoubleToLongFunction function) {
		return () -> new LongIterator() {
            private DoubleIterator it = DoubleStream.this.doubleIterator();

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

	default public CharStream map(DoubleToCharFunction function) {
		return () -> new CharIterator() {
            private DoubleIterator it = DoubleStream.this.doubleIterator();

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