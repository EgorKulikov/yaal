package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		long[] numbers = IOUtils.readLongArray(in, count);
		long xor = 0;
		for (long i : numbers)
			xor ^= i;
		boolean[] answer = new boolean[count];
		int[] special = new int[60];
		long[] mask = new long[count];
		int step = 0;
		long secondXOR = 0;
		for (int i = 59; i >= 0; i--) {
			if ((xor >> i & 1) == 0) {
				for (int j = 0; j < count; j++) {
					if (mask[j] != -1 && (numbers[j] >> i & 1) == 1) {
						special[step] = j;
						if ((secondXOR >> i & 1) == 0) {
							for (int k = 0; k < step; k++) {
								if ((mask[j] >> k & 1) != 0)
									answer[special[k]] ^= true;
							}
							answer[j] = true;
							secondXOR ^= numbers[j];
						}
						for (int k = j + 1; k < count; k++) {
							if ((mask[k] != -1 && (numbers[k] >> i & 1) == 1)) {
								mask[k] ^= (1L << step) + mask[j];
								numbers[k] ^= numbers[j];
							}
						}
						mask[j] = -1;
						step++;
						break;
					}
				}
			}
		}
		for (int i = 59; i >= 0; i--) {
			if ((xor >> i & 1) == 1) {
				for (int j = 0; j < count; j++) {
					if (mask[j] != -1 && (numbers[j] >> i & 1) == 1) {
						special[step] = j;
						if ((secondXOR >> i & 1) == 0) {
							for (int k = 0; k < step; k++) {
								if ((mask[j] >> k & 1) != 0)
									answer[special[k]] ^= true;
							}
							answer[j] = true;
							secondXOR ^= numbers[j];
						}
						for (int k = j + 1; k < count; k++) {
							if ((mask[k] != -1 && (numbers[k] >> i & 1) == 1)) {
								mask[k] ^= (1L << step) + mask[j];
								numbers[k] ^= numbers[j];
							}
						}
						mask[j] = -1;
						step++;
						break;
					}
				}
			}
		}
		int[] result = new int[count];
		for (int i = 0; i < count; i++) {
			if (answer[i])
				result[i] = 2;
			else
				result[i] = 1;
		}
		out.printLine(result);
	}
}
