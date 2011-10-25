import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;

public class TaskC implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		int[] nimber = new int[n + 1];
		int[] minimum = new int[n + 1];
		Arrays.fill(minimum, -1);
		int[] value = new int[5 * n];
		for (int i = 3; i <= n; i++) {
			for (int j = 2; j * j < 2 * i; j++) {
				if (2 * i % j == 0) {
					int min = j;
					int max = 2 * i / j;
					if ((max & 1) == (min & 1))
						continue;
					int a = (min + max - 1) >> 1;
					int b = a - min;
					int current = 0;
					for (int k = b + 1; k <= a; k++)
						current ^= nimber[k];
					if (current == 0 && minimum[i] == -1)
						minimum[i] = min;
					value[current] = i;
				}
			}
			for (int j = 0; ; j++) {
				if (value[j] != i) {
					nimber[i] = j;
					break;
				}
			}
		}
		out.println(minimum[n]);
	}
}

