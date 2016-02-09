package net.egork;

import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		int[] a = new int[m];
		int[] b = new int[m];
		IOUtils.readIntArrays(in, a, b);
		IntList ones = new IntArrayList(n - 1);
		IntList zeroes = new IntArrayList(m - n + 1);
		for (int i = 0; i < m; i++) {
			if (b[i] == 1) {
				ones.add(i);
			} else {
				zeroes.add(i);
			}
		}
		ones.sort((x, y) -> a[x] - a[y]);
		zeroes.sort((x, y) -> a[x] - a[y]);
		int[] u = new int[m];
		int[] v = new int[m];
		int at = 2;
		for (int i : ones) {
			u[i] = 1;
			v[i] = at++;
		}
		at = 2;
		int at2 = 3;
		for (int j : zeroes) {
			if (a[j] < a[ones.get(at2 - 2)]) {
				out.printLine(-1);
				return;
			}
			u[j] = at;
			v[j] = at2;
			at++;
			if (at == at2) {
				at2++;
				at = 2;
			}
		}
		for (int i = 0; i < m; i++) {
			out.printLine(u[i], v[i]);
		}
	}
}
