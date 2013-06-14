package on2013_05.on2013_05_26_OpenCup_South_Caucasus_GP.TaskF;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.numbers.FastFourierTransform;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[][] qty = IOUtils.readIntTable(in, count, count);
		int[][] direct = new int[count][count];
		int[][] reverse = new int[count][count];
		long[] a = new long[count * count * 2];
		long[] b = new long[count * count * 2];
		long sum = 0;
		for (int[] row : qty)
			sum += ArrayUtils.sumArray(row);
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				a[i * count * 2 + j] = qty[i][j];
				direct[i][j] = i * count * 2 + j;
				b[(count - i - 1) * count * 2 + count - j - 1] = qty[i][j];
				reverse[i][j] = (count - i - 1) * count * 2 + count - j - 1;
			}
		}
		long[] result = FastFourierTransform.multiply(a, b);
		double total = 0;
		double cnt = 0;
		int k = 0;
		Value[] values = new Value[count * (2 * count - 1) - count + 1];
		for (int dx = 0; dx < count; dx++) {
			for (int dy = dx == 0 ? 0 : -count + 1; dy < count; dy++) {
				int i0 = 0;
				int j0 = dy >= 0 ? 0 : -dy;
				int i1 = dx;
				int j1 = dy >= 0 ? dy : 0;
				int index = direct[i0][j0] + reverse[i1][j1];
				long qt = result[index];
				if (dx == 0 && dy == 0) {
					qt -= sum;
					qt /= 2;
				}
				Value value = new Value(dx * dx + dy * dy, qt);
				values[k++] = value;
				total += Math.sqrt(value.distance) * value.qty;
				cnt += value.qty;
			}
		}
		Arrays.sort(values);
		List<Value> answer = new ArrayList<Value>();
		Value last = null;
		for (Value value : values) {
			if (value.qty == 0)
				continue;
			if (last == null || last.distance != value.distance)
				answer.add(last = value);
			else
				last.qty += value.qty;
		}
		out.printLine(total / cnt);
		if (answer.size() > 10000) {
			answer = answer.subList(0, 10000);
		}
		for (Value value : answer)
			out.printLine(value.distance, value.qty);
	}

	static class Value implements Comparable<Value> {
		final int distance;
		long qty;

		Value(int distance, long qty) {
			this.distance = distance;
			this.qty = qty;
		}

		public int compareTo(Value o) {
			if (distance != o.distance)
				return distance - o.distance;
			return IntegerUtils.longCompare(qty, o.qty);
		}
	}
}
