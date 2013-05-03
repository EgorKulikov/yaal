package on2011_11.on2011_10_3.taskc;



import net.egork.misc.ArrayUtils;
import net.egork.collections.sequence.Array;
import net.egork.collections.sequence.ListUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		long index = in.readLong() - 1;
		int[][] priorities = IOUtils.readIntTable(in, rowCount, columnCount);
		int length = rowCount + columnCount - 1;
		int[] flatPriorities = new int[length];
		Arrays.fill(flatPriorities, Integer.MAX_VALUE);
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++)
				flatPriorities[i + j] = Math.min(flatPriorities[i + j], priorities[i][j]);
		}
		Integer[] order = ListUtils.order(Array.wrap(flatPriorities));
		char[] flatResult = new char[length];
		long[][] countForward = new long[length + 1][length + 1];
		long[][] countBackward = new long[length + 1][length + 1];
		for (int k : order) {
			ArrayUtils.fill(countForward, 0);
			countForward[0][0] = 1;
			for (int i = 1; i <= k; i++) {
				for (int j = 0; j <= k; j++) {
					countForward[i][j] = 0;
					if (j > 0 && flatResult[i - 1] != ')')
						countForward[i][j] += countForward[i - 1][j - 1];
					if (j < k && flatResult[i - 1] != '(')
						countForward[i][j] += countForward[i - 1][j + 1];
					if (countForward[i][j] > index)
						countForward[i][j] = index + 1;
				}
			}
			ArrayUtils.fill(countBackward, 0);
			countBackward[length][0] = 1;
			for (int i = length - 1; i > k; i--) {
				for (int j = 0; j <= length - k - 1; j++) {
					if (j > 0 && flatResult[i] != '(')
						countBackward[i][j] += countBackward[i + 1][j - 1];
					if (j < length - k - 1 && flatResult[i] != ')')
						countBackward[i][j] += countBackward[i + 1][j + 1];
					if (countBackward[i][j] > index)
						countBackward[i][j] = index + 1;
				}
			}
			long total = 0;
			for (int i = 1; i <= length - k && i - 1 <= k; i++) {
				long left = countForward[k][i - 1];
				long right = countBackward[k + 1][i];
				if (left != 0 && (index + 1) / left < right) {
					total = index + 1;
					break;
				}
				total += left * right;
				if (total > index) {
					total = index + 1;
					break;
				}
			}
			if (total > index)
				flatResult[k] = '(';
			else {
				flatResult[k] = ')';
				index -= total;
			}
		}
		char[][] result = new char[rowCount][columnCount];
		for (int i = 0; i < rowCount; i++) {
			System.arraycopy(flatResult, i, result[i], 0, columnCount);
		}
		for (char[] row : result)
			out.printLine(new String(row));
	}
}
