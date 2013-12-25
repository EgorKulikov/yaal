package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskE {
	int[] min = new int[1 << 20];
	int[] max = new int[1 << 20];
	int count;
	int[][] quality;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		count = in.readInt();
		if (count == 0)
			throw new UnknownError();
		int[] skill = IOUtils.readIntArray(in, count);
		int[][] coWork = new int[count][count];
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++)
				coWork[i][j] = (int) Math.round(in.readDouble() * 100);
		}
		quality = new int[count][count];
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++)
				quality[i][j] = skill[i] * coWork[i][j] + skill[j] * coWork[j][i];
		}
		Arrays.fill(min, 0, 1 << count, -1);
		min[0] = max[0] = 0;
		int all = (1 << count) - 1;
		calculate(all, 0);
		out.printLine(min[all] / 1e2, max[all] / 1e2);
    }

	private void calculate(int mask, int first) {
		if (min[mask] != -1)
			return;
		min[mask] = Integer.MAX_VALUE;
		max[mask] = 0;
		while ((mask >> first & 1) == 0)
			first++;
		int nMask = mask - (1 << first);
		for (int i = first + 1; i < count; i++) {
			if ((mask >> i & 1) == 1) {
				int cMask = nMask - (1 << i);
				calculate(cMask, first + 1);
				min[mask] = Math.min(min[mask], min[cMask] + quality[first][i]);
				max[mask] = Math.max(max[mask], max[cMask] + quality[first][i]);
			}
		}
	}
}
