package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class PoloThePenguinAndTheTest {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int time = in.readInt();
		int[] contained = new int[count];
		int[] points = new int[count];
		int[] required = new int[count];
		IOUtils.readIntArrays(in, contained, points, required);
		int[] answer = new int[time + 1];
		for (int i = 0; i < count; i++) {
			for (int j = time; j >= required[i]; j--)
				answer[j] = Math.max(answer[j], answer[j - required[i]] + contained[i] * points[i]);
		}
		out.printLine(answer[time]);
    }
}
