package net.egork;

import net.egork.misc.ArrayUtils;

import static net.egork.misc.ArrayUtils.partialSums;

public class CanidsSeesaw {
    public int[] construct(int[] wolf, int[] fox, int k) {
        int[] order = ArrayUtils.order(fox);
        int current = 0;
        long[] wSums = partialSums(wolf);
        long[] fSums = new long[fox.length + 1];
        for (int i = 0; i < fox.length; i++) {
            fSums[i + 1] = fSums[i] + fox[order[i]];
        }
        for (int i = 1; i < fSums.length; i++) {
            if (fSums[i] > wSums[i]) {
                current++;
            }
        }
        if (current > k) {
            return new int[0];
        }
        if (current == k) {
            return order;
        }
        for (int i = 0; i < fox.length; i++) {
            for (int j = fox.length - 1; j > i; j--) {
                if (fSums[j] > wSums[j]) {
                    current--;
                }
                fSums[j] += fox[order[j]] - fox[order[j - 1]];
                if (fSums[j] > wSums[j]) {
                    current++;
                }
                int temp = order[j];
                order[j] = order[j - 1];
                order[j - 1] = temp;
                if (current == k) {
                    return order;
                }
            }
        }
        return new int[0];
    }
}
