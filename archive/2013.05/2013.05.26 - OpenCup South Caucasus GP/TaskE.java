package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TaskE {
	static final long INF = (long) 1e18;

	class Edge {
		Vertex a;
		Vertex b;
		boolean inTree;
		long w;
		long answer = INF;
	}

	class Chain {
		Vertex where;
		int maxAttached;

		long[] mins;
		long[] prefixUpdates;
		boolean answersFilled;

		public void init() {
			mins = new long[4 * maxAttached + 10];
			Arrays.fill(mins, INF);
			prefixUpdates = new long[maxAttached + 1];
			Arrays.fill(prefixUpdates, INF);
		}

		public void update(int left, int right, long weight) {
			if (left == 1) {
				prefixUpdates[right] = Math.min(prefixUpdates[right], weight);
			} else {
				updateTree(0, 1, maxAttached, left, right, weight);
			}
		}

		private void updateTree(int root, int rl, int rr, int left, int right, long weight) {
			if (left > right) return;
			if (rl == left && rr == right) {
				mins[root] = Math.min(mins[root], weight);
				return;
			}
			int rm = (rl + rr) / 2;
			updateTree(root * 2 + 1, rl, rm, left, Math.min(rm, right), weight);
			updateTree(root * 2 + 2, rm + 1, rr, Math.max(left, rm + 1), right, weight);
		}

		public void fillAnswers() {
			if (answersFilled) return;
			answersFilled = true;
			for (int i = maxAttached - 1; i >= 1; --i) {
				prefixUpdates[i] = Math.min(prefixUpdates[i], prefixUpdates[i + 1]);
			}
			fillTree(0, 1, maxAttached, INF);
		}

		private void fillTree(int root, int rl, int rr, long ans) {
			ans = Math.min(ans, mins[root]);
			if (rl == rr) {
				prefixUpdates[rl] = Math.min(prefixUpdates[rl], ans);
			} else {
				int rm = (rl + rr) / 2;
				fillTree(root * 2 + 1, rl, rm, ans);
				fillTree(root * 2 + 2, rm + 1, rr, ans);
			}
		}
	}

	class Vertex {
		Vertex mstParent;
		Chain attach;
		Edge treeEdge;
		int depth;
		int pos;
		int maxAttached;
		List<Edge> adj = new ArrayList<Edge>();
		int subtreeSize;

		Vertex getMstRoot() {
			if (mstParent == null) return this;
			mstParent = mstParent.getMstRoot();
			return mstParent;
		}

		public void buildSizes(Vertex parent) {
			subtreeSize = 1;
			for (Edge e : adj) {
				if (!e.inTree) continue;
				Vertex dest = (e.a == this) ? e.b : e.a;
				if (dest == parent) continue;
				dest.depth = depth + 1;
				dest.treeEdge = e;
				dest.buildSizes(this);
				subtreeSize += dest.subtreeSize;
			}
		}

		public void buildChains(Vertex parent, Chain chain, int chainPos) {
			attach = chain;
			pos = chainPos;
			if (attach.maxAttached != pos - 1) throw new RuntimeException();
			attach.maxAttached = pos;
			Vertex mxDest = null;
			for (Edge e : adj) {
				if (!e.inTree) continue;
				Vertex dest = (e.a == this) ? e.b : e.a;
				if (dest == parent) continue;
				if (mxDest == null || dest.subtreeSize > mxDest.subtreeSize) {
					mxDest = dest;
				}
			}
			if (mxDest != null) {
				mxDest.buildChains(this, chain, chainPos + 1);
			}
			for (Edge e : adj) {
				if (!e.inTree) continue;
				Vertex dest = (e.a == this) ? e.b : e.a;
				if (dest == parent) continue;
				if (dest == mxDest) continue;
				Chain newC = new Chain();
				newC.where = this;
				dest.buildChains(this, newC, 1);
			}
		}

	}

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		Vertex[] vs = new Vertex[n];
		for (int i = 0; i < n; ++i) {
			vs[i] = new Vertex();
		}
		Edge[] es = new Edge[m];
		for (int i = 0; i < m; ++i) {
			Edge e = new Edge();
			e.a = vs[in.readInt() - 1];
			e.b = vs[in.readInt() - 1];
			e.w = in.readLong();
			e.a.adj.add(e);
			e.b.adj.add(e);
			es[i] = e;
		}
		Edge[] esSorted = es.clone();
		Arrays.sort(esSorted, new Comparator<Edge>() {
			public int compare(Edge o1, Edge o2) {
				return Long.compare(o1.w, o2.w);
			}
		});
		int numInTree = 0;
		long treeWeight = 0;
		for (Edge e : esSorted) {
			Vertex ra = e.a.getMstRoot();
			Vertex rb = e.b.getMstRoot();
			if (ra == rb) continue;
			ra.mstParent = rb;
			e.inTree = true;
			treeWeight += e.w;
			++numInTree;
		}
		if (numInTree > n - 1) throw new RuntimeException();
		if (numInTree == n - 1) {
			Vertex root = vs[0];
			root.depth = 1;
			root.buildSizes(null);
			Vertex superroot = new Vertex();
			Chain superchain = new Chain();
			superchain.where = superroot;
			root.buildChains(null, superchain, 1);
			for (Vertex v : vs) if (v.attach.mins == null) v.attach.init();
			for (Edge e : es) {
				if (e.inTree) continue;
				e.answer = treeWeight;
				Vertex a = e.a;
				Vertex b = e.b;
				while (true) {
					if (a.attach == b.attach) {
						a.attach.update(Math.min(a.pos, b.pos) + 1, Math.max(a.pos, b.pos), e.w);
						break;
					} else {
						if (a.attach.where.depth > b.attach.where.depth) {
							a.attach.update(1, a.pos, e.w);
							a = a.attach.where;
						} else {
							b.attach.update(1, b.pos, e.w);
							b = b.attach.where;
						}
					}
				}
			}
			for (Vertex v : vs) v.attach.fillAnswers();
			for (Vertex v : vs) {
				if (v != root) {
					v.treeEdge.answer = treeWeight - v.treeEdge.w + v.attach.prefixUpdates[v.pos];
				}
			}
		}
		for (Edge e : es) {
			out.printLine(e.answer >= INF ? -1L : e.answer);
		}
    }
}
