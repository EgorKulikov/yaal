package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int side = in.readInt();
		int row = in.readInt() - 1;
		int column = in.readInt() - 1;
		int required = in.readInt();
		long left = 0;
		long right = 2 * side - 2;
		while (left < right) {
			long current = (left + right) / 2;
			long result = calculate(row, column, current) + calculate(column, side - row - 1, current) +
				calculate(side - row - 1, side - column - 1, current) + calculate(side - column - 1, row, current) + 1;
			if (result >= required)
				right = current;
			else
				left = current + 1;
		}
		out.printLine(left);
	}

	private long calculate(int row, int column, long current) {
		column++;
		long total = 0;
		long mn = Math.min(row, column);
		long mx = Math.max(row, column);
		if (current <= mn)
			return current * (current + 1) / 2;
		total += mn * (mn + 1) / 2;
		current -= mn;
		mx -= mn;
		if (current <= mx)
			return total + mn * current;
		total += mn * mx;
		current -= mx;
		if (current < mn)
			return total + (mn - 1) * mn / 2 - (mn - current - 1) * (mn - current) / 2;
		return total + (mn - 1) * mn / 2;
	}
}
