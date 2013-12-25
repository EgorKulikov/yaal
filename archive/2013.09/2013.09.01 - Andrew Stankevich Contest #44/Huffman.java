package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Comparator;

public class Huffman {
	static class Node {
		Node zero;
		Node one;
		int id = -1;
		int u;
		int v;

		public void buildAnswer(String[] ans, StringBuilder b) {
			if (id >= 0) {
				ans[id] = b.toString();
				return;
			}
			b.append('0');
			zero.buildAnswer(ans, b);
			b.setCharAt(b.length() - 1, '1');
			one.buildAnswer(ans, b);
			b.deleteCharAt(b.length() - 1);
		}
	}

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		if (n == 0) throw new UnknownError();
		Node[] nodes = new Node[n];
		for (int i = 0; i < n; ++i) {
			nodes[i] = new Node();
			nodes[i].id = i;
			nodes[i].u = in.readInt();
			nodes[i].v = in.readInt();
		}
		while (nodes.length > 1) {
			Arrays.sort(nodes, new Comparator<Node>() {
				public int compare(Node o1, Node o2) {
					int z = o2.u + o2.v - o1.u - o1.v;
					if (z != 0)
						return z;
					return o2.u - o1.u;
				}
			});
			Node zero = nodes[0];
			Node one = null;
			for (int i = 1; i < nodes.length; ++i)
				if (nodes[i].u == zero.u - 1 && nodes[i].v == zero.v + 1) {
					one = nodes[i];
					break;
				}
			if (one == null) {
				out.printLine("No");
				return;
			}
			Node nxt = new Node();
			nxt.zero = zero;
			nxt.one = one;
			nxt.u = zero.u - 1;
			nxt.v = zero.v;
			Node[] newNodes = new Node[nodes.length - 1];
			int ptr = 0;
			for (Node x : nodes)
				if (x != zero && x != one) newNodes[ptr++] = x;
			newNodes[ptr++] = nxt;
			if (ptr != newNodes.length) throw new RuntimeException();
			nodes = newNodes;
		}
		if (nodes[0].u != 0 || nodes[0].v != 0) {
			out.printLine("No");
			return;
		}
		String[] ans = new String[n];
		StringBuilder b = new StringBuilder();
		nodes[0].buildAnswer(ans, b);
		out.printLine("Yes");
		for (String x : ans) out.printLine(x);
    }
}
