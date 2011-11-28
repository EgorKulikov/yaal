package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ASCII {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		int answer = 0;
		boolean inside = false;
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (in.readCharacter() == '.') {
					if (inside)
						answer += 2;
				} else {
					answer++;
					inside = !inside;
				}
			}
		}
		out.printLine(answer / 2);
	}
}
