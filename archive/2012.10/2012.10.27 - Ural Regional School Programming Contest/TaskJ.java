package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskJ {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int teddyCount = in.readInt();
		boolean[] isTeddy = new boolean[count];
		for (int i = 0; i < teddyCount; i++)
			isTeddy[in.readInt() - 1] = true;
		int answer = 0;
		for (int i = 1; i < count; i++) {
			for (int j = i + 1; j < count; j++) {
				int totalTeddy = 0;
				if (isTeddy[0])
					totalTeddy++;
				if (isTeddy[i])
					totalTeddy++;
				if (isTeddy[j])
					totalTeddy++;
				if (totalTeddy != 0 && (teddyCount - totalTeddy) * 3 >= count - 3)
					answer++;
			}
		}
		out.printLine(answer);
	}
}
