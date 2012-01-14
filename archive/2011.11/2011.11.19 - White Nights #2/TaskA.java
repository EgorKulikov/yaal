package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.collections.sequence.Array;
import net.egork.collections.sequence.ListUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Random;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		if (count == 1) {
			out.printLine(1);
			return;
		}
		int[] x = new int[count];
		int[] y = new int[count];
		IOUtils.readIntArrays(in, x, y);
		long deltaX0 = x[1] - x[0];
		long deltaY0 = y[1] - y[0];
		boolean sameLine = true;
		for (int i = 2; i < count; i++) {
			long deltaX = x[i] - x[0];
			long deltaY = y[i] - y[0];
			if (deltaX * deltaY0 != deltaY * deltaX0) {
				sameLine = false;
				break;
			}
		}
		if (sameLine) {
			out.printLine("No solution");
			return;
		}
		double cx = (double)ArrayUtils.sumArray(x) / count;
		double cy = (double)ArrayUtils.sumArray(y) / count;
		Random rand = new Random(239);
		cx += rand.nextDouble() * 2 / 3 - 1. / 3;
		cy += rand.nextDouble() * 2 / 3 - 1. / 3;
		double[] angle = new double[count];
		for (int i = 0; i < count; i++)
			angle[i] = Math.atan2(y[i] - cy, x[i] - cx);
		Integer[] order = ListUtils.order(Array.wrap(angle));
		for (int i = 0; i < count; i++)
			out.printLine(order[i] + 1);
	}
}
