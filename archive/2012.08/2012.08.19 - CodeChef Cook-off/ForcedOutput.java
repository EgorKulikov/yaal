package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ForcedOutput {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		boolean[][] answers = new boolean[count][count];
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++)
				answers[i][j] = in.readString().equals("YES");
		}
		for (int i = 0; i < count; i++) {
			boolean good = true;
			for (int j = 0; j < count; j++) {
				boolean same = true;
				for (int k = 0; k < count; k++) {
					if (answers[i][k] != answers[j][k]) {
						same = false;
						break;
					}
				}
				if (answers[i][j] != same) {
					good = false;
					break;
				}
			}
			if (good) {
				for (int j = 0; j < count; j++) {
					if (answers[i][j])
						out.printLine("YES");
					else
						out.printLine("NO");
				}
				return;
			}
		}
		for (int i = 0; i < count; i++)
			out.printLine("NO");
	}
}
