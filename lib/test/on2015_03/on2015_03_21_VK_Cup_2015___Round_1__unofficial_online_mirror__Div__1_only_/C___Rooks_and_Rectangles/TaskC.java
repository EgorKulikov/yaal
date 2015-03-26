package on2015_03.on2015_03_21_VK_Cup_2015___Round_1__unofficial_online_mirror__Div__1_only_.C___Rooks_and_Rectangles;



import net.egork.collections.intervaltree.IntervalTree;
import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		int count = in.readInt();
		int queryCount = in.readInt();
		int[] row = new int[count];
		int[] column = new int[count];
		IOUtils.readIntArrays(in, row, column);
		int[] fromRow = new int[queryCount];
		int[] fromColumn = new int[queryCount];
		int[] toRow = new int[queryCount];
		int[] toColumn = new int[queryCount];
		IOUtils.readIntArrays(in, fromRow, fromColumn, toRow, toColumn);
		MiscUtils.decreaseByOne(row, column, fromRow, fromColumn, toRow, toColumn);
		boolean[] answer = new boolean[queryCount];
		for (int r = 0; r < 2; r++) {
			IntervalTree tree = new LongIntervalTree(columnCount) {
				@Override
				protected long joinValue(long left, long right) {
					return Math.min(left, right);
				}

				@Override
				protected long joinDelta(long was, long delta) {
					return Math.max(was, delta);
				}

				@Override
				protected long accumulate(long value, long delta, int length) {
					if (delta == -1) {
						return value;
					}
					return Math.max(value, delta);
				}

				@Override
				protected long neutralValue() {
					return Integer.MAX_VALUE;
				}

				@Override
				protected long neutralDelta() {
					return -1;
				}

				@Override
				protected long initValue(int index) {
					return -1;
				}
			};
			int[] rookOrder = ArrayUtils.order(row);
			int[] queryOrder = ArrayUtils.order(toRow);
			int at = 0;
			for (int i : queryOrder) {
				while (at < count && row[rookOrder[at]] <= toRow[i]) {
					tree.update(column[rookOrder[at]], column[rookOrder[at]], row[rookOrder[at]]);
					at++;
				}
				if (tree.query(fromColumn[i], toColumn[i]) >= fromRow[i]) {
					answer[i] = true;
				}
			}
			int temp = rowCount;
			rowCount = columnCount;
			columnCount = temp;
			int[] tArr = row;
			row = column;
			column = tArr;
			tArr = fromRow;
			fromRow = fromColumn;
			fromColumn = tArr;
			tArr = toRow;
			toRow = toColumn;
			toColumn = tArr;
		}
		for (boolean b : answer) {
			if (b) {
				out.printLine("YES");
			} else {
				out.printLine("NO");
			}
		}
	}
}
