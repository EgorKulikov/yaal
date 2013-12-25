package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class PoloThePenguinAndTheXOR {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] numbers = IOUtils.readIntArray(in, count);
		int[] qty = new int[30];
		long answer = 0;
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < 30; j++) {
				if ((numbers[i] >> j & 1) == 1)
					qty[j] = i + 1 - qty[j];
				answer += ((long)qty[j]) << j;
			}
		}
		out.printLine(answer);
    }
}
