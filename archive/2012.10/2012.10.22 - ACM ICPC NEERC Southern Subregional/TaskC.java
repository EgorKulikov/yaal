package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskC {
	private static final long MOD = (long) (1e9 + 9);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int gap = in.readInt();
		if (count % 2 != gap % 2) {
			out.printLine(0);
			return;
		}
		int[] firstSkill = IOUtils.readIntArray(in, count);
		int[] secondSkill = IOUtils.readIntArray(in, count);
		Arrays.sort(firstSkill);
		Arrays.sort(secondSkill);
		long[][] current = new long[count + 1][count + 1];
		int firstProcessed = 0;
		int secondProcessed = 0;
		current[0][0] = 1;
		while (firstProcessed < count || secondProcessed < count) {
			if (secondProcessed == count || firstProcessed < count && firstSkill[firstProcessed] < secondSkill[secondProcessed]) {
				for (int i = count; i >= 0; i--) {
					for (int j = count; j >= 0; j--) {
						if (current[i][j] != 0 && secondProcessed > i + j)
							current[i + 1][j] = (current[i + 1][j] + current[i][j] * (secondProcessed - i - j)) % MOD;
					}
				}
				firstProcessed++;
			} else {
				for (int i = count; i >= 0; i--) {
					for (int j = count; j >= 0; j--) {
						if (current[i][j] != 0 && firstProcessed > i + j)
							current[i][j + 1] = (current[i][j + 1] + current[i][j] * (firstProcessed - i - j)) % MOD;
					}
				}
				secondProcessed++;
			}
		}
		out.printLine(current[(count + gap) / 2][(count - gap) / 2]);
	}
}
