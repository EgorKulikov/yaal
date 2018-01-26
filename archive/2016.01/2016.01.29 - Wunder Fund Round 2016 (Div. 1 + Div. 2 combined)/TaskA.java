package net.egork;

import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.string.StringUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		String s = StringUtils.reverse(Integer.toBinaryString(n));
		IntList answer = new IntArrayList();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '1') {
				answer.add(i + 1);
			}
		}
		answer.inPlaceReverse();
		out.printLine(answer);
	}
}
