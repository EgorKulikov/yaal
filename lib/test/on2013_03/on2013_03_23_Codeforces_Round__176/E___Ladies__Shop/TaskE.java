package on2013_03.on2013_03_23_Codeforces_Round__176.E___Ladies__Shop;



import net.egork.io.IOUtils;
import net.egork.numbers.FastFourierTransform;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int maxWeight = in.readInt();
		int[] weights = IOUtils.readIntArray(in, count);
		long[] x = new long[maxWeight + 1];
		for (int i : weights)
			x[i] = 1;
		long[] y = FastFourierTransform.multiply(x, x);
		List<Integer> answer = new ArrayList<Integer>();
		for (int i = 1; i <= maxWeight; i++) {
			if (x[i] == 0 && y[i] != 0) {
				out.printLine("NO");
				return;
			}
			if (x[i] == 1 && y[i] == 0)
				answer.add(i);
		}
		out.printLine("YES");
		out.printLine(answer.size());
		out.printLine(answer.toArray());
	}
}
