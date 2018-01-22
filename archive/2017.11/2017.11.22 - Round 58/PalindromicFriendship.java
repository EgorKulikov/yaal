package net.egork;

import net.egork.generated.collections.pair.IntIntPair;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.HashSet;
import java.util.Set;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static net.egork.io.InputReader.readIntArrays;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class PalindromicFriendship {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int[] a = new int[m];
        int[] b = new int[m];
        in.readIntArrays(a, b);
        decreaseByOne(a, b);
        Set<IntIntPair> edges = new HashSet<>();
        for (int i = 0; i < m; i++) {
            edges.add(new IntIntPair(min(a[i], b[i]), max(a[i], b[i])));
        }
        int answer = 1;
        for (int i = 0; i < n; i++) {
            for (int j = i - 1, k = i + 1; j >= 0 && k < n; j--, k++) {
                if (!edges.contains(new IntIntPair(j, k))) {
                    break;
                }
                answer = Math.max(answer, k - j + 1);
            }
            for (int j = i, k = i + 1; j >= 0 && k < n; j--, k++) {
                if (!edges.contains(new IntIntPair(j, k))) {
                    break;
                }
                answer = Math.max(answer, k - j + 1);
            }
        }
        out.printLine(answer);
    }
}
