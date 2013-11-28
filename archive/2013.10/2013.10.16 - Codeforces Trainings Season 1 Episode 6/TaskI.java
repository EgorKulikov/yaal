package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskI {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int length = in.readInt();
		char[] dna = IOUtils.readCharArray(in, length);
		int[] result = new int[2];
		for (int i = 0; i < length; i++) {
			if (dna[i] == 'A')
				result[1]++;
			else
				result[0]++;
			for (int j = 0; j < 2; j++)
				result[j] = Math.min(result[j], result[1 - j] + 1);
		}
		out.printLine(result[0]);
    }
}
