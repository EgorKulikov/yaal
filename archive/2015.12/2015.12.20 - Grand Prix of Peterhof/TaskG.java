package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskG {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		in = new InputReader(System.in);
		for (int i = 2;; i++) {
			System.out.println(i);
			System.out.flush();
			boolean ok = in.readString().equals("yes");
			if (!ok) {
				continue;
			}
			System.out.println(1);
			ok = in.readString().equals("yes");
			if (ok) {
				break;
			}
		}
	}
}
