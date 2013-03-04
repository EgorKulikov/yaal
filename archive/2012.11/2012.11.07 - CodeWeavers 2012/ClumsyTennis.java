package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ClumsyTennis {
	int[] points = {0, 15, 30, 40};

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int sScore = 0;
		int cScore = 0;
		int sGames = 0;
		int cGames = 0;
		for (int i = 0; i < count; i++) {
			if (in.readCharacter() == 'S') {
				sScore++;
				if (sScore == 4) {
					sScore = cScore = 0;
					sGames++;
				}
			} else {
				cScore++;
				if (cScore == 4) {
					sScore = cScore = 0;
					cGames++;
				}
			}
		}
		out.print(sGames + "-" + cGames);
		if (sScore != 0 || cScore != 0)
			out.print("," + points[sScore] + "-" + points[cScore]);
		out.printLine();
	}
}
