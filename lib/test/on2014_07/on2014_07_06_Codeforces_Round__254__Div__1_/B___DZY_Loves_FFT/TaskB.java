package on2014_07.on2014_07_06_Codeforces_Round__254__Div__1_.B___DZY_Loves_FFT;



import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskB {
	long x;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int ones = in.readInt();
		x = in.readLong();
		int[] a = new int[count];
		for (int i = 0; i < count; i++) {
			a[i] = i + 1;
		}
		shuffle(count, a);
		int[] b = new int[count];
		Arrays.fill(b, 0, ones, 1);
		shuffle(count, b);
		int[] answer = new int[count];
		int size = (count + 31) >> 5;
		int[] ready = new int[size];
		int[] mask = new int[size];
		for (int i = 0; i < size; i++) {
			for (int j = i * 32; j < (i + 1) * 32 && j < count; j++) {
				if (b[j] == 1) {
					mask[i] += 1 << (j - i * 32);
				}
			}
		}
		int[][] upMasks = new int[32][size];
		int[][] downMasks = new int[32][size];
		for (int i = 0; i < 32; i++) {
			int filter;
			if (i == 0) {
				filter = -1;
			} else {
				filter = (1 << (32 - i)) - 1;
			}
			int otherShift = 32 - i;
			for (int j = 0; j < size; j++) {
				upMasks[i][j] = mask[j] & filter;
				downMasks[i][j] = mask[j] >> otherShift;
			}
		}
		int[] order = ArrayUtils.order(a);
		ArrayUtils.reverse(order);
		for (int i : order) {
			int value = a[i];
			int shift = i & 31;
			int otherShift = 32 - shift;
			int filter;
			if (shift == 0) {
				filter = -1;
			} else {
				filter = (1 << (32 - shift)) - 1;
			}
			int start = i >> 5;
			int upTo = size - start - 1;
			int otherFilter = (1 << shift) - 1;
			if (otherFilter == -1) {
				otherFilter = 0;
			}
			for (int j = 0; j < upTo; j++) {
				if ((ready[j + start] >> shift & upMasks[shift][j]) != upMasks[shift][j]) {
					for (int k = (j + start) * 32 + shift; k < (j + start + 1) * 32 && k < count; k++) {
						if (b[k - i] == 1 && answer[k] == 0) {
							answer[k] = value;
							ready[k >> 5] += 1 << (k & 31);
						}
					}
				}
				if ((ready[j + start + 1] & downMasks[shift][j]) != downMasks[shift][j]) {
					for (int k = (j + start + 1) * 32; k < (j + start + 1) * 32 + shift && k < count; k++) {
						if (b[k - i] == 1 && answer[k] == 0) {
							answer[k] = value;
							ready[k >> 5] += 1 << (k & 31);
						}
					}
				}
			}
			if ((ready[upTo + start] >> shift & upMasks[shift][upTo]) != upMasks[shift][upTo]) {
				for (int k = (upTo + start) * 32 + shift; k < (upTo + start + 1) * 32 && k < count; k++) {
					if (b[k - i] == 1 && answer[k] == 0) {
						answer[k] = value;
						ready[k >> 5] += 1 << (k & 31);
					}
				}
			}
		}
		out.printLine(answer);
	}

	protected void shuffle(int count, int[] a) {
		for (int i = 0; i < count; i++) {
			int index = next() % (i + 1);
			int temp = a[i];
			a[i] = a[index];
			a[index] = temp;
		}
	}

	private int next() {
		x = (x * 37 + 10007) % 1000000007;
		return (int) x;
	}
}
