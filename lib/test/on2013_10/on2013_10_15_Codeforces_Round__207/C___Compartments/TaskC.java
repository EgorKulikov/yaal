package on2013_10.on2013_10_15_Codeforces_Round__207.C___Compartments;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] qty = IOUtils.readIntArray(in, count);
		long total = ArrayUtils.sumArray(qty);
		if (total < 3 || total == 5) {
			out.printLine(-1);
			return;
		}
		int[] per = new int[5];
		for (int i : qty)
			per[i]++;
		int[] current = new int[5];
		int[] required = new int[5];
		int answer = Integer.MAX_VALUE;
		for (int i = 0; i * 4 <= total; i++) {
			int j = (int) ((total - i * 4) / 3);
			if (i * 4 + j * 3 != total | i + j > count)
				continue;
			System.arraycopy(per, 0, current, 0, 5);
			Arrays.fill(required, 0);
			required[4] = i;
			required[3] = j;
			required[0] = count - i - j;
			int candidate = 0;
			for (int k = 4; k >= 0; k--) {
				for (int l = 4; l >= 0; l--) {
					int min = Math.min(current[k], required[l]);
					candidate += min * Math.abs(k - l);
					current[k] -= min;
					required[l] -= min;
				}
			}
			answer = Math.min(answer, candidate);
		}
		out.printLine(answer / 2);
    }
}
