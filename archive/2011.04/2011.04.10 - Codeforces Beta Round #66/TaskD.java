package April2011.CodeforcesBetaRound66;

import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;

public class TaskD implements Solver {
	private int[] root;
	private int[] size;
	private int componentCount;

	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		int k = in.readInt();
		root = new int[n];
		size = new int[n];
		componentCount = n;
		Arrays.fill(size, 1);
		for (int i = 0; i < n; i++)
			root[i] = i;
		for (int i = 0; i < m; i++) {
			int a = in.readInt() - 1;
			int b = in.readInt() - 1;
			join(a, b);
		}
		if (k == 1) {
			out.println(Math.max(0, componentCount - 2));
			return;
		}
		int totalWeight = 0;
		for (int i = 0; i < n; i++) {
			if (i == root[i])
				totalWeight += Math.min(k, size[i]);
		}
		int desiredComponentCount = totalWeight / 2 + 1;
		out.println(Math.max(0, componentCount - desiredComponentCount));
	}

	private void join(int a, int b) {
		a = get(a);
		b = get(b);
		if (a == b)
			return;
		componentCount--;
		root[b] = a;
		size[a] += size[b];
	}

	private int get(int v) {
		if (root[v] == v)
			return v;
		return root[v] = get(root[v]);
	}
}

