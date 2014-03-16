package net.egork;

import net.egork.collections.intcollection.IntHashSet;
import net.egork.collections.intcollection.IntSet;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int queryCount = in.readInt();
		int n = 1000000;
		int[] direct = new int[n];
		for (int i = 1; i < n; i++) {
			direct[i] = direct[i - 1] + 1;
			if ((i & (i - 1)) == 0)
				direct[i]++;
		}
		int[][] reverse = new int[20][n];
		int current = 1;
		for (int i = 1; i < n; i++) {
			while (current <= direct[i] && current < n)
				reverse[0][current++] = i;
		}
		for (int i = 1; i < 20; i++) {
			for (int j = 0; j < n; j++)
				reverse[i][j] = reverse[i - 1][reverse[i - 1][j]];
		}
		int[] level = new int[queryCount];
		int[] left = new int[queryCount];
		int[] right = new int[queryCount];
		int[] value = new int[queryCount];
		boolean[] segment = new boolean[queryCount];
		for (int i = 0; i < queryCount; i++) {
			int type = in.readInt();
			if (type == 1) {
				level[i] = in.readInt();
				left[i] = in.readInt();
				right[i] = in.readInt();
				value[i] = in.readInt();
				segment[i] = true;
			} else {
				int cLevel = in.readInt();
				int position = in.readInt();
				IntSet set = new IntHashSet();
				for (int j = 0; j < i; j++) {
					if (segment[j] && level[j] >= cLevel) {
						int delta = level[j] - cLevel;
						int cLeft = left[j];
						int cRight = right[j];
						for (int k = 19; k >= 0; k--) {
							if (delta >= (1 << k)) {
								delta -= 1 << k;
								cLeft = reverse[k][cLeft];
								cRight = reverse[k][cRight];
							}
						}
						if (position >= cLeft && position <= cRight)
							set.add(value[j]);
					}
				}
				out.printLine(set.size());
			}
		}
    }
}
