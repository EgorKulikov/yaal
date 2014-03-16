package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int segmentCount = in.readInt();
		int[][] values = IOUtils.readIntTable(in, count, segmentCount + 1);
		double[] answer = new double[count];
		double[] start = new double[count + 1];
		int[] index = new int[count];
		double[] nextStart = new double[count + 1];
		int[] nextIndex = new int[count];
		int[] a = new int[count];
		int[] b = new int[count];
		for (int i = 0; i < segmentCount; i++) {
			answer[0] += (values[0][i] + values[0][i + 1]) / 2d;
			start[0] = 0;
			start[1] = 1;
			index[0] = 0;
			a[0] = values[0][i + 1] - values[0][i];
			b[0] = values[0][i];
			int length = 1;
			for (int j = 1; j < count; j++) {
				a[j] = values[j][i + 1] - values[j][i];
				b[j] = values[j][i];
				boolean bad = false;
				for (int k = 0; k < j; k++) {
					if (a[j] == a[k] && b[j] == b[k]) {
						bad = true;
						break;
					}
				}
				if (bad)
					continue;
				int newLength = 0;
				for (int k = 0; k < length; k++) {
					double oldStart = a[index[k]] * start[k] + b[index[k]];
					double newStart = a[j] * start[k] + b[j];
					double oldEnd = a[index[k]] * start[k + 1] + b[index[k]];
					double newEnd = a[j] * start[k + 1] + b[j];
					if (oldStart >= newStart && oldEnd >= newEnd) {
						nextIndex[newLength] = index[k];
						nextStart[newLength++] = start[k];
						continue;
					}
					if (oldStart <= newStart && oldEnd <= newEnd) {
						if (newLength == 0 || nextIndex[newLength - 1] != j) {
							nextIndex[newLength] = j;
							nextStart[newLength++] = start[k];
						}
						answer[j] += (newStart - oldStart + newEnd - oldEnd) / 2 * (start[k + 1] - start[k]);
						continue;
					}
					double at = (start[k] * Math.abs(oldEnd - newEnd) + start[k + 1] * Math.abs(oldStart - newStart)) / (Math.abs(oldEnd - newEnd) + Math.abs(oldStart - newStart));
					if (oldStart >= newStart && oldEnd <= newEnd) {
						nextIndex[newLength] = index[k];
						nextStart[newLength++] = start[k];
						nextIndex[newLength] = j;
						nextStart[newLength++] = at;
						answer[j] += (newEnd - oldEnd) / 2 * (start[k + 1] - at);
						continue;
					}
					if (newLength == 0 || nextIndex[newLength - 1] != j) {
						nextIndex[newLength] = j;
						nextStart[newLength++] = start[k];
					}
					nextIndex[newLength] = index[k];
					nextStart[newLength++] = at;
					answer[j] += (newStart - oldStart) / 2 * (at - start[k]);
				}
				nextStart[newLength] = 1;
				double[] t1 = start;
				start = nextStart;
				nextStart = t1;
				int[] t2 = index;
				index = nextIndex;
				nextIndex = t2;
				length = newLength;
			}
		}
		for (double d : answer)
			out.printLine(d);
    }
}
