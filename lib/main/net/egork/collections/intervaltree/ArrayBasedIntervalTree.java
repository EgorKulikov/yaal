package net.egork.collections.intervaltree;

/**
 * @author Egor Kulikov (egorku@yandex-team.ru)
 */
public abstract class ArrayBasedIntervalTree extends LongIntervalTree {
    private long[] array;

    protected ArrayBasedIntervalTree(long[] array) {
        super(array.length, false);
		this.array = array;
		init();
    }

    @Override
    protected long initValue(int index) {
        return array[index];
    }
}
