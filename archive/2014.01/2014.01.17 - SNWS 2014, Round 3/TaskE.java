package net.egork;

import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		long radius = in.readInt();
		int[] height = new int[count];
		double[] volume = new double[count];
		for (int i = 0; i < count; i++) {
			double length = in.readInt();
			double width = in.readInt();
			height[i] = in.readInt();
			volume[i] = length * width * height[i];
		}
		double totalVolume = ((4 * Math.PI / 3 * radius) * radius) * radius;
		int[] order = ArrayUtils.order(height);
		boolean start = true;
		boolean success = true;
		for (int i : order) {
			if (start) {
				if (radius * 2 <= height[i]) {
					success = false;
					break;
				}
			} else {
				double curHeight = Math.pow(totalVolume / 4 / Math.PI * 3, 1d / 3);
				if (curHeight * 2 <= height[i]) {
					success = false;
					break;
				}
			}
			start = false;
			totalVolume += volume[i];
		}
		if (success)
			out.printLine("Success");
		else
			out.printLine("Fail");
    }
}
