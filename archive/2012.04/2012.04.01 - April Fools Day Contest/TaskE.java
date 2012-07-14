package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] s = "INTERCAL".toCharArray();
		int head = 0;
		out.printLine("DO ,1 <- #13");
		int index = 1;
		for (char c : s) {
			int requiredHead = 0;
			for (int i = 0; i < 8; i++) {
				if ((c >> i & 1) == 1)
					requiredHead += 1 << (7 - i);
			}
			int shift;
			if (head >= requiredHead)
				shift = head - requiredHead;
			else
				shift = head - requiredHead + 256;
			head = requiredHead;
			out.printLine("DO ,1SUB#" + index + " <- #" + shift);
			index++;
		}
		out.printLine("DO READ OUT ,1");
		out.printLine("PLEASE GIVE UP");
	}
}
