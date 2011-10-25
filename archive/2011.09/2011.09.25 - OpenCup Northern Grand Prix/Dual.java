import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Dual implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int n = in.readInt();
		List<Node> roots = new ArrayList<Node>();
		Node[] v = new Node[n];
		for (int i = 0; i < n; i++) {
			v[i] = new Node();
			v[i].id = i;
		}
		for (int i = 0; i < n; i++) {
			int p = in.readInt();
			if (p == 0) {
				roots.add(v[i]);
			} else {
				v[p - 1].children.add(v[i]);
			}
		}
		PriorityQueue<Node>[] pq = new PriorityQueue[2];
		for (int i = 0; i < 2; i++) {
			pq[i] = new PriorityQueue<Node>();
		}
		for (Node root : roots) {
			root.dfs();
			pq[0].add(root);
			pq[1].add(root);
		}

		List<Node>[] wait = new List[2];
		List<Node>[] nextWait = new List[2];
		for (int i = 0; i < 2; i++) {
			wait[i] = new ArrayList<Node>();
			nextWait[i] = new ArrayList<Node>();
		}

		int s = 0;
		List<String> res = new ArrayList<String>();
		while (true) {
			pq[0].addAll(nextWait[0]);
			pq[1].addAll(nextWait[1]);
			nextWait[0].clear();
			nextWait[1].clear();
			for (int i = 0; i < 2; i++) {
				while (!pq[i].isEmpty() && pq[i].peek().done)
					pq[i].poll();
			}
			int[] d = new int[2];
			int[] order = new int[2];
			order[1] = 1;
			if (!pq[0].isEmpty() && !pq[1].isEmpty() && pq[0].peek() == pq[1].peek()) {
				Node node = pq[0].poll();
				pq[1].poll();
				for (int i = 0; i < 2; i++) {
					while (!pq[i].isEmpty() && pq[i].peek().done)
						pq[i].poll();
				}
				pq[0].add(node);
				pq[1].add(node);
			}
			if (pq[1].size() < pq[0].size()) {
				order[1] = 0;
				order[0] = 1;
			}
			for (int i : order) {
				Node node = null;
				while (!pq[i].isEmpty()) {
					node = pq[i].poll();
					if (node.done) {
						node = null;
						continue;
					}
					break;
				}
				if (node == null) {
					continue;
				}
				d[i] = node.id + 1;
				node.done = true;
				s++;
				for (Node child : node.children) {
					pq[i].add(child);
					nextWait[1 - i].add(child);
				}
			}
			res.add(d[0] + " " + d[1]);
			if (s == n) break;
			List<Node>[] t = wait;
			wait = nextWait;
			nextWait = t;
		}
		out.println(res.size());
		for (String re : res) {
			out.println(re);
		}
	}

	class Node implements Comparable<Node> {
		int id;
		boolean done;
		int subtreeSize;
		List<Node> children = new ArrayList<Node>();

		void dfs() {
			subtreeSize++;
			for (Node n : children) {
				n.dfs();
				subtreeSize += n.subtreeSize;
			}
		}

		public int compareTo(Node o) {
//			int delta = o.children.size() - children.size();
//			if (delta != 0)
//				return delta;
			return o.subtreeSize - subtreeSize;
		}
	}
}

