import net.egork.utils.Exit;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.Arrays;

public class Pairs implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int n = in.readInt();
		int X = in.readInt();
		if (n == 0 && X == 0) {
			Exit.exit(in, out);
			return;
		}
		int[] a = new int[n];
		for (int i = 0; i < n; ++i) {
			a[i] =  in.readInt();
		}
		int[] factors = new int[10];
		int numFactors = 0;
		for (int i = 2; i * i <= X; ++i) {
			if (X % i == 0) {
				factors[numFactors++] = i;
				while (X % i == 0) X /= i;
			}
		}
		if (X > 1) {
			factors[numFactors++] = X;
			X = 1;
		}
		{
			int[] tmp = new int[numFactors];
			System.arraycopy(factors, 0, tmp, 0, numFactors);
			factors = tmp;
		}
		int[] rem = new int[n];
		long res = 0;
		for (int set = 0; set < (1 << numFactors); ++set) {
			int prod = 1;
			int sign = 1;
			for (int i = 0; i < numFactors; ++i)
				if ((set & (1 << i)) != 0) {
					prod *= factors[i];
					sign = -sign;
				}
			for (int i = 0; i < n; ++i)
				rem[i] = a[i] % prod;
			Arrays.sort(rem);
			long cur = 0;
			long cnt = 0;
			for (int i = 0; i < n; ++i) {
				if (i == 0 || rem[i] != rem[i - 1]) {
					cur += cnt * (cnt - 1) / 2;
					cnt = 0;
				}
				++cnt;
			}
			cur += cnt * (cnt - 1) / 2;
			res += cur * sign;
		}
		out.println(res);
	}
}

