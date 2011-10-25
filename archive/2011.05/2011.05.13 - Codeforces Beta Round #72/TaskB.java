import net.egork.io.IOUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.Arrays;

public class TaskB implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int count = in.readInt();
		long length = in.readLong();
		int[] repetitions = IOUtils.readIntArray(in, count);
		int[] repetitionsCopy = repetitions.clone();
		Arrays.sort(repetitionsCopy);
		int last = 0;
		int remaining = count;
		for (int i : repetitionsCopy) {
			long timeRequired = (long) (i - last) * remaining;
			if (timeRequired <= length) {
				length -= timeRequired;
				remaining--;
				last = i;
			} else {
				long delta = last + length / remaining + 1;
				length %= remaining;
				if (length == 0) {
					int index = 0;
					int[] answer = new int[remaining];
					for (int k = 0; k < count; k++) {
						if (repetitions[k] >= delta)
							answer[index++] = k + 1;
					}
					IOUtils.printArray(answer, out);
					return;
				}
				int[] answer = new int[remaining];
				for (int j = 0; j < count; j++) {
					if (repetitions[j] > last) {
						length--;
						if (length == 0) {
							int index = 0;
							for (int k = j + 1; k < count; k++) {
								if (repetitions[k] >= delta)
									answer[index++] = k + 1;
							}
							for (int k = 0; k <= j; k++) {
								if (repetitions[k] > delta)
									answer[index++] = k + 1;
							}
							answer = Arrays.copyOf(answer, index);
							IOUtils.printArray(answer, out);
							return;
						}
					}
				}
			}
		}
		if (length == 0)
			out.println();
		else
			out.println(-1);
	}
}

