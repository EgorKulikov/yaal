package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskE {
	int start;
	String document;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		document = in.readString();
		List<Node> roots = new ArrayList<Node>();
		while (start != document.length())
			roots.add(build());
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			String[] tags = in.readLine().split(" ");
			int result = 0;
			for (Node root : roots)
				result += root.count(tags, 0);
			out.printLine(result);
		}
	}

	private Node build() {
		String tag = null;
		for (int i = start + 1; ; i++) {
			if (document.charAt(i) == '>') {
				tag = document.substring(start + 1, i);
				start = i + 1;
				break;
			}
		}
		if (tag.charAt(tag.length() - 1) == '/')
			return new Node(tag.substring(0, tag.length() - 1));
		Node result = new Node(tag);
		while (document.charAt(start + 1) != '/')
			result.addChild(build());
		start += tag.length() + 3;
		return result;
	}

	static class Node {
		final String tag;
		List<Node> children;

		Node(String tag) {
			this.tag = tag;
		}

		Node addChild(Node node) {
			if (children == null)
				children = new ArrayList<Node>();
			children.add(node);
			return node;
		}

		public int count(String[] tags, int position) {
			int result = 0;
			if (tags[position].equals(tag)) {
				if (position == tags.length - 1)
					result++;
				else
					position++;
			}
			if (children != null) {
				for (Node node : children)
					result += node.count(tags, position);
			}
			return result;
		}
	}
}
