package net.egork.numbers;

/**
 * @author egor@egork.net
 */
public abstract class NumberIterator {
	protected long[] ten = IntegerUtils.generatePowers(10, 19, Long.MAX_VALUE);

	protected abstract void process(long prefix, int remainingDigits);

	public void process(long from, long to) {
		if (from < 0 || from > to)
			throw new IllegalArgumentException();
		to++;
		for (int i = 0; i <= 18; i++) {
			if (i != 18 && from / ten[i + 1] != to / ten[i + 1]) {
				long prefix = from / ten[i + 1] * 10;
				for (int j = (int) (from / ten[i] % 10); j < 10; j++)
					process(prefix + j, i);
				from /= ten[i + 1];
				from++;
				from *= ten[i + 1];
			} else {
				long upTo = to / ten[i] % 10;
				long prefix = from / ten[i] / 10 * 10;
				for (int j = (int) (from / ten[i] % 10); j < upTo; j++) {
					process(prefix + j, i);
				}
				for (int k = i - 1; k >= 0; k--) {
					upTo = to / ten[k] % 10;
					prefix = to / ten[k + 1] * 10;
					for (int j = 0; j < upTo; j++) {
						process(prefix + j, k);
					}
				}
				break;
			}
		}
	}
}
