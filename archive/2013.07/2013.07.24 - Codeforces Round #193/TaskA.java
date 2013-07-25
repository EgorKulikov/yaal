package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		char[] moves = in.readString().toCharArray();
		int answer = 0;
		for (int i = count; i < moves.length; i += count) {
			if (moves[i - 1] == moves[i - 2] && moves[i - 1] == moves[i - 3])
				answer++;
		}
		out.printLine(answer);
    }
}
