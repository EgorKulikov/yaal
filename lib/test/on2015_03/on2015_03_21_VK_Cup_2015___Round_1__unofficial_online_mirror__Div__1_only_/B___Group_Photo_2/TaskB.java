package on2015_03.on2015_03_21_VK_Cup_2015___Round_1__unofficial_online_mirror__Div__1_only_.B___Group_Photo_2;


import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] width = new int[count];
		int[] height = new int[count];
		IOUtils.readIntArrays(in, width, height);
		int[] all = Arrays.copyOf(width, 2 * count);
		System.arraycopy(height, 0, all, count, count);
		Arrays.sort(all);
		long answer = Long.MAX_VALUE;
		for (int i : all) {
			long sum = 0;
			IntList rotatedDelta = new IntArrayList();
			int numRotated = 0;
			boolean good = true;
			for (int j = 0; j < count; j++) {
				if (height[j] > width[j]) {
					if (height[j] <= i) {
						sum += width[j];
					} else {
						if (width[j] <= i) {
							sum += height[j];
							numRotated++;
						} else {
							good = false;
							break;
						}
					}
				} else {
					if (width[j] <= i) {
						sum += height[j];
						numRotated++;
						rotatedDelta.add(width[j] - height[j]);
					} else {
						if (height[j] <= i) {
							sum += width[j];
						} else {
							good = false;
							break;
						}
					}
				}
			}
			if (!good || numRotated - rotatedDelta.size() > count / 2) {
				continue;
			}
			rotatedDelta.sort();
			for (int j = 0; j < numRotated - count / 2; j++) {
				sum += rotatedDelta.get(j);
			}
			answer = Math.min(answer, sum * i);
		}
		out.printLine(answer);
	}
}
