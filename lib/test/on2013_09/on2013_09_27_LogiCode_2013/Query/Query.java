package on2013_09.on2013_09_27_LogiCode_2013.Query;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Query {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] score = IOUtils.readIntArray(in, count);
		int friendCount = in.readInt();
		int[] task = IOUtils.readIntArray(in, friendCount);
		MiscUtils.decreaseByOne(task);
		task = Arrays.copyOf(task, 2 * friendCount);
		System.arraycopy(task, 0, task, friendCount, friendCount);
		friendCount *= 2;
		int[] next = new int[friendCount];
		int[] last = new int[friendCount];
		int[] nextByTask = new int[count];
		Arrays.fill(nextByTask, friendCount);
		for (int i = friendCount - 1; i >= 0; i--) {
			next[i] = nextByTask[task[i]];
			nextByTask[task[i]] = i;
		}
		int[] lastByTask = new int[count];
		Arrays.fill(lastByTask, -1);
		for (int i = 0; i < friendCount; i++) {
			last[i] = lastByTask[task[i]];
			lastByTask[task[i]] = i;
		}
		int maxScore = (int) ArrayUtils.sumArray(score);
		int step = (int)Math.round(Math.sqrt(friendCount) * 4);
		int wholeParts = friendCount / step;
		int[][] qty = new int[wholeParts][wholeParts];
		for (int i = 0; i < wholeParts; i++) {
			int current = 0;
			int start = i * step;
			for (int j = i; j < wholeParts; j++) {
				int end = (j + 1) * step;
				if (current != maxScore) {
					for (int k = j * step; k < end; k++) {
						if (last[k] < start)
							current += score[task[k]];
					}
				}
				qty[i][j] = current;
			}
		}
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			int from = in.readInt() - 1;
			int length = in.readInt();
			int end = from + length - 1;
			int startPart = from / step;
			int endPart = end / step;
			if (endPart - startPart <= 1) {
				int result = 0;
				for (int j = from; j <= end; j++) {
					if (next[j] > end)
						result += score[task[j]];
				}
				out.printLine(result);
			} else {
				int result = qty[startPart + 1][endPart - 1];
				int up = endPart * step;
				int to = (startPart + 1) * step;
				for (int j = from; j < to; j++) {
					if (next[j] >= up)
						result += score[task[j]];
				}
				for (int j = endPart * step; j <= end; j++) {
					if (last[j] < from)
						result += score[task[j]];
				}
				out.printLine(result);
			}
		}
    }
}
