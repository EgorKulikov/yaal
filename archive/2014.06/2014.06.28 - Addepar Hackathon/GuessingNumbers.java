package net.egork;

import net.egork.io.IOUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Arrays;

public class GuessingNumbers {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int guesses = in.readInt();
		double[] probabilities = IOUtils.readDoubleArray(in, count);
		Arrays.sort(probabilities);
		double answer = 0;
		for (int i = count - 1; i >= Math.max(0, count - (1 << guesses) + 1); i--) {
			answer += probabilities[i];
		}
		out.printFormat("%.8f\n", answer);
    }
}
