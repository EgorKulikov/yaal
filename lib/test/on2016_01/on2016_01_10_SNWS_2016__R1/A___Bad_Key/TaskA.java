package on2016_01.on2016_01_10_SNWS_2016__R1.A___Bad_Key;



import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] a = IOUtils.readIntArray(in, n);
		int[] g = new int[n + 1];
		for (int i = n - 1; i >= 0; i--) {
			g[i] = IntegerUtils.gcd(g[i + 1], a[i]);
		}
		int gcd = 0;
		int answer = 0;
		int at = -1;
		for (int i = 0; i < n; i++) {
			int candidate = IntegerUtils.gcd(gcd, g[i + 1]);
			if (candidate > answer) {
				answer = candidate;
				at = i + 1;
			}
			gcd = IntegerUtils.gcd(gcd, a[i]);
		}
		out.printLine(at, answer);
	}
}
