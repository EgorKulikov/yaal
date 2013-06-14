package net.egork;

import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int skillCount = in.readInt();
		int[][] min = new int[count][skillCount];
		int[][] max = new int[count][skillCount];
		ArrayUtils.fill(max, 100);
		for (int i = 0; i < count; i++) {
			int conditionCount = in.readInt();
			for (int j = 0; j < conditionCount; j++) {
				int skill = in.readInt() - 1;
				boolean more = in.readCharacter() == '>';
				in.readCharacter();
				int threshold = in.readInt();
				if (more)
					min[i][skill] = Math.max(min[i][skill], threshold);
				else
					max[i][skill] = Math.min(max[i][skill], threshold);
			}
			for (int j = 0; j < skillCount; j++) {
				if (min[i][j] > max[i][j]) {
					out.printLine("No");
					return;
				}
			}
		}
		boolean[][] before = new boolean[count][count];
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				for (int k = 0; k < skillCount; k++) {
					if (max[i][k] < min[j][k]) {
						before[i][j] = true;
						break;
					}
				}
			}
		}
		boolean[] done = new boolean[count];
		for (int i = 0; i < count; i++) {
			boolean found = false;
			for (int j = 0; j < count; j++) {
				if (done[j])
					continue;
				boolean good = true;
				for (int k = 0; k < count; k++) {
					if (!done[k] && before[k][j]) {
						good = false;
						break;
					}
				}
				if (good) {
					done[j] = true;
					found = true;
					break;
				}
			}
			if (!found) {
				out.printLine("No");
				return;
			}
		}
		out.printLine("Yes");
    }
}
