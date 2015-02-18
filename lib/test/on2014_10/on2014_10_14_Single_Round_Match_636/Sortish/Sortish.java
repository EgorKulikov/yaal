package on2014_10.on2014_10_14_Single_Round_Match_636.Sortish;



import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;

import java.util.Arrays;

public class Sortish {
	int[][] score;

    public long ways(int sortedness, int[] seq) {
		for (int i = 0; i < seq.length; i++) {
			if (seq[i] != 0) {
				seq[i] = seq.length + 1 - seq[i];
			}
		}
		int empty = ArrayUtils.count(seq, 0);
		int[] notPresent = new int[empty];
		int[] places = new int[empty];
		int id = 0;
		for (int i = 1; i <= seq.length; i++) {
			if (ArrayUtils.count(seq, i) == 0) {
				notPresent[id++] = i;
			}
		}
		id = 0;
		for (int i = 0; i < seq.length; i++) {
			if (seq[i] == 0) {
				places[id++] = i;
			}
		}
		score = new int[empty][empty];
		for (int i = 0; i < empty; i++) {
			for (int j = 0; j < empty; j++) {
				for (int k = 0; k < seq.length; k++) {
					if (seq[k] != 0) {
						score[i][j] += (seq[k] < notPresent[j] ^ k < places[i]) ? 1 : 0;
					}
				}
			}
		}
		for (int i = 0; i < seq.length; i++) {
			if (seq[i] == 0) {
				continue;
			}
			for (int j = i + 1; j < seq.length; j++) {
				if (seq[j] != 0 && seq[i] > seq[j]) {
					sortedness--;
				}
			}
		}
		int[] left = new int[(empty / 2) * seq.length + 1];
		int[] right = new int[((empty + 1) / 2) * seq.length + 1];
		long answer = 0;
		for (int i = 0; i < (1 << empty); i++) {
			if (Integer.bitCount(i) != (empty >> 1)) {
				continue;
			}
			int delta = 0;
			for (int j = 0; j < empty; j++) {
				for (int k = 0; k < j; k++) {
					if ((i >> j & 1) == 1 && (i >> k & 1) == 0) {
						delta++;
					}
				}
			}
			Arrays.fill(left, 0);
			Arrays.fill(right, 0);
			calculate(0, empty >> 1, i, left, 0);
			calculate(empty >> 1, empty, (1 << empty) - 1 - i, right, 0);
			for (int j = 0; j < left.length; j++) {
				int other = sortedness - j - delta;
				if (other >= 0 && other < right.length) {
					answer += (long) left[j] * right[other];
				}
			}
		}
		return answer;
    }

	private void calculate(int step, int last, int mask, int[] qty, int curScore) {
		if (step == last) {
			qty[curScore]++;
			return;
		}
		int remaining = mask;
		while (remaining != 0) {
			int bit = Integer.lowestOneBit(remaining);
			int id = Integer.bitCount(bit - 1);
			int nextScore = curScore + score[step][id] + Integer.bitCount((bit - 1) & mask);
			calculate(step + 1, last, mask - bit, qty, nextScore);
			remaining -= bit;
		}
	}
}
