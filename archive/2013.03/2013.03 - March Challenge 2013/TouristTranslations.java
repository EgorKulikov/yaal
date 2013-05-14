package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TouristTranslations {
	char[] permutation = new char[256];

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		if (testNumber == 1) {
			char[] permutation = in.readString().toCharArray();
			for (int i = 0; i < 26; i++) {
				this.permutation['a' + i] = permutation[i];
				this.permutation['A' + i] = Character.toUpperCase(permutation[i]);
			}
			this.permutation['_'] = ' ';
		}
		char[] message = in.readString().toCharArray();
		for (int i = 0; i < message.length; i++) {
			if (permutation[message[i]] != 0)
				message[i] = permutation[message[i]];
		}
		out.printLine(message);
	}
}
