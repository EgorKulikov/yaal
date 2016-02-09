package net.egork;

import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] password = in.readString().toCharArray();
		long answer = 0;
		for (int i = 0; i < 2; i++) {
			long current = 1;
			for (int j = 0; j < password.length; j++) {
				if (password[j] == '?') {
					if ((j & 1) == i) {
						current *= 6;
					} else {
						current *= 20;
					}
				} else if (MiscUtils.isVowel(password[j]) ^ ((j & 1) == i)) {
					current = 0;
				}
			}
			answer += current;
		}
		out.printLine(answer);
	}
}
