package net.egork;

import net.egork.io.IOUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Arrays;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int typeCount = in.readInt();
        int required = in.readInt();
        int[] power = IOUtils.readIntArray(in, count);
        int[] mask = new int[count];
        for (int i = 0; i < count; i++) {
            int size = in.readInt();
            for (int j = 0; j < size; j++) {
                mask[i] += 1 << (in.readInt() - 1);
            }
        }
        int[] answer = new int[count];
        int current = count - 1;
        int total = 1 << typeCount;
        int[] best = new int[total];
        int[] worst = new int[total];
        int[] next = new int[count];
        int[] last = new int[count];
        Arrays.fill(best, -1);
        Arrays.fill(worst, -1);
        Arrays.fill(next, -1);
        Arrays.fill(last, -1);
        long[] dyn = new long[total];
        long[] ndyn = new long[total];
        boolean enough = false;
        for (int i = count - 1; i >= 0; i--) {
            int same = worst[mask[i]];
            while (same != -1 && power[same] <= power[i]) {
                same = next[same];
            }
            worst[mask[i]] = i;
            if (same == -1) {
                best[mask[i]] = i;
            } else {
                next[i] = same;
                last[same] = i;
            }
            if (same == -1) {
                int remaining = (total) - 1 - mask[i];
                for (int j = remaining; j != 0; j = (j - 1) & remaining) {
                    dyn[j + mask[i]] = Math.max(dyn[j + mask[i]], dyn[j] + power[i]);
                }
                dyn[mask[i]] = Math.max(dyn[mask[i]], power[i]);
            }
            if (!enough && dyn[(total) - 1] >= required) {
                enough = true;
            }
            if (enough && (next[i] == -1 || next[i] == current) && dyn[(total) - 1 - mask[i]] + power[i] >= required) {
                while (current > i) {
                    if (best[mask[current]] != current) {
                        current--;
                        continue;
                    }
                    best[mask[current]] = last[current];
                    for (int j = 1; j < total; j++) {
                        if ((j & mask[current]) != mask[current]) {
                            ndyn[j] = dyn[j];
                            continue;
                        }
                        if (best[j] != -1) {
                            ndyn[j] = power[best[j]];
                        } else {
                            ndyn[j] = 0;
                        }
                        for (int k = j - Integer.highestOneBit(j); k != 0; k = (k - 1) & j) {
                            ndyn[j] = Math.max(ndyn[j], ndyn[k] + ndyn[j - k]);
                        }
                    }
                    if (ndyn[(total) - 1] < required) {
                        best[mask[current]] = current;
                        break;
                    }
                    System.arraycopy(ndyn, 0, dyn, 0, total);
                    if (best[mask[current]] != -1) {
                        next[best[mask[current]]] = -1;
                    } else {
                        worst[mask[current]] = -1;
                    }
                    current--;
                }
            }
            answer[i] = enough ? current + 1 : -1;
        }
        for (int i : answer) {
            out.printLine(i);
        }
    }
}
