package on2011_12.on2011_11_16.taskd;



import net.egork.chelper.task.Test;
import net.egork.chelper.tester.Verdict;
import net.egork.utils.io.InputReader;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class TaskDChecker {
	public Verdict check(InputReader input, InputReader expected, InputReader actual) {
		int changes = expected.readInt();
		if (changes != actual.readInt())
			return Verdict.WA;
		char[] original = input.readString().toCharArray();
		int partCount = input.readInt();
		String[] parts = actual.readString().split("[+]");
		int index = 0;
		int actualChanges = 0;
		for (String part : parts) {
			for (char c : part.toCharArray()) {
				if (index == original.length)
					return Verdict.WA;
				if (c != original[index++])
					actualChanges++;
			}
		}
		if (actualChanges != changes || index != original.length)
			return Verdict.WA;
		for (String part : parts) {
			for (int i = 0; i < part.length(); i++) {
				if (part.charAt(i) != part.charAt(part.length() - i - 1))
					return Verdict.WA;
			}
		}
		return Verdict.OK;
	}

	public double getCertainty() {
		return 0;
	}

	public Collection<? extends Test> generateTests() {
		char[] s = new char[500];
		Arrays.fill(s, 'a');
		StringBuilder input = new StringBuilder();
		input.append(s).append("\n");
		input.append("500\n");
		StringBuilder output = new StringBuilder();
		output.append("0\n");
		output.append(s).append("\n");
		return Collections.singleton(new Test(input.toString(), output.toString(), 0));
	}
}
