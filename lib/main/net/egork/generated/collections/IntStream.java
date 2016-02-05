package net.egork.generated.collections;

import net.egork.generated.collections.comparator.IntComparator;
import net.egork.generated.collections.function.CharIntToCharFunction;
import net.egork.generated.collections.function.DoubleIntToDoubleFunction;
import net.egork.generated.collections.function.IntCharToCharFunction;
import net.egork.generated.collections.function.IntCharToDoubleFunction;
import net.egork.generated.collections.function.IntCharToIntFunction;
import net.egork.generated.collections.function.IntCharToLongFunction;
import net.egork.generated.collections.function.IntDoubleToCharFunction;
import net.egork.generated.collections.function.IntDoubleToDoubleFunction;
import net.egork.generated.collections.function.IntDoubleToIntFunction;
import net.egork.generated.collections.function.IntDoubleToLongFunction;
import net.egork.generated.collections.function.IntFilter;
import net.egork.generated.collections.function.IntIntToCharFunction;
import net.egork.generated.collections.function.IntIntToDoubleFunction;
import net.egork.generated.collections.function.IntIntToIntFunction;
import net.egork.generated.collections.function.IntIntToLongFunction;
import net.egork.generated.collections.function.IntLongToCharFunction;
import net.egork.generated.collections.function.IntLongToDoubleFunction;
import net.egork.generated.collections.function.IntLongToIntFunction;
import net.egork.generated.collections.function.IntLongToLongFunction;
import net.egork.generated.collections.function.IntTask;
import net.egork.generated.collections.function.IntToCharFunction;
import net.egork.generated.collections.function.IntToDoubleFunction;
import net.egork.generated.collections.function.IntToIntFunction;
import net.egork.generated.collections.function.IntToLongFunction;
import net.egork.generated.collections.function.LongIntToLongFunction;
import net.egork.generated.collections.iterator.CharIterator;
import net.egork.generated.collections.iterator.DoubleIterator;
import net.egork.generated.collections.iterator.IntIterator;
import net.egork.generated.collections.iterator.LongIterator;
import net.egork.generated.collections.list.IntArrayList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public interface IntStream extends Iterable<Integer>, Comparable<IntStream> {
    //abstract
    public IntIterator intIterator();

    //base
    default public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private IntIterator it = intIterator();

            public boolean hasNext() {
                return it.isValid();
            }

            public Integer next() {
                int result = it.value();
                it.advance();
                return result;
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
            result[(int) it.value()]++;
        }
        return result;
    }

    default public int[] qty() {
        return qty((int) (max() + 1));
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

    default public int reduce(IntIntToIntFunction f) {
        IntIterator it = intIterator();
        if (!it.isValid()) {
            return Integer.MIN_VALUE;
        }
        int result = it.value();
        while (it.advance()) {
            result = f.value(result, it.value());
        }
        return result;
    }

    default public double reduce(double initial, DoubleIntToDoubleFunction f) {
        for (IntIterator it = intIterator(); it.isValid(); it.advance()) {
            initial = f.value(initial, it.value());
        }
        return initial;
    }

    default public int reduce(int initial, IntIntToIntFunction f) {
        for (IntIterator it = intIterator(); it.isValid(); it.advance()) {
            initial = f.value(initial, it.value());
        }
        return initial;
    }

    default public long reduce(long initial, LongIntToLongFunction f) {
        for (IntIterator it = intIterator(); it.isValid(); it.advance()) {
            initial = f.value(initial, it.value());
        }
        return initial;
    }

    default public char reduce(char initial, CharIntToCharFunction f) {
        for (IntIterator it = intIterator(); it.isValid(); it.advance()) {
            initial = f.value(initial, it.value());
        }
        return initial;
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

    default public DoubleStream join(DoubleStream other, IntDoubleToDoubleFunction f) {
        return () -> new DoubleIterator() {
            private IntIterator it = IntStream.this.intIterator();
            private DoubleIterator jt = other.doubleIterator();

            public double value() {
                return f.value(it.value(), jt.value());
            }

            public boolean advance() {
                return it.advance() && jt.advance();
            }

            public boolean isValid() {
                return it.isValid() && jt.isValid();
            }

            public void remove() {
                it.remove();
                jt.remove();
            }
        };
    }

    default public IntStream join(DoubleStream other, IntDoubleToIntFunction f) {
        return () -> new IntIterator() {
            private IntIterator it = IntStream.this.intIterator();
            private DoubleIterator jt = other.doubleIterator();

            public int value() {
                return f.value(it.value(), jt.value());
            }

            public boolean advance() {
                return it.advance() && jt.advance();
            }

            public boolean isValid() {
                return it.isValid() && jt.isValid();
            }

            public void remove() {
                it.remove();
                jt.remove();
            }
        };
    }

    default public LongStream join(DoubleStream other, IntDoubleToLongFunction f) {
        return () -> new LongIterator() {
            private IntIterator it = IntStream.this.intIterator();
            private DoubleIterator jt = other.doubleIterator();

            public long value() {
                return f.value(it.value(), jt.value());
            }

            public boolean advance() {
                return it.advance() && jt.advance();
            }

            public boolean isValid() {
                return it.isValid() && jt.isValid();
            }

            public void remove() {
                it.remove();
                jt.remove();
            }
        };
    }

    default public CharStream join(DoubleStream other, IntDoubleToCharFunction f) {
        return () -> new CharIterator() {
            private IntIterator it = IntStream.this.intIterator();
            private DoubleIterator jt = other.doubleIterator();

            public char value() {
                return f.value(it.value(), jt.value());
            }

            public boolean advance() {
                return it.advance() && jt.advance();
            }

            public boolean isValid() {
                return it.isValid() && jt.isValid();
            }

            public void remove() {
                it.remove();
                jt.remove();
            }
        };
    }

    default public DoubleStream join(IntStream other, IntIntToDoubleFunction f) {
        return () -> new DoubleIterator() {
            private IntIterator it = IntStream.this.intIterator();
            private IntIterator jt = other.intIterator();

            public double value() {
                return f.value(it.value(), jt.value());
            }

            public boolean advance() {
                return it.advance() && jt.advance();
            }

            public boolean isValid() {
                return it.isValid() && jt.isValid();
            }

            public void remove() {
                it.remove();
                jt.remove();
            }
        };
    }

    default public IntStream join(IntStream other, IntIntToIntFunction f) {
        return () -> new IntIterator() {
            private IntIterator it = IntStream.this.intIterator();
            private IntIterator jt = other.intIterator();

            public int value() {
                return f.value(it.value(), jt.value());
            }

            public boolean advance() {
                return it.advance() && jt.advance();
            }

            public boolean isValid() {
                return it.isValid() && jt.isValid();
            }

            public void remove() {
                it.remove();
                jt.remove();
            }
        };
    }

    default public LongStream join(IntStream other, IntIntToLongFunction f) {
        return () -> new LongIterator() {
            private IntIterator it = IntStream.this.intIterator();
            private IntIterator jt = other.intIterator();

            public long value() {
                return f.value(it.value(), jt.value());
            }

            public boolean advance() {
                return it.advance() && jt.advance();
            }

            public boolean isValid() {
                return it.isValid() && jt.isValid();
            }

            public void remove() {
                it.remove();
                jt.remove();
            }
        };
    }

    default public CharStream join(IntStream other, IntIntToCharFunction f) {
        return () -> new CharIterator() {
            private IntIterator it = IntStream.this.intIterator();
            private IntIterator jt = other.intIterator();

            public char value() {
                return f.value(it.value(), jt.value());
            }

            public boolean advance() {
                return it.advance() && jt.advance();
            }

            public boolean isValid() {
                return it.isValid() && jt.isValid();
            }

            public void remove() {
                it.remove();
                jt.remove();
            }
        };
    }

    default public DoubleStream join(LongStream other, IntLongToDoubleFunction f) {
        return () -> new DoubleIterator() {
            private IntIterator it = IntStream.this.intIterator();
            private LongIterator jt = other.longIterator();

            public double value() {
                return f.value(it.value(), jt.value());
            }

            public boolean advance() {
                return it.advance() && jt.advance();
            }

            public boolean isValid() {
                return it.isValid() && jt.isValid();
            }

            public void remove() {
                it.remove();
                jt.remove();
            }
        };
    }

    default public IntStream join(LongStream other, IntLongToIntFunction f) {
        return () -> new IntIterator() {
            private IntIterator it = IntStream.this.intIterator();
            private LongIterator jt = other.longIterator();

            public int value() {
                return f.value(it.value(), jt.value());
            }

            public boolean advance() {
                return it.advance() && jt.advance();
            }

            public boolean isValid() {
                return it.isValid() && jt.isValid();
            }

            public void remove() {
                it.remove();
                jt.remove();
            }
        };
    }

    default public LongStream join(LongStream other, IntLongToLongFunction f) {
        return () -> new LongIterator() {
            private IntIterator it = IntStream.this.intIterator();
            private LongIterator jt = other.longIterator();

            public long value() {
                return f.value(it.value(), jt.value());
            }

            public boolean advance() {
                return it.advance() && jt.advance();
            }

            public boolean isValid() {
                return it.isValid() && jt.isValid();
            }

            public void remove() {
                it.remove();
                jt.remove();
            }
        };
    }

    default public CharStream join(LongStream other, IntLongToCharFunction f) {
        return () -> new CharIterator() {
            private IntIterator it = IntStream.this.intIterator();
            private LongIterator jt = other.longIterator();

            public char value() {
                return f.value(it.value(), jt.value());
            }

            public boolean advance() {
                return it.advance() && jt.advance();
            }

            public boolean isValid() {
                return it.isValid() && jt.isValid();
            }

            public void remove() {
                it.remove();
                jt.remove();
            }
        };
    }

    default public DoubleStream join(CharStream other, IntCharToDoubleFunction f) {
        return () -> new DoubleIterator() {
            private IntIterator it = IntStream.this.intIterator();
            private CharIterator jt = other.charIterator();

            public double value() {
                return f.value(it.value(), jt.value());
            }

            public boolean advance() {
                return it.advance() && jt.advance();
            }

            public boolean isValid() {
                return it.isValid() && jt.isValid();
            }

            public void remove() {
                it.remove();
                jt.remove();
            }
        };
    }

    default public IntStream join(CharStream other, IntCharToIntFunction f) {
        return () -> new IntIterator() {
            private IntIterator it = IntStream.this.intIterator();
            private CharIterator jt = other.charIterator();

            public int value() {
                return f.value(it.value(), jt.value());
            }

            public boolean advance() {
                return it.advance() && jt.advance();
            }

            public boolean isValid() {
                return it.isValid() && jt.isValid();
            }

            public void remove() {
                it.remove();
                jt.remove();
            }
        };
    }

    default public LongStream join(CharStream other, IntCharToLongFunction f) {
        return () -> new LongIterator() {
            private IntIterator it = IntStream.this.intIterator();
            private CharIterator jt = other.charIterator();

            public long value() {
                return f.value(it.value(), jt.value());
            }

            public boolean advance() {
                return it.advance() && jt.advance();
            }

            public boolean isValid() {
                return it.isValid() && jt.isValid();
            }

            public void remove() {
                it.remove();
                jt.remove();
            }
        };
    }

    default public CharStream join(CharStream other, IntCharToCharFunction f) {
        return () -> new CharIterator() {
            private IntIterator it = IntStream.this.intIterator();
            private CharIterator jt = other.charIterator();

            public char value() {
                return f.value(it.value(), jt.value());
            }

            public boolean advance() {
                return it.advance() && jt.advance();
            }

            public boolean isValid() {
                return it.isValid() && jt.isValid();
            }

            public void remove() {
                it.remove();
                jt.remove();
            }
        };
    }
}