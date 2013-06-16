package on2013_06.on2013_06_16_Russian_CodeCup_2013_Online_Round.C_________;



import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		int[] rowOdd = createOdd(rowCount);
		int[] columnOdd = createOdd(columnCount);
		long[][] answer = new long[rowOdd.length][columnOdd.length];
		ArrayUtils.fill(answer, Long.MAX_VALUE);
		answer[0][0] = 0;
		for (int i = 0; i < rowOdd.length; i++) {
			for (int j = 0; j < columnOdd.length; j++) {
				if (i + 1 < rowOdd.length)
					answer[i + 1][j] = Math.min(answer[i + 1][j], answer[i][j] + columnOdd[j]);
				if (j + 1 < columnOdd.length)
					answer[i][j + 1] = Math.min(answer[i][j + 1], answer[i][j] + rowOdd[i]);
			}
		}
		out.printLine(answer[rowOdd.length - 1][columnOdd.length - 1]);
    }

	private int[] createOdd(int count) {
		IntList result = new IntArrayList();
		while (count != 0) {
			if ((count & 1) == 1)
				result.add(count);
			count >>= 1;
		}
		return result.toArray();
	}
}
