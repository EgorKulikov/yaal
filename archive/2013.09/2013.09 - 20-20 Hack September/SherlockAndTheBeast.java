package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class SherlockAndTheBeast {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int threes = 0;
		while (count % 3 != 0) {
			if (count < 5) {
				out.printLine(-1);
				return;
			}
			threes += 5;
			count -= 5;
		}
		for (int i = 0; i < count; i++)
			out.print(5);
		for (int i = 0; i < threes; i++)
			out.print(3);
		out.printLine();
    }
}
