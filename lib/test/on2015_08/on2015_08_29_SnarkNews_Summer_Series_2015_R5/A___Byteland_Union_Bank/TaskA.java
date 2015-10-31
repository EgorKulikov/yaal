package on2015_08.on2015_08_29_SnarkNews_Summer_Series_2015_R5.A___Byteland_Union_Bank;



import net.egork.collections.intervaltree.IntervalTree;
import net.egork.collections.intervaltree.SumIntervalTree;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskA {
	int BUBEN = 1000;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] a = IOUtils.readIntArray(in, n);
		ArrayUtils.reverse(a);
		a = Arrays.copyOf(a, n + 1);
		ArrayUtils.reverse(a);
		int m = in.readInt();
		int[] add = new int[BUBEN + 1];
		IntervalTree tree = new SumIntervalTree(ArrayUtils.asLong(a));
		for (int i = 0; i < m; i++) {
			int type = in.readInt();
			if (type == 1) {
				int l = in.readInt();
				int r = in.readInt();
				int c = in.readInt();
				tree.update(l, r, c);
			} else if (type == 2) {
				int d = in.readInt();
				int c = in.readInt();
				if (d <= BUBEN) {
					add[d] += c;
				} else {
					for (int j = d; j <= n; j += d) {
						tree.update(j, j, c);
					}
				}
			} else {
				int l = in.readInt();
				int r = in.readInt();
				long answer = tree.query(l, r);
				for (int j = 1; j <= BUBEN; j++) {
					answer += (long)(r / j - (l - 1) / j) * add[j];
				}
				out.printLine(answer);
				out.flush();
			}
		}
	}
}
