package on2015_03.on2015_03_18_Code_Mania_2015.Alice_and_Bob_revisited;


import net.egork.generated.collections.set.IntHashSet;
import net.egork.generated.collections.set.IntSet;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class AliceAndBobRevisited {
	int[][] result = new int[21][21];

	{
		ArrayUtils.fill(result, -1);
	}

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		if (solve(rowCount, columnCount) == 0) {
			out.printLine("Bob");
		} else {
			out.printLine("Alice");
		}
    }

	private int solve(int rowCount, int columnCount) {
		if (result[rowCount][columnCount] != -1) {
			return result[rowCount][columnCount];
		}
		if (rowCount == 0 || columnCount == 0) {
			return result[rowCount][columnCount] = 0;
		}
		IntSet possible = new IntHashSet();
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				possible.add(solve(i, j) ^ solve(i, columnCount - j - 1) ^ solve(rowCount - i - 1, j) ^
					solve(rowCount - i - 1, columnCount - j - 1));
			}
		}
		for (int i = 0; ; i++) {
			if (!possible.contains(i)) {
				return result[rowCount][columnCount] = i;
			}
		}
	}
}
