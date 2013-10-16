package on2013_09.on2013_09_20_Codeforces_Round__201.C___Number_Transformation_II;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] mods = IOUtils.readIntArray(in, count);
		int from = in.readInt();
		int to = in.readInt();
		Arrays.sort(mods);
		mods = ArrayUtils.unique(mods);
		count = mods.length;
		int[] stillIn = ArrayUtils.createOrder(count);
		int stillSize = count;
		int answer = 0;
		while (from != to) {
			int minNext = from - 1;
			int k = 0;
			for (int i = 0; i < stillSize; i++) {
				int j = stillIn[i];
				int down = from - from % mods[j];
				if (down >= to) {
					stillIn[k++] = j;
					minNext = Math.min(minNext, down);
				}
			}
			stillSize = k;
			from = minNext;
			answer++;
		}
		out.printLine(answer);
    }
}
