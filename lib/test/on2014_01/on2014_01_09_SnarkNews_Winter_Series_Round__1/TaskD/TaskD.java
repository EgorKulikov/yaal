package on2014_01.on2014_01_09_SnarkNews_Winter_Series_Round__1.TaskD;



import net.egork.collections.comparators.IntComparator;
import net.egork.geometry.Point;
import net.egork.geometry.Polygon;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int goodCount = in.readInt();
		int badCount = in.readInt();
		int count = goodCount + badCount;
		final int[] x = new int[count];
		final int[] y = new int[count];
		IOUtils.readIntArrays(in, x, y);
		int[] order = ArrayUtils.createOrder(count);
		ArrayUtils.sort(order, new IntComparator() {
			public int compare(int first, int second) {
				return Long.compare((long)x[first] * y[second], (long)x[second] * y[first]);
			}
		});
		Point[] points = new Point[count];
		for (int i = 0; i < count; i++)
			points[i] = new Point(x[i], y[i]);
		int[][] answer = new int[count][count];
		Point origin = new Point(0, 0);
		for (int i : order) {
			for (int j : order) {
				if (i == j)
					break;
				for (int k : order) {
					if (k == j)
						break;
					if (Polygon.over(points[k], points[j], points[i]))
						answer[j][i] = Math.max(answer[j][i], answer[k][j]);
				}
				Polygon polygon = new Polygon(points[i], points[j], origin);
				for (int k = 0; k < count; k++) {
					if (k != i && k != j && polygon.contains(points[k])) {
						if (k < goodCount)
							answer[j][i]++;
						else
							answer[j][i]--;
					}
				}
				if (j < goodCount)
					answer[j][i]++;
				else
					answer[j][i]--;
			}
		}
		int result = 0;
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				if (j < goodCount)
					answer[i][j]++;
				else
					answer[i][j]--;
				result = Math.max(result, answer[i][j]);
			}
		}
		out.printLine(result);
    }
}
