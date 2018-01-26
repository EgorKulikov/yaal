package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class LittleElephantAndBombs {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        char[] bombs = in.readString().toCharArray();
        int answer = 0;
        for (int i = 0; i < count; i++) {
            if (bombs[i] == '0' && (i == 0 || bombs[i - 1] == '0') && (i == count - 1 || bombs[i + 1] == '0'))
                answer++;
        }
        out.printLine(answer);
	}
}
