package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class ChefAndFeedback {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		String feedback = in.readString();
		if (feedback.contains("010") || feedback.contains("101"))
			out.printLine("Good");
		else
			out.printLine("Bad");
    }
}
