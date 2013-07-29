package on2013_07.on2013_07_17_Yandex_Algorithm_Onlne_Round__2.TaskC;



import net.egork.collections.comparators.IntComparator;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		final long[] x = new long[count];
		final long[] y = new long[count];
		IOUtils.readLongArrays(in, x, y);
		int[] order = ArrayUtils.createOrder(count);
		double answer = 0;
		for (int i = 0; i < count; i++) {
			int min = i;
			for (int j = i + 1; j < count; j++) {
				if (x[j] < x[min])
					min = j;
			}
			long t = x[i];
			x[i] = x[min];
			x[min] = t;
			t = y[i];
			y[i] = y[min];
			y[min] = t;
			for (int j = i + 1; j < count; j++) {
				x[j] -= x[i];
				y[j] -= y[i];
				order[j] = j;
			}
			ArrayUtils.sort(order, i + 1, count, new IntComparator() {
				public int compare(int first, int second) {
					return IntegerUtils.longCompare(x[first] * y[second], y[first] * x[second]);
				}
			});
			double sx = 0;
			double sy = 0;
			for (int j = i + 1; j < count; j++) {
				int k = order[j];
				answer += sy * x[k] - sx * y[k];
				sx += x[k];
				sy += y[k];
			}
		}
		answer /= count;
		answer /= count - 1;
		answer /= count - 2;
		answer *= 3;
		out.printLine(answer);
    }
}
