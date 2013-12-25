package net.egork.numbers;

/**
 * @author egor@egork.net
 */
public abstract class NumberIterator {
	protected final long base;
	protected final long[] power;

	protected NumberIterator() {
		this(10);
	}

	protected NumberIterator(int base) {
		this.base = base;
		power = IntegerUtils.generatePowers(base, Long.MAX_VALUE);
	}

	protected abstract void process(long prefix, int remainingDigits);

	public void run(long from, long to) {
		if (from < 0 || from > to)
			throw new IllegalArgumentException();
		to++;
		for (int i = 0; ; i++) {
			if (i != power.length - 1 && from / power[i + 1] != to / power[i + 1]) {
				long prefix = from / power[i + 1] * base;
				for (int j = (int) (from / power[i] % base); j < base; j++)
					process(prefix + j, i);
				from /= power[i + 1];
				from++;
				from *= power[i + 1];
			} else {
				long upTo = to / power[i] % base;
				long prefix = from / power[i] / base * base;
				for (int j = (int) (from / power[i] % base); j < upTo; j++) {
					process(prefix + j, i);
				}
				for (int k = i - 1; k >= 0; k--) {
					upTo = to / power[k] % base;
					prefix = to / power[k + 1] * base;
					for (int j = 0; j < upTo; j++) {
						process(prefix + j, k);
					}
				}
				break;
			}
		}
	}
}
