package net.egork;

import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Comparator;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int studentCount = in.readInt();
		int capacity = in.readInt();
		int[] time = new int[studentCount];
		final int[] destination = new int[studentCount];
		IOUtils.readIntArrays(in, time, destination);
		long curTime = 0;
		long[] answer = new long[studentCount];
		for (int i = 0; i < studentCount; i += capacity) {
			int curCapacity = Math.min(capacity, studentCount - i);
			Integer[] order = new Integer[curCapacity];
			for (int j = 0; j < curCapacity; j++)
				order[j] = i + j;
			curTime = Math.max(curTime, time[i + curCapacity - 1]);
			Arrays.sort(order, new Comparator<Integer>() {
				public int compare(Integer o1, Integer o2) {
					return destination[o1] - destination[o2];
				}
			});
			int lastPosition = 0;
			int atCurStop = 0;
			long stopTime = -1;
			for (int j : order) {
				if (destination[j] != lastPosition) {
					curTime += destination[j] - lastPosition;
					stopTime = curTime;
					lastPosition = destination[j];
					atCurStop = 0;
				}
				answer[j] = stopTime;
				if (atCurStop == 0)
					curTime++;
				if (atCurStop % 2 == 1)
					curTime++;
				atCurStop++;
			}
			curTime += lastPosition;
		}
		out.printLine(Array.wrap(answer).toArray());
	}
}
