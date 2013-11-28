package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Empty {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int groupCount = in.readInt();
		int[] basic = new int[count];
		for (int i = 0; i < groupCount; i++) {
			int perCase = in.readInt();
			int caseCount = in.readInt();
			int a = in.readInt() % count;
			int b = in.readInt() % count;
			for (int j = 0; j < caseCount; j++) {
				b += a;
				if (b >= count)
					b -= count;
				basic[b] += perCase;
			}
		}
		int current = 0;
		for (int i = 0; i < count; i++) {
			current += basic[i];
			if (current > 0) {
				current--;
				basic[i] = 1;
			} else
				basic[i] = 0;
		}
		for (int i = 0; i < count; i++) {
			current += basic[i];
			if (current > 0) {
				current--;
				basic[i] = 1;
			} else
				basic[i] = 0;
		}
		for (int i = 0; i < count; i++) {
			if (basic[i] == 0) {
				out.printLine(i);
				return;
			}
		}
    }
}
