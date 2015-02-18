package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Autocomplete {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		String[] words = IOUtils.readStringArray(in, count);
		int answer = 0;
		Node root = new Node();
		for (String s : words) {
			Node current = root;
			boolean done = false;
			for (int i = 0; i < s.length(); i++) {
				int at = s.charAt(i) - 'a';
				if (current.children[at] == null) {
					if (!done) {
						done = true;
						answer += i + 1;
					}
					current.children[at] = new Node();
				}
				current = current.children[at];
			}
			if (!done) answer += s.length();
		}
		out.printLine("Case #" + testNumber + ":", answer);
    }

	static class Node {
		Node[] children = new Node[26];
	}
}
