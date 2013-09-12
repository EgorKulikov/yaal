package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskH {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int length = in.readInt();
		char[] message = new char[length];
		for (int i = 0; i < length; i++)
			message[i] = in.readCharacter();
		char[] crib = in.readString().toCharArray();
		char[] decoded = new char[length];
		for (int i = 1; i < 26; i++) {
			for (int j = 5; j <= 20; j++) {
				for (int k = 0; k < length; k += j) {
					for (int l = k, m = Math.min(k + j - 1, length - 1); l <= m; l++, m--) {
						decoded[m] = decode(message[l], i);
						decoded[l] = decode(message[m], i);
					}
				}
				for (int k = 0; k <= length - crib.length; k++) {
					boolean found = true;
					for (int l = 0; l < crib.length; l++) {
						if (crib[l] != decoded[k + l]) {
							found = false;
							break;
						}
					}
					if (found) {
						out.printLine(i, j);
						return;
					}
				}
			}
		}
		out.printLine("Crib is not encrypted.");
    }

	final char decode(char ch, int shift) {
		return (char) ((ch + 26 - shift - 'A') % 26 + 'A');
	}
}
