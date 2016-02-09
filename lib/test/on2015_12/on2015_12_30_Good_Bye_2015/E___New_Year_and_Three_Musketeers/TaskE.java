package on2015_12.on2015_12_30_Good_Bye_2015.E___New_Year_and_Three_Musketeers;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.NavigableSet;
import java.util.TreeSet;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] aa = IOUtils.readIntArray(in, 3);
		int[] t = Arrays.copyOf(IOUtils.readIntArray(in, n), n + 1);
		NavigableSet<Integer> bad = new TreeSet<>((x, y) -> t[x] != t[y] ? t[x] - t[y] : x - y);
		for (int i = 0; i < n; i++) {
			bad.add(i);
		}
		ArrayUtils.sort(aa);
		int a = aa[0];
		int b = aa[1];
		int c = aa[2];
		if (t[bad.last()] > a + b + c) {
			out.printLine(-1);
			return;
		}
		int answer = 0;
		while (t[bad.last()] > b + c) {
			bad.pollLast();
			answer++;
			if (bad.isEmpty()) {
				out.printLine(answer);
				return;
			}
		}
		while (t[bad.last()] > a + c) {
			bad.pollLast();
			answer++;
			t[n] = a;
			Integer x = bad.lower(n);
			if (x != null) {
				bad.remove(x);
			}
			if (bad.isEmpty()) {
				out.printLine(answer);
				return;
			}
		}
		int lim = Math.max(a + b, c);
		while (t[bad.last()] > lim) {
			bad.pollLast();
			answer++;
			t[n] = b;
			Integer x = bad.lower(n);
			if (x != null) {
				bad.remove(x);
			}
			if (bad.isEmpty()) {
				out.printLine(answer);
				return;
			}
		}
		while (t[bad.last()] > c) {
			bad.pollLast();
			answer++;
			t[n] = c;
			Integer x = bad.lower(n);
			if (x != null) {
				bad.remove(x);
			}
			if (bad.isEmpty()) {
				out.printLine(answer);
				return;
			}
		}
		while (true) {
			bad.pollLast();
			answer++;
			t[n] = a;
			Integer x = bad.lower(n);
			t[n] = b;
			Integer y = bad.lower(n);
			if (x != null && y != null && x.equals(y)) {
				y = bad.lower(x);
			}
			if (x != null && y != null) {
				bad.remove(x);
				bad.remove(y);
			} else {
				t[n] = a + b;
				x = bad.lower(n);
				if (x != null) {
					bad.remove(x);
				}
			}
			if (bad.isEmpty()) {
				out.printLine(answer);
				return;
			}
		}
	}
}
