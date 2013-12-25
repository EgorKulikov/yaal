package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class Changes {
	
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] a = new int[n];
		int[] b = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = in.readInt();
		}
		for (int i = 0; i < n; i++) {
			b[i] = in.readInt();
		}
		int[] d = new int[n];
		int mind = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			d[i] = b[i] - a[i];
			mind = Math.min(mind, d[i]);
		}
		if (mind > 0) {
			out.printLine(-1);
			return;
		}
		int[] c = new int[n];
		for (int i = 0; i < n; i++) {
			c[i] = (d[i]-mind) / n;
		}
		
		List<Integer> res = new ArrayList<Integer>();
		
		while (true) {
			int k = -1;
			for (int i = 0; i < n; i++) {
				if (c[i] > 0) {
					if (k == -1 || a[i] < a[k]) {
						k = i;
					}
				}
			}
			if (k == -1) break;
			c[k]--;
			a[k] += n;
			res.add(k + 1);
			for (int i = 0; i < n; i++) {
				a[i]--;
				if (a[i] < 0) {
					out.printLine(-1);
					return;
				}
			}
		}
		
		
		for (int i = 0; i < n; i++) {
			if (a[i] != b[i]) {
				out.printLine(-1);
				return;
			}
		}

		out.printLine(res.size());
		for (Integer integer : res) {
			out.print(integer + " ");
		}
	}
}
