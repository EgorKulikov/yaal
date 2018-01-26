package net.egork;

import static java.util.Arrays.fill;
import static net.egork.misc.ArrayUtils.partialSums;

public class DengklekGaneshAndDetention {
    public double getExpected(int N, int valInit, int valMul, int valAdd, int valMod) {
        int[] val = new int[N];
        val[0] = valInit;
        for (int i = 1; i < N; i++) {
            val[i] = (int) (((long)val[i - 1] * valMul + valAdd) % valMod);
        }
        int[] lamps = new int[N];
        int[] probs = new int[N];
        for (int i = 0; i < N; i++) {
            lamps[i] = val[i] % 2;
            probs[i] = val[i] % 101;
        }
        double answer = 0;
        double[] prob = new double[2];
        prob[0] = 1;
        double[] nProb = new double[2];
        double[] eOff = new double[2];
        double[] nEOff = new double[2];
        long[] qty = partialSums(lamps);
        for (int i = 0; i < N; i++) {
            fill(nProb, 0);
            fill(nEOff, 0);
            nProb[lamps[i]] += prob[lamps[i]];
            nEOff[lamps[i]] = eOff[lamps[i]] + prob[lamps[i]];
            double p = probs[i] / 100d;
            answer += p * eOff[1 - lamps[i]];
            nProb[1 - lamps[i]] += prob[1 - lamps[i]] * p;
            nEOff[1 - lamps[i]] += prob[1 - lamps[i]] * p * i - eOff[1 - lamps[i]] * p;
            double q = 1 - p;
            answer += prob[1 - lamps[i]] * q * (lamps[i] == 0 ? qty[N] - qty[i + 1] : N - i - 1 - qty[N] + qty[i + 1]);
            nProb[lamps[i]] += prob[1 - lamps[i]] * q;
            nEOff[lamps[i]] += eOff[1 - lamps[i]] * q;
            double[] temp = prob;
            prob = nProb;
            nProb = temp;
            temp = eOff;
            eOff = nEOff;
            nEOff = temp;
        }
        return answer;
    }
}
