package net.egork;

import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskG {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		int h[] = new int[n];
		for (int i = 0; i < n; i++) {
			h[i] = in.readInt();
		}
		int idx[] = ArrayUtils.order(h);
		int ins[] = new int[n];
		int outs[] = new int[n];
		for (int i = 0; i < m; i++) {
			outs[in.readInt() - 1]++;
			ins[in.readInt() - 1]++;
		}
		IntList ans = new IntArrayList();
		for (int id: idx) {
			if (ins[id] == 0 || outs[id] == 0) {
				ans.add(id);
			}
		}
		out.printLine(h[idx[n - 1]] - h[idx[0]]);
		out.printLine(ans.size() - 1);
		for (int i = 0; i < ans.size() - 1; i++) {
			out.printLine(ans.get(i) + 1 + " " + (ans.get(i + 1) + 1));
		}
    }
}
