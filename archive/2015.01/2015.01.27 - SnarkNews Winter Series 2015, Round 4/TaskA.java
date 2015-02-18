package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] score = IOUtils.readIntArray(in, count);
		long answer = Math.max((ArrayUtils.sumArray(score) + 1) / 2, ArrayUtils.maxElement(score));
		out.printLine(answer);
    }
}
