import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskC implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int artistCount = in.readInt();
		char[] result = new char[artistCount];
		List<Integer> divisors = new ArrayList<Integer>();
		for (int i = 1; i < artistCount; i++) {
			if (artistCount % i == 0)
				divisors.add(i);
		}
		boolean[] bad = new boolean[artistCount];
		for (char c = 'a'; c <= 'z'; c++) {
			Arrays.fill(bad, false);
			for (int i = 0; i < artistCount; i++) {
				if (result[i] == 0 && !bad[i]) {
					result[i] = c;
					for (int divisor : divisors) {
						if (i + divisor < artistCount)
							bad[i + divisor] = true;
						if (i < divisor)
							bad[i + artistCount - divisor] = true;
					}
				}
			}
		}
		for (int i = 0; i < artistCount; i++) {
			if (result[i] == 0) {
				out.println("Impossible");
				return;
			}
		}
		out.println(new String(result));
	}
}

