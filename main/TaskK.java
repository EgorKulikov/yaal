import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.Exit;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.util.*;

public class TaskK implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int size;
		try {
			size = in.readInt();
		} catch (InputMismatchException e) {
			Exit.exit(in, out);
			return;
		}
		int[][] arrays = IOUtils.readIntTable(in, size, size);
		long[] power = new long[size];
		power[0] = 1;
		for (int i = 1; i < size; i++)
			power[i] = power[i - 1] * 6299;
		for (int i = 0; i < size; i++)
			Arrays.sort(arrays[i]);
		int[][] indices = new int[size][size];
		int[] answer = new int[size];
		NavigableSet<Sum> queue = new TreeSet<Sum>();
		for (int i = 0; i < size; i++)
			answer[0] += arrays[i][0];
		long lastHash = 0;
		for (int i = 0; i < size - 1; i++) {
			for (int j = 0; j < size; j++) {
				queue.add(new Sum(answer[i] + arrays[j][indices[i][j] + 1] - arrays[j][indices[i][j]], i, j, lastHash + power[j]));
				if (queue.size() + i == size)
					queue.remove(queue.last());
			}
			Sum best = queue.first();
			queue.remove(best);
			answer[i + 1] = best.sum;
			System.arraycopy(indices[best.base], 0, indices[i + 1], 0, size);
			indices[i + 1][best.index]++;
			lastHash = best.hash;
		}
		IOUtils.printArray(answer, out);
	}

	private static class Sum implements Comparable<Sum> {
		private final int sum;
		private final int base;
		private final int index;
		private final long hash;

		private Sum(int sum, int base, int index, long hash) {
			this.sum = sum;
			this.base = base;
			this.index = index;
			this.hash = hash;
		}

		public int compareTo(Sum o) {
			if (sum != o.sum)
				return sum - o.sum;
			return IntegerUtils.longCompare(hash, o.hash);
		}
	}
}
