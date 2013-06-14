package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Interactive {
	static class Tree {
		int x;
		int y;
		int height;
		Tree left;
		Tree right;
	}

	static class TreePair {
		Tree left;
		Tree right;
	}

	int getHeight(Tree what) {
		if (what == null)
			return 0;
		return what.height;
	}

	TreePair split(Tree what, int by) {
		if (what == null) {
			TreePair res = new TreePair();
			return res;
		}
		if (what.x < by) {
			TreePair res = split(what.right, by);
			what.right = res.left;
			updateHeight(what);
			res.left = what;
			return res;
		} else {
			TreePair res = split(what.left, by);
			what.left = res.right;
			updateHeight(what);
			res.right = what;
			return res;
		}
	}

	private void updateHeight(Tree what) {
		what.height = 1 + Math.max(getHeight(what.left), getHeight(what.right));
	}

	Tree merge(Tree left, Tree right) {
		if (left == null) return right;
		if (right == null) return left;
		if (left.y > right.y) {
			left.right = merge(left.right, right);
			updateHeight(left);
			return left;
		} else {
			right.left = merge(left, right.left);
			updateHeight(right);
			return right;
		}
	}

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] x = new int[n];
		int[] y = new int[n];
		for (int i = 0; i < n; ++i) {
			x[i] = in.readInt();
		}
		for (int i = 0; i < n; ++i) {
			y[i] = in.readInt();
		}
		Tree res = null;
		for (int i = 0; i < n; ++i) {
			TreePair pair = split(res, x[i]);
			Tree nxt = new Tree();
			nxt.x = x[i];
			nxt.y = y[i];
			updateHeight(nxt);
			res = merge(pair.left, merge(nxt, pair.right));
			if (i > 0) out.print(" ");
			out.print(res.height - 1);
		}
		out.printLine();
    }
}
