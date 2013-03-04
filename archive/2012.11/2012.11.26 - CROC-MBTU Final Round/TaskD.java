package net.egork;

import net.egork.geometry.GeometryUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int leftCount = in.readInt();
		int rightCount = in.readInt();
		int leftX = in.readInt();
		int rightX = in.readInt();
		int[] leftY = IOUtils.readIntArray(in, leftCount);
		int[] rightY = IOUtils.readIntArray(in, rightCount);
		int[] rightLength = IOUtils.readIntArray(in, rightCount);
		double answer = Double.POSITIVE_INFINITY;
		int leftIndex = -1;
		int rightIndex = -1;
		int j = 0;
		for (int i = 0; i < rightCount; i++) {
			double value = GeometryUtils.fastHypot(leftX, leftY[j]) + GeometryUtils.fastHypot(leftX - rightX, leftY[j] - rightY[i]) + rightLength[i];
			while (j + 1 < leftCount) {
				double next = GeometryUtils.fastHypot(leftX, leftY[j + 1]) + GeometryUtils.fastHypot(leftX - rightX, leftY[j + 1] - rightY[i]) + rightLength[i];
				if (next >= value)
					break;
				value = next;
				j++;
			}
			if (value < answer) {
				answer = value;
				leftIndex = j + 1;
				rightIndex = i + 1;
			}
		}
		out.printLine(leftIndex, rightIndex);
	}
}
