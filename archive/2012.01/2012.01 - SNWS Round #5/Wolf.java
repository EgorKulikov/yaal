package net.egork;

import net.egork.collections.sequence.Array;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Wolf {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		Set<String> left = new HashSet<String>(Array.wrap(in.readLine(false).split(" ")));
		Set<String> right = new HashSet<String>(Array.wrap(in.readLine(false).split(" ")));
		if (left.size() == 4)
			right = new HashSet<String>();
		else if (right.size() == 4)
			left = new HashSet<String>();
		if (bad(left) || bad(right)) {
			out.printLine("Impossible");
			return;
		}
		List<String> answer = new ArrayList<String>();
		if (left.size() == 4) {
			left.remove("Farmer");
			left.remove("Goat");
			right.add("Farmer");
			right.add("Goat");
			answer.add("Goat");
		}
		if (right.size() == 2 && right.contains("Goat")) {
			right.remove("Farmer");
			left.add("Farmer");
			answer.add("Self");
		}
		if (right.size() == 1 && right.contains("Goat")) {
			left.remove("Wolf");
			right.add("Wolf");
			left.remove("Farmer");
			right.add("Farmer");
			answer.add("Wolf");
		}
		if (right.size() == 3 && right.contains("Goat")) {
			right.remove("Farmer");
			right.remove("Goat");
			left.add("Farmer");
			left.add("Goat");
			answer.add("Goat");
		}
		if (left.size() == 3) {
			if (left.contains("Wolf")) {
				left.remove("Wolf");
				right.add("Wolf");
				answer.add("Wolf");
			} else {
				left.remove("Cabbage");
				right.add("Cabbage");
				answer.add("Cabbage");
			}
			left.remove("Farmer");
			right.add("Farmer");
		}
		if (left.size() == 1) {
			right.remove("Farmer");
			left.add("Farmer");
			answer.add("Self");
		}
		if (left.size() == 2) {
			left.remove("Farmer");
			left.remove("Goat");
			right.add("Farmer");
			right.add("Goat");
			answer.add("Goat");
		}
		if (!left.isEmpty())
			throw new RuntimeException();
		for (String action : answer)
			out.printLine(action);
	}

	private boolean bad(Set<String> side) {
		return side.contains("Goat") && !side.contains("Farmer") && side.size() != 1;
	}
}
