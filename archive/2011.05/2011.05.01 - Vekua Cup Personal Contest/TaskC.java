import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;

public class TaskC implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int count = in.readInt();
		long[] x = IOUtils.readLongArray(in, count);
		Arrays.sort(x);
		long start = in.readInt();
		long finish = in.readInt();
		if (count == 0) {
			out.println(start == finish ? 1 : 0);
			return;
		}
		if (x[count - 1] == x[0]) {
			out.println(start == finish || start + finish == 2 * x[0] ? 1 : 0);
			return;
		}
		long gcd = 0;
		for (int i = 1; i < count; i++)
			gcd = IntegerUtils.gcd(gcd, 2 * Math.abs(x[i] - x[0]));
		if (IntegerUtils.trueMod(start, gcd) == IntegerUtils.trueMod(finish, gcd)) {
			out.println(1);
			return;
		}
		finish = 2 * x[0] - finish;
		if (IntegerUtils.trueMod(start, gcd) == IntegerUtils.trueMod(finish, gcd)) {
			out.println(1);
			return;
		}
		out.println(0);
	}
}

