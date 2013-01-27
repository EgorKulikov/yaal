package on2013_01.on2013_01_20_Codeforces_Round__162.C___Choosing_Balls;



import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int queryCount = in.readInt();
		int[] values = IOUtils.readIntArray(in, count);
		int[] colors = IOUtils.readIntArray(in, count);
		MiscUtils.decreaseByOne(colors);
		long[] result = new long[count];
		for (int i = 0; i < queryCount; i++) {
			Arrays.fill(result, Long.MIN_VALUE / 2);
			int maxIndex = 0;
			long max = 0;
			long secondMax = 0;
			long same = in.readInt();
			long other = in.readInt();
			for (int j = 0; j < count; j++) {
				long current;
				if (maxIndex == colors[j]) {
					current = Math.max(same * values[j] + result[colors[j]], other * values[j] + secondMax);
					max = Math.max(max, current);
				} else {
					current = Math.max(same * values[j] + result[colors[j]], other * values[j] + max);
					if (current > max) {
						secondMax = max;
						max = current;
						maxIndex = colors[j];
					} else
						secondMax = Math.max(secondMax, current);
				}
				result[colors[j]] = Math.max(result[colors[j]], current);
			}
			out.printLine(max);
		}
	}
}
