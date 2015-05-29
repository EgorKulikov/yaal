package on2015_05.on2015_05_14_Kattis_ICPC_World_Finals_2015_Warmup_6A.C___Bus_Tour;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        if (in.isExhausted()) {
            throw new UnknownError();
        }
        int count = in.readInt();
        int edgeCount = in.readInt();
        int[] from = new int[edgeCount];
        int[] to = new int[edgeCount];
        int[] distance = new int[edgeCount];
        IOUtils.readIntArrays(in, from, to, distance);
        int[][] time = new int[count][count];
        ArrayUtils.fill(time, Integer.MAX_VALUE / 4);
        for (int i = 0; i < count; i++) {
            time[i][i] = 0;
        }
        for (int i = 0; i < edgeCount; i++) {
            time[to[i]][from[i]] = time[from[i]][to[i]] = Math.min(time[from[i]][to[i]], distance[i]);
        }
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                for (int k = 0; k < count; k++) {
                    time[j][k] = Math.min(time[j][k], time[j][i] + time[i][k]);
                }
            }
        }
        int[][] fromStart = new int[count][1 << (count - 2)];
        ArrayUtils.fill(fromStart, Integer.MAX_VALUE / 4);
        fromStart[0][0] = 0;
        for (int i = 1; i < (1 << (count - 2)); i++) {
            for (int k = 1; k < count - 1; k++) {
                if ((i >> (k - 1) & 1) == 1) {
                    if (Integer.bitCount(i) == 1) {
                        fromStart[k][i] = time[0][k];
                    } else {
                        for (int j = 1; j < count - 1; j++) {
                            if (j != k && ((i >> (j - 1) & 1) == 1)) {
                                fromStart[k][i] = Math.min(fromStart[k][i], fromStart[j][i - (1 << (k - 1))] + time[j][k]);
                            }
                        }
                    }
                }
            }
        }
        int[][] fromEnd = new int[count][1 << (count - 2)];
        ArrayUtils.fill(fromEnd, Integer.MAX_VALUE / 4);
        fromEnd[count - 1][0] = 0;
        for (int i = 1; i < (1 << (count - 2)); i++) {
            for (int k = 1; k < count - 1; k++) {
                if ((i >> (k - 1) & 1) == 1) {
                    if (Integer.bitCount(i) == 1) {
                        fromEnd[k][i] = time[count - 1][k];
                    } else {
                        for (int j = 1; j < count - 1; j++) {
                            if (j != k && ((i >> (j - 1) & 1) == 1)) {
                                fromEnd[k][i] = Math.min(fromEnd[k][i], fromEnd[j][i - (1 << (k - 1))] + time[j][k]);
                            }
                        }
                    }
                }
            }
        }
        int answer = Integer.MAX_VALUE;
        int all = (1 << (count - 2)) - 1;
        for (int i = 0; i < (1 << (count - 2)); i++) {
            if (Integer.bitCount(i) != (count - 2) / 2) {
                continue;
            }
            int there = Integer.MAX_VALUE / 2;
            int back = Integer.MAX_VALUE / 2;
            for (int j = 0; j < count; j++) {
                for (int k = 0; k < count; k++) {
                    there = Math.min(there, fromStart[j][i] + fromEnd[k][all - i] + time[j][k]);
                    back = Math.min(back, fromEnd[j][i] + fromStart[k][all - i] + time[j][k]);
                }
            }
            answer = Math.min(answer, there + back);
        }
        out.printLine("Case " + testNumber + ":", answer);
    }
}
