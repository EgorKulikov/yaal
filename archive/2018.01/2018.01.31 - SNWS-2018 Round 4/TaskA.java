package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Integer.lowestOneBit;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int[] u = new int[m];
        int[] v = new int[m];
        in.readIntArrays(u, v);
        int[] graph = new int[n];
        for (int i = 0; i < m; i++) {
            graph[u[i]] += 1 << v[i];
            graph[v[i]] += 1 << u[i];
        }
        int[] closure = new int[1 << n];
        for (int i = 0; i < (1 << n); i++) {
            closure[i] = i;
            for (int j = 0; j < n; j++) {
                if ((i >> j & 1) == 1) {
                    closure[i] |= graph[j];
                }
            }
        }
        int[] answer = new int[m];
//        IntList[] lists = new IntList[n];
//        for (int i = 0; i < n; i++) {
//            lists[i] = new IntArrayList();
//        }
        for (int i = 1; i < (1 << n) - 1; i += 2) {
            int mask = 1;
            while (true) {
                int nMask = closure[mask] & i;
                if (mask == nMask) {
                    break;
                }
                mask = nMask;
            }
            if (mask != i) {
                continue;
            }
            int j = (1 << n) - 1 - i;
            mask = lowestOneBit(j);
            while (true) {
                int nMask = closure[mask] & j;
                if (mask == nMask) {
                    break;
                }
                mask = nMask;
            }
            if (mask != j) {
                continue;
            }
//            for (int k = 0; k < n; k++) {
//                if ((i >> k & 1) == 1) {
//                    lists[k].add(i);
//                }
//            }
            for (int k = 0; k < m; k++) {
//                if ((i >> u[k] & 1) == 1 ^ (i >> v[k] & 1) == 1) {
                    answer[k] += (i >> u[k] & 1) ^ (i >> v[k] & 1);
//                }
            }
        }
//        int[][] arrs = new int[n][];
//        for (int i = 0; i < n; i++) {
//            arrs[i] = lists[i].toArray();
//        }
//        for (int i = 0; i < m; i++) {
//            for (int j : arrs[u[i]]) {
//                answer[j] +=
//            }
//        }
        out.printLine(answer);
    }
}
