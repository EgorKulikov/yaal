package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int height = in.readInt();
		Node root = null;
		List<Node> allNodes = new ArrayList<Node>();
		NavigableSet<Node> ordered = new TreeSet<Node>();
		for (int i = 0; i < height; i++) {
			while (true) {
				while (!Character.isDigit(in.peek()))
					in.read();
				int value = 0;
				while (Character.isDigit(in.peek())) {
					value *= 10;
					value += in.read() - '0';
				}
				Node node = new Node(value, i);
				if (root == null) {
					root = node;
				} else {
					NavigableSet<Node> head = ordered.headSet(node, false);
					if (ordered.first().value < value && head.last().level == i - 1)
						head.last().add(node);
					else
						ordered.tailSet(node, false).first().add(node);
				}
				allNodes.add(node);
				ordered.add(node);
				int space = in.read();
				if (space != ' ')
					break;
			}
		}
		for (Node node : allNodes) {
			out.print(node.value + ":");
			if (node.left != null)
				out.print(node.left.value);
			out.print("-");
			if (node.right != null)
				out.print(node.right.value);
			out.printLine();
		}
	}

	static class Node implements Comparable<Node> {
		final int value;
		final int level;
		Node left, right;

		Node(int value, int level) {
			this.value = value;
			this.level = level;
		}

		void add(Node node) {
			if (node.value > value)
				right = node;
			else
				left = node;
		}

		public int compareTo(Node o) {
			return value - o.value;
		}
	}
}
