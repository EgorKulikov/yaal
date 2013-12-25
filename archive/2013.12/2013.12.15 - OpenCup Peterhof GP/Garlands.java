package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Garlands {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] p = new int[n];
		p[0] = -1;
		for (int i = 1; i < n; i++) {
			p[i] = in.readInt() - 1;
		}
		int g = in.readInt();
		Set<Integer>[] gr = new Set[n];
		for (int i = 0; i < n; i++) {
			gr[i] = new HashSet<Integer>();
		}
		for (int i = 0; i < g; i++) {
			int k = in.readInt();
			for (int j = 0; j < k; j++) {
				int x = in.readInt() - 1;
				gr[x].add(i);
			}
		}
		boolean[] z = new boolean[g];
		List<Integer> res = new ArrayList<Integer>();
		for (int i = n - 1; i >= 0; i--) {
			boolean d = false;
			for (Integer gg : gr[i]) {
				if (z[gg]) continue;
				if (p[i] >= 0 && gr[p[i]].contains(gg)) continue;
				d = true;
				break;
			}
			if (d) {
				res.add(i);
				for (Integer gg : gr[i]) {
					z[gg] = true;
				}
			}
		}
		out.printLine(res.size());
		for (Integer integer : res) {
			out.printLine(integer + 1);
		}

    }
}
