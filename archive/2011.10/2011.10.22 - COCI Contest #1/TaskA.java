package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int columnCount = in.readInt();
		int length = in.readInt();
		int appleCount = in.readInt();
		int[] apples = IOUtils.readIntArray(in, appleCount);
		int left = 1;
		int right = length;
		int result = 0;
		for (int position : apples) {
			if (position >= left && position <= right)
				continue;
			if (position > right) {
				result += position - right;
				right = position;
				left = right - length + 1;
			} else {
				result += left - position;
				left = position;
				right = left + length - 1;
			}
		}
		out.println(result);
	}
}
