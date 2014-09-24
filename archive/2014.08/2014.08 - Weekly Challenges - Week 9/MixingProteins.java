package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class MixingProteins {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int length = in.readInt();
		int count = in.readInt();
		char[] protein = IOUtils.readCharArray(in, length);
		char[] next = new char[length];
		for (int i = 0; i < length; i++) {
			protein[i] -= 'A';
		}
		for (int i = 29; i >= 0; i--) {
			if (count >= (1 << i)) {
				int other = (1 << i) % length;
				for (int j = 0; j < length; j++) {
					next[j] = (char) (protein[j] ^ protein[other]);
					other++;
					if (other == length) {
						other = 0;
					}
				}
				char[] temp = protein;
				protein = next;
				next = temp;
				count -= 1 << i;
			}
		}
		for (int i = 0; i < length; i++) {
			protein[i] += 'A';
		}
		out.printLine(protein);
    }
}
