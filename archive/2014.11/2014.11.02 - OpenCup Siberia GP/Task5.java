package net.egork;

import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Task5 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int[] dimensions = IOUtils.readIntArray(in, 3);
		Arrays.sort(dimensions);
		int count = in.readInt();
		int[] width = new int[count];
		int[] height = new int[count];
		int[][] destinations = new int[count][];
		for (int i = 0; i < count; i++) {
			width[i] = in.readInt();
			height[i] = in.readInt();
			destinations[i] = IOUtils.readIntArray(in, in.readInt());
		}
		MiscUtils.decreaseByOne(destinations);
		int[] answer = new int[3];
		int maxVolume = 0;
		int[] maxHeight = new int[count];
		boolean[] processed = new boolean[count];
		for (int i = width[0]; i >= 1; i--) {
			Arrays.fill(maxHeight, 0);
			Arrays.fill(processed, false);
			maxHeight[0] = height[0];
			for (int j = 0; j < count; j++) {
				int at = -1;
				for (int k = 0; k < count; k++) {
					if (!processed[k] && (at == -1 || maxHeight[k] > maxHeight[at])) {
						at = k;
					}
				}
				if (at == -1) {
					break;
				}
				processed[at] = true;
				for (int k : destinations[at]) {
					if (width[k] >= i) {
						maxHeight[k] = Math.max(maxHeight[k], Math.min(height[k], maxHeight[at]));
					}
				}
			}
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					if (j == k) {
						continue;
					}
					int other = 3 - j - k;
					int current = Math.min(i, dimensions[j]) * Math.min(maxHeight[count - 1], dimensions[k]) * dimensions[other];
					if (current > maxVolume) {
						maxVolume = current;
						answer[0] = Math.min(maxHeight[count - 1], dimensions[k]);
						answer[1] = Math.min(i, dimensions[j]);
						answer[2] = dimensions[other];
					}
				}
			}
		}
		out.printLine(answer);
	}
}
