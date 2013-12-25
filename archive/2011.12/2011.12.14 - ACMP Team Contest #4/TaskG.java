package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;

public class TaskG {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt() + 1;
		int columnCount = in.readInt() + 1;
		BigInteger answer = BigInteger.ZERO;
		for (int i = 0; i < 30; i++) {
			int period = 1 << (i + 1);
			if (rowCount <= period  / 2 && columnCount <= period / 2)
				break;
			long rowZero = (rowCount / period) * period / 2 + Math.min(rowCount % period, period / 2);
			long rowOne = rowCount - rowZero;
			long columnZero = (columnCount / period) * period / 2 + Math.min(columnCount % period, period / 2);
			long columnOne = columnCount - columnZero;
			answer = answer.add(BigInteger.valueOf(rowZero * columnOne + columnZero * rowOne).shiftLeft(i));
		}
		out.printLine(answer);
	}
}
