package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE1 {
	int[] array;
	long[][][] answerStarted;
	long[][][] answerNotStarted;
	int count;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		count = in.readInt();
		int partCount = in.readInt();
		array = IOUtils.readIntArray(in, count);
		answerStarted = new long[5][partCount + 1][count + 1];
		answerNotStarted = new long[5][partCount + 1][count + 1];
		ArrayUtils.fill(answerStarted, Long.MIN_VALUE);
		ArrayUtils.fill(answerNotStarted, Long.MIN_VALUE);
		out.printLine(Math.max(goNotStarted(-1, partCount, 0), goNotStarted(1, partCount, 0)));
    }

	private long goNotStarted(int type, int remainingParts, int at) {
		if (answerNotStarted[type + 2][remainingParts][at] != Long.MIN_VALUE) {
			return answerNotStarted[type + 2][remainingParts][at];
		}
		if (remainingParts == 0) {
			return answerNotStarted[type + 2][remainingParts][at] = 0;
		}
		if (at == count) {
			return answerNotStarted[type + 2][remainingParts][at] = Long.MIN_VALUE / 2;
		}
		long result = getStarted(type, remainingParts, at + 1) + type * array[at];
		result = Math.max(result, goNotStarted(type, remainingParts, at + 1));
		if (Math.abs(type) != 1) {
			result = Math.max(result, goNotStarted(convertType(type, remainingParts - 1), remainingParts - 1, at + 1));
		}
		return answerNotStarted[type + 2][remainingParts][at] = result;
	}

	private long getStarted(int type, int remainingParts, int at) {
		if (answerStarted[type + 2][remainingParts][at] != Long.MIN_VALUE) {
			return answerStarted[type + 2][remainingParts][at];
		}
		long result = goNotStarted(convertType(-type, remainingParts - 1), remainingParts - 1, at);
		if (at != count) {
			result = Math.max(result, getStarted(type, remainingParts, at + 1) + type * array[at]);
		}
		return answerStarted[type + 2][remainingParts][at] = result;
	}

	private int convertType(int type, int partsRemaining) {
		type /= Math.abs(type);
		if (partsRemaining == 1)  {
			return type;
		} else {
			return 2 * type;
		}
	}
}
