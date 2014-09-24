package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ReachThePoint {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int row = Math.abs(in.readInt());
		int column = Math.abs(in.readInt());
		int answer = 2 * Math.max(row, column);
		if ((row + column) % 2 == 1) {
			if (row > column) {
				answer++;
			} else {
				answer--;
			}
		}
		out.printLine(answer);
    }
}
