package on2015_12.on2015_12_30_Good_Bye_2015.F___New_Year_and_Cleaning;



import net.egork.generated.collections.list.IntArray;
import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
	private static final long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int h = in.readInt();
		int w = in.readInt();
		char[] template = IOUtils.readCharArray(in, n);
		int l = 0;
		int r = 0;
		int u = 0;
		int d = 0;
		int x = 0;
		int y = 0;
		IntList rs = new IntArrayList();
		IntList ls = new IntArrayList();
		IntList us = new IntArrayList();
		IntList ds = new IntArrayList();
		long answer = 0;
		for (int i = 0; i < n; i++) {
			if (template[i] == 'U') {
				y++;
			} else if (template[i] == 'D') {
				y--;
			} else if (template[i] == 'L') {
				x--;
			} else if (template[i] == 'R') {
				x++;
			}
			if (x < l) {
				l = x;
				ls.add(i + 1);
				answer += (long)(i + 1) * h;
				w--;
			} else if (x > r) {
				r = x;
				rs.add(i + 1);
				answer += (long)(i + 1) * h;
				w--;
			} else if (y < d) {
				d = y;
				ds.add(i + 1);
				answer += (long)(i + 1) * w;
				h--;
			} else if (y > u) {
				u = y;
				us.add(i + 1);
				answer += (long)(i + 1) * w;
				h--;
			}
			answer %= MOD;
			if (w == 0 || h == 0) {
				out.printLine(answer);
				return;
			}
		}
		if (x == 0 && y == 0) {
			out.printLine(-1);
			return;
		}
		IntList at = new IntArrayList();
		IntList dir = new IntArrayList();
		if (x > 0) {
			at.addAll(rs.subList(rs.size() - x, rs.size()));
			dir.addAll(new IntArray(new int[x]));
		} else if (x < 0) {
			at.addAll(ls.subList(ls.size() + x, ls.size()));
			dir.addAll(new IntArray(new int[-x]));
		}
		if (y > 0) {
			at.addAll(us.subList(us.size() - y, us.size()));
			dir.addAll(new IntArray(ArrayUtils.createArray(y, 1)));
		} else if (y < 0) {
			at.addAll(ds.subList(ds.size() + y, ds.size()));
			dir.addAll(new IntArray(ArrayUtils.createArray(-y, 1)));
		}
		int[] aa = at.toArray();
		int[] dd = dir.toArray();
		ArrayUtils.orderBy(aa, dd);
		for (long add = n; ; add += n) {
			for (int i = 0; i < aa.length; i++) {
				if (dd[i] == 0) {
					answer += (add + aa[i]) % MOD * h;
					w--;
				} else {
					answer += (add + aa[i]) % MOD * w;
					h--;
				}
				answer %= MOD;
				if (w == 0 || h == 0) {
					out.printLine(answer);
					return;
				}
			}
		}
	}
}
