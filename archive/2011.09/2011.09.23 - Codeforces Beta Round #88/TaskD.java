import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskD implements Solver {
	private long doubleMod;

	public void solve(int testNumber, InputReader in, PrintWriter out) {
		long size = in.readLong();
		int count = in.readInt();
		long mod = in.readInt();
		doubleMod = 2 * mod;
		for (int i = 0; i < count; i++) {
			long left = in.readLong() - 1;
			long right = in.readLong();
			long min = in.readLong();
			long max = Math.min(in.readLong(), size);
			out.println(query(0, size, 1L, 1L, left, right, min, max, mod));
		}
	}

	private long query(long from, long to, long start, long shift, long left, long right, long min, long max, long mod) {
		if (from >= right || to <= left)
			return 0;
		if (from >= left && to <= right) {
			long plus = Math.max(0, min - start + shift - 1) / shift;
			start += plus * shift;
			if (start > max)
				return 0;
			long count = (max - start) / shift;
			long end = start + count * shift;
			count++;
			long first = (start + end) % doubleMod;
			count %= doubleMod;
			return (first * count) % doubleMod / 2;
		}
		long middle = (from + to + 1) / 2;
		long result = query(from, middle, start, shift << 1, left, right, min, max, mod) +
			query(middle, to, start + shift, shift << 1, left, right, min, max, mod);
		if (result >= mod)
			result -= mod;
		return result;
	}


}

