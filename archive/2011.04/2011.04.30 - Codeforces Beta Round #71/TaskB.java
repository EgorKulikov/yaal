import net.egork.collections.intervaltree.SumIntervalTree;
import net.egork.io.IOUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;

public class TaskB implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		int badCount = in.readInt();
		int queryCount = in.readInt();
		SumIntervalTree tree = new SumIntervalTree(rowCount);
		tree.putSegment(0, rowCount, columnCount);
		int[] badRow = new int[badCount];
		int[] badColumn = new int[badCount];
		IOUtils.readIntArrays(in, badRow, badColumn);
		for (int i = 0; i < badCount; i++) {
			badRow[i]--;
			badColumn[i]--;
			tree.put(badRow[i], -1);
		}
		int[][] badInRow = new int[rowCount][];
		for (int i = 0; i < rowCount; i++)
			badInRow[i] = new int[(int) (columnCount - tree.get(i))];
		int[] index = new int[rowCount];
		for (int i = 0; i < badCount; i++)
			badInRow[badRow[i]][index[badRow[i]]++] = badColumn[i];
		for (int i = 0; i < rowCount; i++)
			Arrays.sort(badInRow[i]);
		for (int i = 0; i < queryCount; i++) {
			int row = in.readInt() - 1;
			int column = in.readInt() - 1;
			long total = tree.getSegment(0, row);
			int indexInRow = Arrays.binarySearch(badInRow[row], column);
			if (indexInRow >= 0)
				out.println("Waste");
			else {
				indexInRow = -indexInRow - 1;
				total += column - indexInRow;
				if (total % 3 == 0)
					out.println("Carrots");
				else if (total % 3 == 1)
					out.println("Kiwis");
				else
					out.println("Grapes");
			}
		}
	}
}

