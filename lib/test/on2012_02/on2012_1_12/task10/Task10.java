package on2012_02.on2012_1_12.task10;



import net.egork.collections.CollectionUtils;
import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Comparator;

public class Task10 {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		double[] x = new double[count];
		double[] y = new double[count];
		IOUtils.readDoubleArrays(in, x, y);
		int vertexCount = in.readInt();
		double[] px = new double[vertexCount];
		double[] py = new double[vertexCount];
		IOUtils.readDoubleArrays(in, px, py);
		boolean[] good = new boolean[count];
		final double[] from = new double[count];
		double[] to = new double[count];
		outer:
		for (int i = 0; i < count; i++) {
			double[] angles = new double[vertexCount];
			for (int j = 0; j < vertexCount; j++) {
				if (x[i] == px[j] && y[i] == py[j])
					continue outer;
				angles[j] = Math.atan2(py[j] - y[i], px[j] - x[i]);
			}
			Arrays.sort(angles);
			for (int j = 1; j < vertexCount; j++) {
				if (angles[j] - angles[j - 1] > Math.PI) {
					good[i] = true;
					from[i] = angles[j - 1];
					to[i] = angles[j] - Math.PI;
				}
			}
			if (!good[i] && angles[0] + 2 * Math.PI - angles[vertexCount - 1] > Math.PI) {
				good[i] = true;
				from[i] = angles[vertexCount - 1];
				to[i] = Math.PI + angles[0];
			}
		}
		for (int i = 0; i < count; i++) {
			if (!good[i])
				continue;
			for (int j = 0; j < count; j++) {
				if (i == j || !good[j])
					continue;
				if (from[i] <= from[j] && to[i] >= to[j] && (i > j || from[i] < from[j] || to[i] > to[j])) {
					good[i] = false;
					break;
				}
			}
		}
		int goodCount = CollectionUtils.count(Array.wrap(good), true);
		Integer[] order = new Integer[goodCount];
		int index = 0;
		for (int i = 0; i < count; i++) {
			if (good[i])
				order[index++] = i;
		}
		Arrays.sort(order, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return Double.compare(from[o2], from[o1]);
			}
		});
		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < goodCount; i++) {
			int current = 1;
			double last = from[order[i]];
			for (int j = i + 1; j < goodCount; j++) {
				if (to[order[j]] < last) {
					last = from[order[j]];
					current++;
				}
			}
			last += 2 * Math.PI;
			for (int j = 0; j < i; j++) {
				if (to[order[j]] < last) {
					last = from[order[j]];
					current++;
				}
			}
			answer = Math.min(answer, current);
		}
		out.printLine(answer);
	}
}
