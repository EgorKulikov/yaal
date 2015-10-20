package net.egork.generated.collections;

import java.util.NoSuchElementException;
import java.util.Iterator;

import net.egork.generated.collections.iterator.*;
import net.egork.generated.collections.function.*;
import net.egork.generated.collections.list.*;
import net.egork.generated.collections.comparator.*;

public interface CharStream extends Iterable<Character>, Comparable<CharStream> {
	public CharIterator charIterator();

	default public Iterator<Character> iterator() {
		return new Iterator<Character>() {
			private CharIterator it = charIterator();

			public boolean hasNext() {
				return it.advance();
			}

			public Character next() {
				return it.value();
			}

			public void remove() {
				it.remove();
			}
		};
	}

	default public char first() {
		return charIterator().value();
	}

	default public void forEach(CharTask task) {
		for (CharIterator it = charIterator(); it.isValid(); it.advance()) {
			task.process(it.value());
        }
	}

	default public boolean contains(char value) {
		for (CharIterator it = charIterator(); it.isValid(); it.advance()) {
			if (it.value() == value) {
				return true;
            }
		}
		return false;
	}

	default public boolean contains(CharFilter filter) {
		for (CharIterator it = charIterator(); it.isValid(); it.advance()) {
			if (filter.accept(it.value())) {
				return true;
            }
		}
		return false;
	}

	default public int count(char value) {
		int result = 0;
		for (CharIterator it = charIterator(); it.isValid(); it.advance()) {
			if (it.value() == value) {
				result++;
            }
		}
		return result;
	}

	default public int count(CharFilter filter) {
		int result = 0;
		for (CharIterator it = charIterator(); it.isValid(); it.advance()) {
			if (filter.accept(it.value())) {
				result++;
            }
		}
		return result;
	}

	default public char min() {
		char result = Character.MAX_VALUE;
		for (CharIterator it = charIterator(); it.isValid(); it.advance()) {
			char current = it.value();
			if (current < result) {
				result = current;
			}
		}
		return result;
	}

	default public char min(CharComparator comparator) {
		char result = Character.MAX_VALUE;
		for (CharIterator it = charIterator(); it.isValid(); it.advance()) {
			char current = it.value();
			if (result == Character.MAX_VALUE || comparator.compare(result, current) > 0) {
				result = current;
			}
		}
		return result;
	}

	default public char max() {
		char result = Character.MIN_VALUE;
		for (CharIterator it = charIterator(); it.isValid(); it.advance()) {
			char current = it.value();
			if (current > result) {
				result = current;
			}
		}
		return result;
	}

	default public char max(CharComparator comparator) {
		char result = Character.MAX_VALUE;
		for (CharIterator it = charIterator(); it.isValid(); it.advance()) {
			char current = it.value();
			if (result == Character.MAX_VALUE || comparator.compare(result, current) < 0) {
				result = current;
			}
		}
		return result;
	}

	default public int sum() {
		int result = 0;
		for (CharIterator it = charIterator(); it.isValid(); it.advance()) {
			result += it.value();
		}
		return result;
	}

	default public CharCollection compute() {
		return new CharArrayList(this);
	}

    default public CharStream union(CharStream other) {
        return () -> new CharIterator() {
            private CharIterator first = CharStream.this.charIterator();
            private CharIterator second;

            public char value() throws NoSuchElementException {
                if (first.isValid()) {
                    return first.value();
                }
                return second.value();
            }

            public boolean advance() throws NoSuchElementException {
                if (first.isValid()) {
                    first.advance();
                    if (!first.isValid()) {
                        second = other.charIterator();
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
		for (CharIterator it = charIterator(); it.isValid(); it.advance()) {
			result[(int)it.value()]++;
		}
		return result;
	}

	default public int[] qty() {
		return qty((int)(max() + 1));
	}

	default public int compareTo(CharStream c) {
		CharIterator it = charIterator();
		CharIterator jt = c.charIterator();
		while (it.isValid() && jt.isValid()) {
			char i = it.value();
			char j = jt.value();
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

	default public CharStream filter(CharFilter f) {
		return () -> new CharIterator() {
			private CharIterator it = CharStream.this.charIterator();

			{
				next();
			}

			private void next() {
				while (it.isValid() && !f.accept(it.value())) {
					it.advance();
				}
			}

			public char value() {
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

		default public DoubleStream map(CharToDoubleFunction function) {
		return () -> new DoubleIterator() {
            private CharIterator it = CharStream.this.charIterator();

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

	default public IntStream map(CharToIntFunction function) {
		return () -> new IntIterator() {
            private CharIterator it = CharStream.this.charIterator();

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

	default public LongStream map(CharToLongFunction function) {
		return () -> new LongIterator() {
            private CharIterator it = CharStream.this.charIterator();

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

	default public CharStream map(CharToCharFunction function) {
		return () -> new CharIterator() {
            private CharIterator it = CharStream.this.charIterator();

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