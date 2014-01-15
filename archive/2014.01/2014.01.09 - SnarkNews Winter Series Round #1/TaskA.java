package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] message = in.readString().toCharArray();
		int[] qty = new int[256];
		for (int i = 0; i < message.length; i++) {
			qty[message[i]]++;
			if (qty[message[i]] % 3 == 0) {
				if (i == message.length - 1 || message[i + 1] != message[i]) {
					out.printLine("FAKE");
					return;
				}
				i++;
			}
		}
		out.printLine("OK");
    }
}
