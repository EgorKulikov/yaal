package net.egork;

import net.egork.collections.comparators.IntComparator;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int length = in.readInt();
		int count = in.readInt();
		int[] at = IOUtils.readIntArray(in, count);
		ArrayUtils.sort(at, IntComparator.DEFAULT);
		double speed = in.readInt();
		int fromLeft = ArrayUtils.maxElement(IOUtils.readIntArray(in, in.readInt()));
		int fromRight = ArrayUtils.maxElement(IOUtils.readIntArray(in, in.readInt()));
		int[] all = new int[count + 2];
		System.arraycopy(at, 0, all, 1, count);
		all[count + 1] = length;
		at = all;
		count += 2;
		double answer = Double.POSITIVE_INFINITY;
		for (int i = 0; i < count; i++) {
			double time = Math.max(fromLeft + at[i] / speed, fromRight + (length - at[i]) / speed);
			answer = Math.min(answer, time + Math.max(length - at[i], at[i]) / speed);
		}
		out.printLine(answer);
    }
}
