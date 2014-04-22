package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ANeedleInTheHaystack {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] target = in.readString().toCharArray();
		char[] sample = in.readString().toCharArray();
		int[] qty = new int[256];
		int nonZero = 0;
		for (char c : target) {
			if (qty[c] == 0)
				nonZero++;
			qty[c]++;
		}
		for (int i = 0; i < target.length - 1; i++) {
			qty[sample[i]]--;
			if (qty[sample[i]] == 0)
				nonZero--;
		}
		for (int i = target.length - 1; i < sample.length; i++) {
			qty[sample[i]]--;
			if (qty[sample[i]] == 0)
				nonZero--;
			if (nonZero == 0) {
				out.printLine("YES");
				return;
			}
			if (qty[sample[i - target.length + 1]]++ == 0)
				nonZero++;
		}
		out.printLine("NO");
    }
}
