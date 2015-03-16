package net.egork.collections.intervaltree;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class SumIntervalTree extends LongIntervalTree {
	private final long[] array;

    public SumIntervalTree(int size) {
        super(size);
		array = null;
    }

	public SumIntervalTree(long[] array) {
    	super(array.length, false);
		this.array = array;
		init();
 	}

	@Override
	protected long initValue(int index) {
		if (array == null) {
			return 0;
		}
		return array[index];
	}

	@Override
    protected long joinValue(long left, long right) {
        return left + right;
    }

    @Override
    protected long joinDelta(long was, long delta) {
        return was + delta;
    }

    @Override
    protected long accumulate(long value, long delta, int length) {
        return value + delta * length;
    }

    @Override
    protected long neutralValue() {
        return 0;
    }

    @Override
    protected long neutralDelta() {
        return 0;
    }
}
