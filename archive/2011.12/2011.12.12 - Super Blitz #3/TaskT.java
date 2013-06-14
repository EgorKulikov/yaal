package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskT {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		String[] surname = new String[count];
		String[] name = new String[count];
		long[] average = new long[count];
		for (int i = 0; i < count; i++) {
			surname[i] = in.readString();
			name[i] = in.readString();
			int markCount = in.readInt();
			for (int j = 0; j < markCount; j++)
				average[i] += in.readInt();
			average[i] /= markCount;
		}
		int required = in.readInt();
		for (int i = 0; i < count; i++) {
			if (average[i] >= required)
				out.printLine(surname[i], name[i]);
		}
	}
}
