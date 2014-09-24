package on2014_08.on2014_08_13_SnarkNews_Summer_Series_2014_Round_1.G___Avia;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskG {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int required = in.readInt();
		int[] x0 = new int[count];
		int[] y0 = new int[count];
		int[] x1 = new int[count];
		int[] y1 = new int[count];
		int[] opacity = new int[count];
		IOUtils.readIntArrays(in, x0, y0, x1, y1, opacity);
		int[] ys = new int[2 * count];
		System.arraycopy(y0, 0, ys, 0, count);
		System.arraycopy(y1, 0, ys, count, count);
		Arrays.sort(ys);
		ys = ArrayUtils.unique(ys);
		int[] at = new int[2 * count];
		int[] start = new int[2 * count];
		int[] end = new int[2 * count];
		int[] delta = new int[2 * count];
		for (int i = 0; i < count; i++) {
			at[2 * i] = x0[i];
			start[2 * i] = Arrays.binarySearch(ys, y0[i]);
			end[2 * i] = Arrays.binarySearch(ys, y1[i]);
			delta[2 * i] = opacity[i];
			at[2 * i + 1] = x1[i];
			start[2 * i + 1] = Arrays.binarySearch(ys, y0[i]);
			end[2 * i + 1] = Arrays.binarySearch(ys, y1[i]);
			delta[2 * i + 1] = -opacity[i];
		}
		int[] level = new int[ys.length - 1];
		int[] order = ArrayUtils.order(at);
		int last = 0;
		long answer = 0;
		for (int i : order) {
			for (int j = 0; j < level.length; j++) {
				if (level[j] >= required) {
					answer += (long)(at[i] - last) * (ys[j + 1] - ys[j]);
				}
			}
			last = at[i];
			for (int j = start[i]; j < end[i]; j++) {
				level[j] += delta[i];
			}
		}
		out.printLine(answer);
	}
}
