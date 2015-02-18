package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int time = in.readInt();
        int[] probability = new int[count];
        int[] certain = new int[count];
        IOUtils.readIntArrays(in, probability, certain);
        double[] current = new double[time + 1];
        double[] delta = new double[time + 1];
        current[0] = 1;
        double answer = 0;
        for (int i = 0; i < count; i++) {
            Arrays.fill(delta, 0);
            double total = 0;
            double incorrect = 1 - probability[i] / 100d;
            double remaining = Math.pow(incorrect, certain[i] - 1);
            for (int j = 0; j <= time; j++) {
                total -= delta[j];
                double result = delta[j];
                result += total * (1 - incorrect);
                total *= incorrect;
                if (j + certain[i] <= time) {
                    delta[j + certain[i]] = current[j] * remaining;
                }
                total += current[j];
                current[j] = result;
            }
            answer += total * i;
        }
        for (double d : current) {
            answer += d * count;
        }
        out.printLine(answer);
    }
}
