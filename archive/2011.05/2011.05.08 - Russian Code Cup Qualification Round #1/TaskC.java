import net.egork.io.IOUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskC implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int size = in.readInt();
		int[] numbers = IOUtils.readIntArray(in, size);
		int shift = in.readInt();
		int answer = 0;
		for (int i = 0; i < shift; i++) {
			for (int j = i; j < size; j += shift) {
				int minimum = Integer.MAX_VALUE;
				int index = -1;
				for (int k = j; k < size; k += shift) {
					if (numbers[k] < minimum) {
						minimum = numbers[k];
						index = k;
					}
				}
				answer += (index - j) / shift;
				for (int k = index; k > j; k -= shift)
					numbers[k] = numbers[k - shift];
				numbers[j] = minimum;
			}
		}
		for (int i = 1; i < size; i++) {
			if (numbers[i] < numbers[i - 1]) {
				out.println(-1);
				return;
			}
		}
		out.println(answer);
	}
}

