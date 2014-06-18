package net.egork;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long length = in.readLong();
		int part = in.readInt();
		if (length < part + 2) {
			out.printLine(0);
			return;
		}
		double answer = length - part - 1;
		for (int i = 0; i < part + 2; i++)
			answer /= 2;
		out.printLine(answer);
    }
}
