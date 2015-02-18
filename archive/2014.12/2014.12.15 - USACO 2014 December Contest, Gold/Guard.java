package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Guard {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int targetHeight = in.readInt();
        int[] height = new int[count];
        int[] weight = new int[count];
        int[] strength = new int[count];
        IOUtils.readIntArrays(in, height, weight, strength);
        for (int i = 0; i < count; i++) {
            strength[i] += weight[i];
        }
        ArrayUtils.orderBy(strength, height, weight);
        int answer = -1;
        for (int i = 1; i < (1 << count); i++) {
            long totalHeight = 0;
            int remainingStrength = Integer.MAX_VALUE;
            boolean good = true;
            for (int j = count - 1; j >= 0; j--) {
                if ((i >> j & 1) == 1) {
                    remainingStrength = Math.min(remainingStrength, strength[j]);
                    remainingStrength -= weight[j];
                    totalHeight += height[j];
                    if (remainingStrength < 0) {
                        good = false;
                        break;
                    }
                }
            }
            if (good && totalHeight >= targetHeight) {
                answer = Math.max(answer, remainingStrength);
            }
        }
        if (answer >= 0) {
            out.printLine(answer);
        } else {
            out.printLine("Mark is too tall");
        }
    }
}
