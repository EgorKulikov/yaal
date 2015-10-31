
package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskH {
	static final int BUBEN = 200;

	static class Town {
		Town[] quickParent;
		int[] costEach = new int[BUBEN];
		int depth;
		int fuelCost;
		List<Town> adj = new ArrayList<>();

		public void makeRoot(Town parent) {
			if (parent != null) depth = 1 + parent.depth; else depth = 0;
			int cnt = 0;
			while ((1 << cnt) <= depth) ++cnt;
			quickParent = new Town[cnt];
			for (int i = 0; i < cnt; ++i) {
				if (i == 0) quickParent[i] = parent; else
					quickParent[i] = quickParent[i - 1].quickParent[i - 1];
			}
			for (Town t : adj) if (t != parent) t.makeRoot(this);
		}

		public void initEach(Town[] stack) {
			for (int each = 1; each < BUBEN; ++each) {
				costEach[each] = fuelCost;
				if (depth >= each) {
					costEach[each] += stack[depth - each].costEach[each];
				}
			}
			stack[depth] = this;
			for (Town t : adj) if (t.depth == depth + 1) t.initEach(stack);
		}
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		Town[] towns = new Town[n];
		for (int i = 0; i < n; ++i) {
			towns[i] = new Town();
			towns[i].fuelCost = in.readInt();
		}
		for (int i = 0; i < n - 1; ++i) {
			Town a = towns[in.readInt() - 1];
			Town b = towns[in.readInt() - 1];
			a.adj.add(b);
			b.adj.add(a);
		}

		Town root = towns[0];
		root.makeRoot(null);
		Town[] stack = new Town[n];
		root.initEach(stack);

		Town[] seq = new Town[n];
		for (int i = 0; i < n; ++i) seq[i] = towns[in.readInt() - 1];

		int[] each = new int[n - 1];
		for (int i = 0; i < n - 1; ++i) each[i] = in.readInt();

		for (int si = 0; si < n - 1; ++si) {
			Town a = seq[si];
			Town b = seq[si + 1];
			Town ta = a;
			Town tb = b;
			ta = walkUp(ta, Math.max(0, a.depth - b.depth));
			tb = walkUp(tb, Math.max(0, b.depth - a.depth));
			if (ta.depth != tb.depth) throw new RuntimeException();
			int maxJump = ta.quickParent.length - 1;
			for (int ji = maxJump; ji >= 0; --ji) {
				if (ji < ta.quickParent.length && ta.quickParent[ji] != tb.quickParent[ji]) {
					ta = ta.quickParent[ji];
					tb = tb.quickParent[ji];
				}
			}
			Town lca;
			if (ta == tb) lca = ta; else lca = ta.quickParent[0];
			int ea = each[si];
			int areachAt = (ea - (a.depth - lca.depth) % ea) % ea;
			int breachAt = (ea - (b.depth - lca.depth) % ea) % ea;
			if (ea < BUBEN) {
				int res = a.costEach[ea] + b.costEach[ea];
				Town suba = walkUp(lca, areachAt);
				if (suba != null) res -= suba.costEach[ea];
				Town subb = walkUp(lca, breachAt);
				if (subb != null) res -= subb.costEach[ea];
				if (areachAt == 0) res += lca.fuelCost;
				out.printLine(res);
			} else {
				int res = 0;
				if (areachAt == 0) res += lca.fuelCost;
				while (a != null && a.depth > lca.depth) {
					res += a.fuelCost;
					a = walkUp(a, ea);
				}
				while (b != null && b.depth > lca.depth) {
					res += b.fuelCost;
					b = walkUp(b, ea);
				}
				out.printLine(res);
			}
		}
	}

	private Town walkUp(Town a, int by) {
		if (by > a.depth) return null;
		while (by > 0) {
			int z = Integer.numberOfTrailingZeros(by);
			a = a.quickParent[z];
			by ^= 1 << z;
		}
		return a;
	}
}
