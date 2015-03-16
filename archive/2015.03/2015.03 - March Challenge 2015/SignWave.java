package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class SignWave {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int sin = in.readInt();
		int cos = in.readInt();
		int need = in.readInt();
		long accounted = 2;
		long answer = 0;
		for (int i = 1; i <= 52; i++) {
			long current = (1L << i) + 1 - accounted;
			int through = Math.max(sin - i + 1, 0) + (i > 1 && i - 2 < cos ? 1 : 0);
			if (through >= need) {
				answer += current;
			}
			accounted += current;
		}
		if (sin >= need) {
			answer += 2;
		}
		out.printLine(answer);
    }
}
