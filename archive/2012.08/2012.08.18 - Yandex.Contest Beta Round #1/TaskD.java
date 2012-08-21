package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        String die = in.readString();
        int required = in.readInt();
        die = die.replace('d', ' ').replace('+', ' ');
        String[] tokens = die.split(" ");
        int throwsCount = Integer.parseInt(tokens[0]);
        int diceSize = Integer.parseInt(tokens[1]);
        required -= Integer.parseInt(tokens[2]);
        required -= throwsCount;
        if (required < 0 || required >= throwsCount * diceSize) {
            out.printLine("0.00");
            return;
        }
        double[][] probability = new double[throwsCount + 1][diceSize * throwsCount];
        probability[0][0] = 100;
        for (int i = 0; i < throwsCount; i++) {
            for (int j = 0; j <= i * diceSize; j++) {
                for (int k = 0; k < diceSize; k++)
                    probability[i + 1][j + k] += probability[i][j] / diceSize;
            }
        }
        out.printFormat("%.2f\n", probability[throwsCount][required]);
	}
}
