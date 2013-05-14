package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class PleasingTheMartianKing {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int ones = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		int answer = 0;
		for (int i = 0; i < count - ones; i++)
			answer -= array[i];
		for (int i = count - ones; i < count; i++)
			answer += array[i];
		out.printLine(answer);
	}
}
