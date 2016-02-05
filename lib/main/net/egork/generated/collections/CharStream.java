package net.egork.generated.collections;

import net.egork.generated.collections.comparator.CharComparator;
import net.egork.generated.collections.function.CharCharToCharFunction;
import net.egork.generated.collections.function.CharCharToDoubleFunction;
import net.egork.generated.collections.function.CharCharToIntFunction;
import net.egork.generated.collections.function.CharCharToLongFunction;
import net.egork.generated.collections.function.CharDoubleToCharFunction;
import net.egork.generated.collections.function.CharDoubleToDoubleFunction;
import net.egork.generated.collections.function.CharDoubleToIntFunction;
import net.egork.generated.collections.function.CharDoubleToLongFunction;
import net.egork.generated.collections.function.CharFilter;
import net.egork.generated.collections.function.CharIntToCharFunction;
import net.egork.generated.collections.function.CharIntToDoubleFunction;
import net.egork.generated.collections.function.CharIntToIntFunction;
import net.egork.generated.collections.function.CharIntToLongFunction;
import net.egork.generated.collections.function.CharLongToCharFunction;
import net.egork.generated.collections.function.CharLongToDoubleFunction;
import net.egork.generated.collections.function.CharLongToIntFunction;
import net.egork.generated.collections.function.CharLongToLongFunction;
import net.egork.generated.collections.function.CharTask;
import net.egork.generated.collections.function.CharToCharFunction;
import net.egork.generated.collections.function.CharToDoubleFunction;
import net.egork.generated.collections.function.CharToIntFunction;
import net.egork.generated.collections.function.CharToLongFunction;
import net.egork.generated.collections.function.DoubleCharToDoubleFunction;
import net.egork.generated.collections.function.IntCharToIntFunction;
import net.egork.generated.collections.function.LongCharToLongFunction;
import net.egork.generated.collections.iterator.CharIterator;
import net.egork.generated.collections.iterator.DoubleIterator;
import net.egork.generated.collections.iterator.IntIterator;
import net.egork.generated.collections.iterator.LongIterator;
import net.egork.generated.collections.list.CharArrayList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public interface CharStream extends Iterable<Character>, Comparable<CharStream> {
    //abstract
    public CharIterator charIterator();

    //base
    default public Iterator<Character> iterator() {
        return new Iterator<Character>() {
            private CharIterator it = charIterator();

            public boolean hasNext() {
                return it.isValid();
            }

            public Character next() {
                char result = it.value();
                it.advance();
                return result;
            }
        };
    }

    default public char first() {
        return charIterator().value();
    }

    default public CharCollection compute() {
        return new CharArrayList(this);
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

    //algorithms
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

    default public int[] qty(int bound) {
        int[] result = new int[bound];
        for (CharIterator it = charIterator(); it.isValid(); it.advance()) {
            result[(int) it.value()]++;
        }
        return result;
    }

    default public int[] qty() {
        return qty((int) (max() + 1));
    }

    default public boolean allOf(CharFilter f) {
        for (CharIterator it = charIterator(); it.isValid(); it.advance()) {
            if (!f.accept(it.value())) {
                return false;
            }
        }
        return true;
    }

    default public boolean anyOf(CharFilter f) {
        for (CharIterator it = charIterator(); it.isValid(); it.advance()) {
            if (f.accept(it.value())) {
                return true;
            }
        }
        return false;
    }

    default public boolean noneOf(CharFilter f) {
        for (CharIterator it = charIterator(); it.isValid(); it.advance()) {
            if (f.accept(it.value())) {
                return false;
            }
        }
        return true;
    }

    default public char reduce(CharCharToCharFunction f) {
        CharIterator it = charIterator();
        if (!it.isValid()) {
            return Character.MAX_VALUE;
        }
        char result = it.value();
        while (it.advance()) {
            result = f.value(result, it.value());
        }
        return result;
    }

    default public double reduce(double initial, DoubleCharToDoubleFunction f) {
        for (CharIterator it = charIterator(); it.isValid(); it.advance()) {
            initial = f.value(initial, it.value());
        }
        return initial;
    }

    default public int reduce(int initial, IntCharToIntFunction f) {
        for (CharIterator it = charIterator(); it.isValid(); it.advance()) {
            initial = f.value(initial, it.value());
        }
        return initial;
    }

    default public long reduce(long initial, LongCharToLongFunction f) {
        for (CharIterator it = charIterator(); it.isValid(); it.advance()) {
            initial = f.value(initial, it.value());
        }
        return initial;
    }

    default public char reduce(char initial, CharCharToCharFunction f) {
        for (CharIterator it = charIterator(); it.isValid(); it.advance()) {
            initial = f.value(initial, it.value());
        }
        return initial;
    }

    //views
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

    default public DoubleStream join(DoubleStream other, CharDoubleToDoubleFunction f) {
        return () -> new DoubleIterator() {
            private CharIterator it = CharStream.this.charIterator();
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

    default public IntStream join(DoubleStream other, CharDoubleToIntFunction f) {
        return () -> new IntIterator() {
            private CharIterator it = CharStream.this.charIterator();
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

    default public LongStream join(DoubleStream other, CharDoubleToLongFunction f) {
        return () -> new LongIterator() {
            private CharIterator it = CharStream.this.charIterator();
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

    default public CharStream join(DoubleStream other, CharDoubleToCharFunction f) {
        return () -> new CharIterator() {
            private CharIterator it = CharStream.this.charIterator();
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

    default public DoubleStream join(IntStream other, CharIntToDoubleFunction f) {
        return () -> new DoubleIterator() {
            private CharIterator it = CharStream.this.charIterator();
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

    default public IntStream join(IntStream other, CharIntToIntFunction f) {
        return () -> new IntIterator() {
            private CharIterator it = CharStream.this.charIterator();
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

    default public LongStream join(IntStream other, CharIntToLongFunction f) {
        return () -> new LongIterator() {
            private CharIterator it = CharStream.this.charIterator();
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

    default public CharStream join(IntStream other, CharIntToCharFunction f) {
        return () -> new CharIterator() {
            private CharIterator it = CharStream.this.charIterator();
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

    default public DoubleStream join(LongStream other, CharLongToDoubleFunction f) {
        return () -> new DoubleIterator() {
            private CharIterator it = CharStream.this.charIterator();
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

    default public IntStream join(LongStream other, CharLongToIntFunction f) {
        return () -> new IntIterator() {
            private CharIterator it = CharStream.this.charIterator();
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

    default public LongStream join(LongStream other, CharLongToLongFunction f) {
        return () -> new LongIterator() {
            private CharIterator it = CharStream.this.charIterator();
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

    default public CharStream join(LongStream other, CharLongToCharFunction f) {
        return () -> new CharIterator() {
            private CharIterator it = CharStream.this.charIterator();
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

    default public DoubleStream join(CharStream other, CharCharToDoubleFunction f) {
        return () -> new DoubleIterator() {
            private CharIterator it = CharStream.this.charIterator();
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

    default public IntStream join(CharStream other, CharCharToIntFunction f) {
        return () -> new IntIterator() {
            private CharIterator it = CharStream.this.charIterator();
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

    default public LongStream join(CharStream other, CharCharToLongFunction f) {
        return () -> new LongIterator() {
            private CharIterator it = CharStream.this.charIterator();
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

    default public CharStream join(CharStream other, CharCharToCharFunction f) {
        return () -> new CharIterator() {
            private CharIterator it = CharStream.this.charIterator();
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