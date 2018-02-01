package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import net.egork.misc.ArrayUtils;

import java.util.Arrays;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static net.egork.misc.ArrayUtils.*;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class P2 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int d = in.readInt();
        int[] x = in.readIntArray(n);
        int[] p1 = new int[d];
        int[] p2 = new int[d];
        in.readIntArrays(p1, p2);
        decreaseByOne(p1, p2);
        int[] order = ArrayUtils.order(x);
        int[] rev = reversePermutation(order);
        for (int i = 0; i < d; i++) {
            p1[i] = rev[p1[i]];
            p2[i] = rev[p2[i]];
        }
        sort(x);
        int[] f1 = createArray(n, -1);
        int[] n1 = new int[d];
        int[] f2 = createArray(n, -1);
        int[] n2 = new int[d];
        for (int i = 0; i < d; i++) {
            n1[i] = f1[p1[i]];
            f1[p1[i]] = i;
            n2[i] = f2[p2[i]];
            f2[p2[i]] = i;
        }
        boolean[] took = new boolean[d];
        int minStart = minElement(p1);
        int end = max(maxElement(p1), maxElement(p2));
        for (int i = minStart; i <= end; i++) {
            for (int j = f1[i]; j != -1; j = n1[j]) {
                took[j] = true;
            }
            for (int j = f2[i]; j != -1; j = n2[j]) {
                took[j] = false;
            }
        }
        int answer = x[end] - x[minStart];
        int needReturn = end;
        for (int i = 0; i < d; i++) {
            if (took[i]) {
                needReturn = min(needReturn, p2[i]);
            }
        }
        answer += x[end] - x[needReturn];
        Arrays.fill(took, false);
        int maxStart = maxElement(p1);
        end = min(minElement(p1), minElement(p2));
        for (int i = maxStart; i >= end; i--) {
            for (int j = f1[i]; j != -1; j = n1[j]) {
                took[j] = true;
            }
            for (int j = f2[i]; j != -1; j = n2[j]) {
                took[j] = false;
            }
        }
        int candidate = x[maxStart] - x[end];
        needReturn = end;
        for (int i = 0; i < d; i++) {
            if (took[i]) {
                needReturn = max(needReturn, p2[i]);
            }
        }
        candidate += x[needReturn] - x[end];
        answer = Math.min(answer, candidate);
        out.printLine(answer);
    }
}
