package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class AllCritical {
	long[][] c = IntegerUtils.generateBinomialCoefficients(21);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		double probability = in.readDouble();
		double[] answer = new double[21];
		for (int i = 1; i <= 20; i++) {
			for (int j = 0; j < i; j++) {
				answer[i] += answer[j] * c[i][j] * Math.pow(probability, i - j) * Math.pow(1 - probability, j);
			}
			answer[i]++;
			answer[i] /= 1 - Math.pow(1 - probability, i);
		}
		out.printLine("Case #" + testNumber + ":", answer[20]);
    }
}
