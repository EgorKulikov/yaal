package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class NewRockPaperScissors {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		out.printLine("Case #" + testNumber + ":");
		int count = in.readInt();
		char[] players = IOUtils.readCharArray(in, count);
		int[] result = new int[3];
		result[0] = ArrayUtils.count(players, 'R');
		result[1] = ArrayUtils.count(players, 'S');
		result[2] = ArrayUtils.count(players, 'P');
		int answer = 0;
		int defeated = 0;
		for (int i = 0; i < 3; i++) {
			if (result[i] == 0) {
				continue;
			}
			int next = result[(i + 1) % 3];
			int prev = result[(i + 2) % 3];
			if (next > defeated) {
				defeated = next;
				answer = result[i];
			} else if (next == defeated) {
				answer += result[i];
			}
		}
		out.printLine(answer);
    }
}
