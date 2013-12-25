package net.egork;

import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class Patterns {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		String first = in.readString();
		String second = in.readString();
		if (!first.contains("*")) {
			noAsterik(out, first, second);
			return;
		}
		if (!second.contains("*")) {
			noAsterik(out, second, first);
			return;
		}
		int prefixLength = Math.min(first.indexOf('*'), second.indexOf('*'));
		int suffixLength = Math.min(first.length() - 1 - first.lastIndexOf('*'), second.length() - 1 - second.lastIndexOf('*'));
		if (!first.substring(0, prefixLength).equals(second.substring(0, prefixLength))) {
			out.printLine("Impossible");
			return;
		}
		if (!first.substring(first.length() - suffixLength).equals(second.substring(second.length() - suffixLength))) {
			out.printLine("Impossible");
			return;
		}
		if (first.charAt(prefixLength) == '*' && second.charAt(second.length() - suffixLength - 1) == '*') {
			differentSides(out, first, second, prefixLength, suffixLength);
			return;
		}
		if (second.charAt(prefixLength) == '*' && first.charAt(first.length() - suffixLength - 1) == '*') {
			differentSides(out, second, first, prefixLength, suffixLength);
			return;
		}
		if (first.charAt(prefixLength) == '*') {
			same(out, first, second, prefixLength, suffixLength);
			return;
		}
		same(out, second, first, prefixLength, suffixLength);
    }

	private void same(OutputWriter out, String first, String second, int prefixLength, int suffixLength) {
		int at = second.indexOf('*');
		String answer = second.substring(0, at) + first.substring(prefixLength + 1, first.length() - suffixLength - 1) + second.substring(at + 1);
		answer = answer.replace("*", "");
		output(out, answer);
	}

	private void output(OutputWriter out, String answer) {
		if (answer.isEmpty())
			out.printLine("nunafigbylotakdelat");
		else
			out.printLine(answer);
	}

	private void differentSides(OutputWriter out, String first, String second, int prefixLength, int suffixLength) {
		String answer = second.substring(0, second.length() - suffixLength - 1) + first.substring(prefixLength + 1);
		answer = answer.replace("*", "");
		output(out, answer);
	}

	private void noAsterik(OutputWriter out, String first, String second) {
		int prefixLength = second.indexOf('*');
		if (prefixLength == -1) {
			if (first.equals(second)) {
				out.printLine(first);
			} else {
				out.printLine("Impossible");
			}
			return;
		}
		int suffixLength = second.length() - 1 - second.lastIndexOf('*');
		if (!first.substring(0, prefixLength).equals(second.substring(0, prefixLength)) ||
			!first.substring(first.length() - suffixLength).equals(second.substring(second.length() - suffixLength)))
		{
			out.printLine("Impossible");
			return;
		}
		String toMatch = first.substring(prefixLength, first.length() - suffixLength);
		List<String> fragments = new ArrayList<String>();
		StringBuilder current = new StringBuilder();
		for (int i = prefixLength; i < second.length() - suffixLength; i++) {
			if (second.charAt(i) == '*') {
				if (current.length() != 0) {
					fragments.add(current.toString());
					current = new StringBuilder();
				}
			} else
				current.append(second.charAt(i));
		}
		int position = prefixLength;
		for (String fragment : fragments) {
			int[][] automata = StringUtils.buildPrefixAutomaton(fragment);
			int at = 0;
			while (position < first.length() - suffixLength && at < fragment.length()) {
				at = automata[at][first.charAt(position++) - 'a'];
			}
			if (at != fragment.length()) {
				out.printLine("Impossible");
				return;
			}
		}
		out.printLine(first);
	}
}
