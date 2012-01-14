package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int pointCount = in.readInt();
		int length = in.readInt();
		int[] x = new int[pointCount];
		int[] y = new int[pointCount];
		IOUtils.readIntArrays(in, x, y);
		int[] rows = new int[800001];
		int[] columns = new int[800001];
		for (int i : x) {
			if (Math.abs(i) <= 400000)
				rows[i + 400000]++;
		}
		for (int i : y) {
			if (Math.abs(i) <= 400000)
				columns[i + 400000]++;
		}
		int up = 0;
		int down = 0;
		int left = 0;
		int right = 0;
		long answer = 0;
		for (int i : x) {
			if (i > 0)
				up++;
			else if (i < 0)
				down++;
			answer += Math.abs(i);
		}
		for (int i : y) {
			if (i > 0)
				right++;
			else if (i < 0)
				left++;
			answer += Math.abs(i);
		}
		char[] commands = IOUtils.readCharArray(in, length);
		int row = 400000;
		int column = 400000;
		for (char c : commands) {
			if (c == 'I') {
				down += rows[row];
				answer += down - up;
				row++;
				up -= rows[row];
			} else if (c == 'Z') {
				up += rows[row];
				row--;
				answer += up - down;
				down -= rows[row];
			} else if (c == 'S') {
				left += columns[column];
				answer += left - right;
				column++;
				right -= columns[column];
			} else {
				right += columns[column];
				answer += right - left;
				column--;
				left -= columns[column];
			}
			out.printLine(answer);
		}
	}
}
