package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		String[] channels = IOUtils.readStringArray(in, count);
		int pointer = 0;
		StringBuilder answer = new StringBuilder();
		while (!channels[pointer].equals("BLJTV1")) {
			pointer++;
			answer.append(1);
		}
		for (; pointer != 0; pointer--) {
			channels[pointer] = channels[pointer - 1];
			answer.append(4);
		}
		while (!channels[pointer].equals("BLJTV2")) {
			pointer++;
			answer.append(1);
		}
		for (; pointer != 1; pointer--) {
			channels[pointer] = channels[pointer - 1];
			answer.append(4);
		}
		out.printLine(answer);
	}
}
