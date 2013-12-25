package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskF {
	private double[] pa;
	private double[] pb;
	private double[] pc;
	private double[] a;
	private double[] b;
	private double[] c;
	List<Integer>[] nb;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		pa = new double[n];
		pb = new double[n];
		pc = new double[n];
		a = new double[n];
		b = new double[n];
		c = new double[n];
		for (int i = 0; i < n; i++) {
			pa[i] = in.readInt() * 0.01;
			pb[i] = in.readInt() * 0.01;
			pc[i] = in.readInt() * 0.01;
		}

		nb = new List[n];
		for (int i = 0; i < n; i++) nb[i] = new ArrayList<Integer>();

		for (int i = 0; i < n - 1; i++) {
			int u = in.readInt();
			int v = in.readInt();
//			System.out.println(u + " " + v);
			nb[u - 1].add(v - 1);
			nb[v - 1].add(u - 1);
		}
		dfs(0, -1);
		out.printLine(a[0] + b[0] * c[0]);
    }

	private void dfs(int i, int p) {
		double sa = 0;
		double sb = 0;
		double sc = 0;
		double ppc = 1;
		for (Integer j : nb[i]) {
			if (j != p) {
				dfs(j, i);
				ppc *= c[j];
				sa += a[j];
				sb += b[j];
				sc += b[j] * c[j];
			}
		}
		a[i] = sa + pa[i] * (sc);
		c[i] = pa[i] + pc[i] * ppc;
		b[i] = c[i] == 0 ? 0 : ((pc[i] * ppc) / c[i]) * (1 + sb);
//		System.out.println(i + " " + a[i] + " " + b[i] + " " + c[i]);
	}
}
