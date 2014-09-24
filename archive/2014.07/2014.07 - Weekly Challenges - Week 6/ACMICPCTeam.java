package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ACMICPCTeam {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int topicCount = in.readInt();
		char[][] know = IOUtils.readTable(in, count, topicCount);
		int answer = 0;
		int qty = 0;
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < i; j++) {
				int current = 0;
				for (int k = 0; k < topicCount; k++) {
					if (know[i][k] == '1' || know[j][k] == '1') {
						current++;
					}
				}
				if (current > answer) {
					answer = current;
					qty = 1;
				} else if (current == answer) {
					qty++;
				}
			}
		}
		out.printLine(answer);
		out.printLine(qty);
	}
}
