package net.egork;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.abs;

public class ArithmeticSequenceDiv1 {
    public int findMinCost(int[] x) {
        int answer = MAX_VALUE;
        for (int i = 0; i < x.length; i++) {
            for (int j = -100; j <= 100; j++) {
                int current = 0;
                for (int k = 0; k < x.length; k++) {
                    current += abs(x[i] + (k - i) * j - x[k]);
                }
                answer = Math.min(answer, current);
            }
        }
        return answer;
    }
}
