package net.egork.collections.intervaltree;

/**
 * @author Egor Kulikov (egorku@yandex-team.ru)
 */
public abstract class ArrayBasedLongIntervalTree extends LongIntervalTree {
    private long[] array;

    protected ArrayBasedLongIntervalTree(long[] array) {
        super(array.length);
        this.array = array;
    }

    @Override
    protected long initValue(int index) {
        return array[index];
    }
}
