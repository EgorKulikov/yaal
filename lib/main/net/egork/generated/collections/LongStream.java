package net.egork.generated.collections;

import net.egork.generated.collections.comparator.LongComparator;
import net.egork.generated.collections.function.CharLongToCharFunction;
import net.egork.generated.collections.function.DoubleLongToDoubleFunction;
import net.egork.generated.collections.function.IntLongToIntFunction;
import net.egork.generated.collections.function.LongCharToCharFunction;
import net.egork.generated.collections.function.LongCharToDoubleFunction;
import net.egork.generated.collections.function.LongCharToIntFunction;
import net.egork.generated.collections.function.LongCharToLongFunction;
import net.egork.generated.collections.function.LongDoubleToCharFunction;
import net.egork.generated.collections.function.LongDoubleToDoubleFunction;
import net.egork.generated.collections.function.LongDoubleToIntFunction;
import net.egork.generated.collections.function.LongDoubleToLongFunction;
import net.egork.generated.collections.function.LongFilter;
import net.egork.generated.collections.function.LongIntToCharFunction;
import net.egork.generated.collections.function.LongIntToDoubleFunction;
import net.egork.generated.collections.function.LongIntToIntFunction;
import net.egork.generated.collections.function.LongIntToLongFunction;
import net.egork.generated.collections.function.LongLongToCharFunction;
import net.egork.generated.collections.function.LongLongToDoubleFunction;
import net.egork.generated.collections.function.LongLongToIntFunction;
import net.egork.generated.collections.function.LongLongToLongFunction;
import net.egork.generated.collections.function.LongTask;
import net.egork.generated.collections.function.LongToCharFunction;
import net.egork.generated.collections.function.LongToDoubleFunction;
import net.egork.generated.collections.function.LongToIntFunction;
import net.egork.generated.collections.function.LongToLongFunction;
import net.egork.generated.collections.iterator.CharIterator;
import net.egork.generated.collections.iterator.DoubleIterator;
import net.egork.generated.collections.iterator.IntIterator;
import net.egork.generated.collections.iterator.LongIterator;
import net.egork.generated.collections.list.LongArrayList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public interface LongStream extends Iterable<Long>, Comparable<LongStream> {
    //abstract
    public LongIterator longIterator();

    //base
    default public Iterator<Long> iterator() {
        return new Iterator<Long>() {
            private LongIterator it = longIterator();

            public boolean hasNext() {
                return it.isValid();
            }

            public Long next() {
                long result = it.value();
                it.advance();
                return result;
            }
        };
    }

    default public long first() {
        return longIterator().value();
    }

    default public LongCollection compute() {
        return new LongArrayList(this);
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

    //algorithms
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

    default public int[] qty(int bound) {
        int[] result = new int[bound];
        for (LongIterator it = longIterator(); it.isValid(); it.advance()) {
            result[(int) it.value()]++;
        }
        return result;
    }

    default public int[] qty() {
        return qty((int) (max() + 1));
    }

    default public boolean allOf(LongFilter f) {
        for (LongIterator it = longIterator(); it.isValid(); it.advance()) {
            if (!f.accept(it.value())) {
                return false;
            }
        }
        return true;
    }

    default public boolean anyOf(LongFilter f) {
        for (LongIterator it = longIterator(); it.isValid(); it.advance()) {
            if (f.accept(it.value())) {
                return true;
            }
        }
        return false;
    }

    default public boolean noneOf(LongFilter f) {
        for (LongIterator it = longIterator(); it.isValid(); it.advance()) {
            if (f.accept(it.value())) {
                return false;
            }
        }
        return true;
    }

    default public long reduce(LongLongToLongFunction f) {
        LongIterator it = longIterator();
        if (!it.isValid()) {
            return Long.MIN_VALUE;
        }
        long result = it.value();
        while (it.advance()) {
            result = f.value(result, it.value());
        }
        return result;
    }

    default public double reduce(double initial, DoubleLongToDoubleFunction f) {
        for (LongIterator it = longIterator(); it.isValid(); it.advance()) {
            initial = f.value(initial, it.value());
        }
        return initial;
    }

    default public int reduce(int initial, IntLongToIntFunction f) {
        for (LongIterator it = longIterator(); it.isValid(); it.advance()) {
            initial = f.value(initial, it.value());
        }
        return initial;
    }

    default public long reduce(long initial, LongLongToLongFunction f) {
        for (LongIterator it = longIterator(); it.isValid(); it.advance()) {
            initial = f.value(initial, it.value());
        }
        return initial;
    }

    default public char reduce(char initial, CharLongToCharFunction f) {
        for (LongIterator it = longIterator(); it.isValid(); it.advance()) {
            initial = f.value(initial, it.value());
        }
        return initial;
    }

    //views
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

    default public DoubleStream join(DoubleStream other, LongDoubleToDoubleFunction f) {
        return () -> new DoubleIterator() {
            private LongIterator it = LongStream.this.longIterator();
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

    default public IntStream join(DoubleStream other, LongDoubleToIntFunction f) {
        return () -> new IntIterator() {
            private LongIterator it = LongStream.this.longIterator();
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

    default public LongStream join(DoubleStream other, LongDoubleToLongFunction f) {
        return () -> new LongIterator() {
            private LongIterator it = LongStream.this.longIterator();
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

    default public CharStream join(DoubleStream other, LongDoubleToCharFunction f) {
        return () -> new CharIterator() {
            private LongIterator it = LongStream.this.longIterator();
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

    default public DoubleStream join(IntStream other, LongIntToDoubleFunction f) {
        return () -> new DoubleIterator() {
            private LongIterator it = LongStream.this.longIterator();
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

    default public IntStream join(IntStream other, LongIntToIntFunction f) {
        return () -> new IntIterator() {
            private LongIterator it = LongStream.this.longIterator();
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

    default public LongStream join(IntStream other, LongIntToLongFunction f) {
        return () -> new LongIterator() {
            private LongIterator it = LongStream.this.longIterator();
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

    default public CharStream join(IntStream other, LongIntToCharFunction f) {
        return () -> new CharIterator() {
            private LongIterator it = LongStream.this.longIterator();
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

    default public DoubleStream join(LongStream other, LongLongToDoubleFunction f) {
        return () -> new DoubleIterator() {
            private LongIterator it = LongStream.this.longIterator();
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

    default public IntStream join(LongStream other, LongLongToIntFunction f) {
        return () -> new IntIterator() {
            private LongIterator it = LongStream.this.longIterator();
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

    default public LongStream join(LongStream other, LongLongToLongFunction f) {
        return () -> new LongIterator() {
            private LongIterator it = LongStream.this.longIterator();
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

    default public CharStream join(LongStream other, LongLongToCharFunction f) {
        return () -> new CharIterator() {
            private LongIterator it = LongStream.this.longIterator();
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

    default public DoubleStream join(CharStream other, LongCharToDoubleFunction f) {
        return () -> new DoubleIterator() {
            private LongIterator it = LongStream.this.longIterator();
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

    default public IntStream join(CharStream other, LongCharToIntFunction f) {
        return () -> new IntIterator() {
            private LongIterator it = LongStream.this.longIterator();
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

    default public LongStream join(CharStream other, LongCharToLongFunction f) {
        return () -> new LongIterator() {
            private LongIterator it = LongStream.this.longIterator();
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

    default public CharStream join(CharStream other, LongCharToCharFunction f) {
        return () -> new CharIterator() {
            private LongIterator it = LongStream.this.longIterator();
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