package net.egork;

import net.egork.generated.collections.list.CharArrayList;
import net.egork.generated.collections.list.CharList;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] s = in.readString().toCharArray();
		CharList stack = new CharArrayList(s.length);
		int answer = 0;
		char[] opening = new char[256];
		opening['>'] = '<';
		opening[']'] = '[';
		opening[')'] = '(';
		opening['}'] = '{';
		for (char c : s) {
			if (opening[c] == 0) {
				stack.add(c);
			} else {
				if (stack.isEmpty()) {
					out.printLine("Impossible");
					return;
				} else if (stack.last() != opening[c]) {
					answer++;
				}
				stack.popLast();
			}
		}
		if (!stack.isEmpty()) {
			out.printLine("Impossible");
			return;
		}
		out.printLine(answer);
	}
}
