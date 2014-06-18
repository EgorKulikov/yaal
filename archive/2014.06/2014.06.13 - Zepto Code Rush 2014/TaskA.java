package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int initialHeight = in.readInt();
		int[] type = new int[count];
		int[] height = new int[count];
		int[] mass = new int[count];
		IOUtils.readIntArrays(in, type, height, mass);
		int answer = 0;
		for (int i = 0; i < 2; i++) {
			int curType = i;
			int current = initialHeight;
			int result = 0;
			boolean[] used = new boolean[count];
			for (int j = 0; j < count; j++) {
				int maxAt = -1;
				for (int k = 0; k < count; k++) {
					if (type[k] == curType && !used[k] && height[k] <= current && (maxAt == -1 || mass[maxAt] < mass[k]))
						maxAt = k;
				}
				if (maxAt == -1)
					break;
				result++;
				used[maxAt] = true;
				current += mass[maxAt];
				curType = 1 - curType;
			}
			answer = Math.max(answer, result);
		}
		out.printLine(answer);
    }
}
