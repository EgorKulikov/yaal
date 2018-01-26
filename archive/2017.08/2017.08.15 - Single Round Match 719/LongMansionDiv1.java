package net.egork;

import static java.lang.Long.MAX_VALUE;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class LongMansionDiv1 {
    public long minimalTime(int[] t, int sX, int sY, int eX, int eY) {
        int n = t.length;
        long answer = MAX_VALUE;
        for (int i = 0; i < n; i++) {
            long current = 0;
            for (int j = min(sX, i); j <= max(sX, i); j++) {
                current += t[j];
            }
            for (int j = min(eX, i); j <= max(eX, i); j++) {
                current += t[j];
            }
            current += (long)(max(sY, eY) - min(sY, eY) - 1) * t[i];
            answer = Math.min(answer, current);
        }
        return answer;
    }
}
