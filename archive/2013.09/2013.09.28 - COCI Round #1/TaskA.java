package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] qty = new int[26];
		for (int i = 0; i < count; i++) {
			String name = in.readString();
			qty[name.charAt(0) - 'a']++;
		}
		boolean shown = false;
		for (int i = 0; i < 26; i++) {
			if (qty[i] >= 5) {
				out.print((char)(i + 'a'));
				shown = true;
			}
		}
		if (!shown)
			out.print("PREDAJA");
		out.printLine();
    }
}
