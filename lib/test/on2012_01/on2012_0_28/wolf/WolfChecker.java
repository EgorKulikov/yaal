package on2012_01.on2012_0_28.wolf;



import net.egork.chelper.task.Test;
import net.egork.chelper.tester.Verdict;
import net.egork.collections.sequence.Array;
import net.egork.utils.io.InputReader;

import java.util.*;

public class WolfChecker {
	public Verdict check(InputReader input, InputReader expected, InputReader actual) {
		if (actual.peek() == 'I')
			return Verdict.OK;
		Set<String> left = new HashSet<String>(Array.wrap(input.readLine(false).split(" ")));
		Set<String> right = new HashSet<String>(Array.wrap(input.readLine(false).split(" ")));
		if (left.size() == 4)
			right = new HashSet<String>();
		else if (right.size() == 4)
			left = new HashSet<String>();
		while (!actual.isExhausted()) {
			if (left.contains("Goat") && !left.contains("Farmer") && left.size() > 1)
				return Verdict.WA;
			if (right.contains("Goat") && !right.contains("Farmer") && right.size() > 1)
				return Verdict.WA;
			String action = actual.readString();
			if (left.contains("Farmer")) {
				if (!action.equals("Self")) {
					if (!left.contains(action))
						return Verdict.WA;
					left.remove(action);
					right.add(action);
				}
				left.remove("Farmer");
				right.add("Farmer");
			} else {
				if (!action.equals("Self")) {
					if (!right.contains(action))
						return Verdict.WA;
					right.remove(action);
					left.add(action);
				}
				right.remove("Farmer");
				left.add("Farmer");
			}
		}
		if (left.size() != 0)
			return Verdict.WA;
		return Verdict.OK;
	}

	public double getCertainty() {
		return 0;
	}

	public Collection<? extends Test> generateTests() {
		List<Test> tests = new ArrayList<Test>();
		for (int i = 0; i < 16; i++) {
			String first = "";
			String second = "";
			if ((i & 1) == 0)
				first += "Farmer ";
			else
				second += "Farmer ";
			if ((i & 2) == 0)
				first += "Wolf ";
			else
				second += "Wolf ";
			if ((i & 4) == 0)
				first += "Goat ";
			else
				second += "Goat ";
			if ((i & 8) == 0)
				first += "Cabbage ";
			else
				second += "Cabbage ";
			tests.add(new Test(first.trim() + "\n" + second.trim(), "", 0));
		}
		return tests;
	}
}
