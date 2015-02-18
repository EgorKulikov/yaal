package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Towers {
	private static final long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		out.printLine("Case #" + testNumber + ":");
		long rowCount = in.readInt();
		long columnCount = in.readInt();
		long answer = rowCount * columnCount * 5;
		long at = rowCount * columnCount - 1;
		long rows = 1;
		long columns = 1;
		long ways = 1;
		long columnsPower = 2;
		long rowsPower = 2;
		while (rows < rowCount || columns < columnCount) {
			if (rows < columns && rows < rowCount) {
				answer += 2 * at;
				at -= columns;
				rows++;
				ways *= columnsPower;
				ways %= MOD;
				rowsPower *= 2;
				rowsPower %= MOD;
			} else if (rows == columns && columns < columnCount && rows < rowCount) {
				answer += 2 * at;
				at -= rows;
				columns++;
				ways *= rowsPower * 2;
				ways %= MOD;
				columnsPower *= 2;
				columnsPower %= MOD;
			} else if (rows < rowCount) {
				answer += 2 * at;
				at -= columns;
				rows++;
				ways *= columnsPower;
				ways %= MOD;
				rowsPower *= 2;
				rowsPower %= MOD;
			} else if (columns < columnCount) {
				answer += 2 * at;
				at -= rows;
				columns++;
				ways *= rowsPower;
				ways %= MOD;
				columnsPower *= 2;
				columnsPower %= MOD;
			}
		}
		out.printLine(answer, ways);
    }
}
