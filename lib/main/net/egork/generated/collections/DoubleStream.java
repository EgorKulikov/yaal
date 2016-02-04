package net.egork.generated.collections;

import net.egork.generated.collections.comparator.DoubleComparator;
import net.egork.generated.collections.function.CharDoubleToCharFunction;
import net.egork.generated.collections.function.DoubleCharToCharFunction;
import net.egork.generated.collections.function.DoubleCharToDoubleFunction;
import net.egork.generated.collections.function.DoubleCharToIntFunction;
import net.egork.generated.collections.function.DoubleCharToLongFunction;
import net.egork.generated.collections.function.DoubleDoubleToCharFunction;
import net.egork.generated.collections.function.DoubleDoubleToDoubleFunction;
import net.egork.generated.collections.function.DoubleDoubleToIntFunction;
import net.egork.generated.collections.function.DoubleDoubleToLongFunction;
import net.egork.generated.collections.function.DoubleFilter;
import net.egork.generated.collections.function.DoubleIntToCharFunction;
import net.egork.generated.collections.function.DoubleIntToDoubleFunction;
import net.egork.generated.collections.function.DoubleIntToIntFunction;
import net.egork.generated.collections.function.DoubleIntToLongFunction;
import net.egork.generated.collections.function.DoubleLongToCharFunction;
import net.egork.generated.collections.function.DoubleLongToDoubleFunction;
import net.egork.generated.collections.function.DoubleLongToIntFunction;
import net.egork.generated.collections.function.DoubleLongToLongFunction;
import net.egork.generated.collections.function.DoubleTask;
import net.egork.generated.collections.function.DoubleToCharFunction;
import net.egork.generated.collections.function.DoubleToDoubleFunction;
import net.egork.generated.collections.function.DoubleToIntFunction;
import net.egork.generated.collections.function.DoubleToLongFunction;
import net.egork.generated.collections.function.IntDoubleToIntFunction;
import net.egork.generated.collections.function.LongDoubleToLongFunction;
import net.egork.generated.collections.iterator.CharIterator;
import net.egork.generated.collections.iterator.DoubleIterator;
import net.egork.generated.collections.iterator.IntIterator;
import net.egork.generated.collections.iterator.LongIterator;
import net.egork.generated.collections.list.DoubleArrayList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public interface DoubleStream extends Iterable<Double>, Comparable<DoubleStream> {
    //abstract
    public DoubleIterator doubleIterator();

    //base
    default public Iterator<Double> iterator() {
        return new Iterator<Double>() {
            private DoubleIterator it = doubleIterator();

            public boolean hasNext() {
                return it.isValid();
            }

            public Double next() {
                double result = it.value();
                it.advance();
                return result;
            }
        };
    }

    default public double first() {
        return doubleIterator().value();
    }

    default public DoubleCollection compute() {
        return new DoubleArrayList(this);
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

    //algorithms
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

    default public int[] qty(int bound) {
        int[] result = new int[bound];
        for (DoubleIterator it = doubleIterator(); it.isValid(); it.advance()) {
            result[(int) it.value()]++;
        }
        return result;
    }

    default public int[] qty() {
        return qty((int) (max() + 1));
    }

    default public boolean allOf(DoubleFilter f) {
        for (DoubleIterator it = doubleIterator(); it.isValid(); it.advance()) {
            if (!f.accept(it.value())) {
                return false;
            }
        }
        return true;
    }

    default public boolean anyOf(DoubleFilter f) {
        for (DoubleIterator it = doubleIterator(); it.isValid(); it.advance()) {
            if (f.accept(it.value())) {
                return true;
            }
        }
        return false;
    }

    default public boolean noneOf(DoubleFilter f) {
        for (DoubleIterator it = doubleIterator(); it.isValid(); it.advance()) {
            if (f.accept(it.value())) {
                return false;
            }
        }
        return true;
    }

    default public double reduce(DoubleDoubleToDoubleFunction f) {
        DoubleIterator it = doubleIterator();
        if (!it.isValid()) {
            return Double.MIN_NORMAL;
        }
        double result = it.value();
        while (it.advance()) {
            result = f.value(result, it.value());
        }
        return result;
    }

    default public double reduce(double initial, DoubleDoubleToDoubleFunction f) {
        for (DoubleIterator it = doubleIterator(); it.isValid(); it.advance()) {
            initial = f.value(initial, it.value());
        }
        return initial;
    }

    default public int reduce(int initial, IntDoubleToIntFunction f) {
        for (DoubleIterator it = doubleIterator(); it.isValid(); it.advance()) {
            initial = f.value(initial, it.value());
        }
        return initial;
    }

    default public long reduce(long initial, LongDoubleToLongFunction f) {
        for (DoubleIterator it = doubleIterator(); it.isValid(); it.advance()) {
            initial = f.value(initial, it.value());
        }
        return initial;
    }

    default public char reduce(char initial, CharDoubleToCharFunction f) {
        for (DoubleIterator it = doubleIterator(); it.isValid(); it.advance()) {
            initial = f.value(initial, it.value());
        }
        return initial;
    }

    //views
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

    default public DoubleStream join(DoubleStream other, DoubleDoubleToDoubleFunction f) {
        return () -> new DoubleIterator() {
            private DoubleIterator it = DoubleStream.this.doubleIterator();
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

    default public IntStream join(DoubleStream other, DoubleDoubleToIntFunction f) {
        return () -> new IntIterator() {
            private DoubleIterator it = DoubleStream.this.doubleIterator();
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

    default public LongStream join(DoubleStream other, DoubleDoubleToLongFunction f) {
        return () -> new LongIterator() {
            private DoubleIterator it = DoubleStream.this.doubleIterator();
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

    default public CharStream join(DoubleStream other, DoubleDoubleToCharFunction f) {
        return () -> new CharIterator() {
            private DoubleIterator it = DoubleStream.this.doubleIterator();
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

    default public DoubleStream join(IntStream other, DoubleIntToDoubleFunction f) {
        return () -> new DoubleIterator() {
            private DoubleIterator it = DoubleStream.this.doubleIterator();
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

    default public IntStream join(IntStream other, DoubleIntToIntFunction f) {
        return () -> new IntIterator() {
            private DoubleIterator it = DoubleStream.this.doubleIterator();
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

    default public LongStream join(IntStream other, DoubleIntToLongFunction f) {
        return () -> new LongIterator() {
            private DoubleIterator it = DoubleStream.this.doubleIterator();
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

    default public CharStream join(IntStream other, DoubleIntToCharFunction f) {
        return () -> new CharIterator() {
            private DoubleIterator it = DoubleStream.this.doubleIterator();
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

    default public DoubleStream join(LongStream other, DoubleLongToDoubleFunction f) {
        return () -> new DoubleIterator() {
            private DoubleIterator it = DoubleStream.this.doubleIterator();
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

    default public IntStream join(LongStream other, DoubleLongToIntFunction f) {
        return () -> new IntIterator() {
            private DoubleIterator it = DoubleStream.this.doubleIterator();
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

    default public LongStream join(LongStream other, DoubleLongToLongFunction f) {
        return () -> new LongIterator() {
            private DoubleIterator it = DoubleStream.this.doubleIterator();
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

    default public CharStream join(LongStream other, DoubleLongToCharFunction f) {
        return () -> new CharIterator() {
            private DoubleIterator it = DoubleStream.this.doubleIterator();
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

    default public DoubleStream join(CharStream other, DoubleCharToDoubleFunction f) {
        return () -> new DoubleIterator() {
            private DoubleIterator it = DoubleStream.this.doubleIterator();
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

    default public IntStream join(CharStream other, DoubleCharToIntFunction f) {
        return () -> new IntIterator() {
            private DoubleIterator it = DoubleStream.this.doubleIterator();
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

    default public LongStream join(CharStream other, DoubleCharToLongFunction f) {
        return () -> new LongIterator() {
            private DoubleIterator it = DoubleStream.this.doubleIterator();
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

    default public CharStream join(CharStream other, DoubleCharToCharFunction f) {
        return () -> new CharIterator() {
            private DoubleIterator it = DoubleStream.this.doubleIterator();
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