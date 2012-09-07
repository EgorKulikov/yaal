package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Set;
import java.util.TreeSet;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		Set<String> words = new TreeSet<String>();
		StringBuilder current = new StringBuilder();
		int c;
		while ((c = in.read()) != -1) {
			if (Character.isLetter(c))
				current.append(Character.toLowerCase((char)c));
			else if (current.length() > 0) {
				words.add(current.toString());
				current = new StringBuilder();
			}
		}
		for (String word : words)
			out.printLine(word);
	}
}
