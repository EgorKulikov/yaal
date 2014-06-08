package net.egork;

import net.egork.collections.comparators.IntComparator;
import net.egork.misc.ArrayUtils;

import java.util.Arrays;

public class AliceAndFriends {
    public double getMoney(int[] cards) {
		Arrays.sort(cards);
		int total = 0;
		int[] at = new int[cards.length * 6];
		int[] value = new int[cards.length * 6];
		int[] order = new int[cards.length * 6];
		for (int i = 0; i < cards.length; i++) {
			int size = 0;
			int base = 0;
			for (int k = 0; k < i; k++) {
				if (cards[k] == cards[i])
					break;
				if (cards[k] * 2 <= cards[i]) {
					at[size] = cards[i] - 2 * cards[k] + 1;
					value[size++] = -1;
					base += 2;
					at[size] = cards[i] - cards[k] + 1;
					value[size++] = -1;
				}
				if (cards[k] * 2 < cards[i]) {
					at[size] = cards[i] - 2 * cards[k];
					value[size++] = -1;
					base += 2;
					at[size] = cards[i] - cards[k] + 1;
					value[size++] = -1;
				}
				if (cards[i] < 2 * cards[k] && 2 * cards[k] - cards[i] < cards[i] - cards[k] + 1) {
					at[size] = 2 * cards[k] - cards[i];
					value[size++] = 1;
					at[size] = cards[i] - cards[k] + 1;
					value[size++] = -1;
				}
				if (cards[i] <= 2 * cards[k] && 2 * cards[k] - cards[i] + 1 < cards[i] - cards[k] + 1) {
					at[size] = 2 * cards[k] - cards[i] + 1;
					value[size++] = 1;
					at[size] = cards[i] - cards[k] + 1;
					value[size++] = -1;
				}
			}
			for (int k = i + 1; k < cards.length; k++) {
				if (cards[k] == cards[i])
					continue;
				base -= 4;
				if (2 * cards[i] >= cards[k]) {
					at[size] = cards[k] - cards[i];
					value[size++] = 2;
				}
			}
			int result = base;
			for (int k = 0; k < size; k++)
				order[k] = k;
			ArrayUtils.sort(order, 0, size, new IntComparator() {
				@Override
				public int compare(int first, int second) {
					if (at[first] != at[second])
						return at[first] - at[second];
					return value[first] - value[second];
				}
			});
			for (int k = 0; k < size; k++) {
				base += value[order[k]];
				result = Math.max(result, base);
			}
			total += result;
		}
		return (double)total / (4 * cards.length);
    }
}
