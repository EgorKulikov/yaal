package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class SquareArray {
    static final int BUBEN = 500;
    private static final long MOD = (long) (1e9 + 7);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int queryCount = in.readInt();
        long[] result = new long[count + 1];
        int[] type = new int[queryCount];
        int[] from = new int[queryCount];
        int[] to = new int[queryCount];
        IOUtils.readIntArrays(in, type, from, to);
        int start = 0;
        long[] qty = new long[count + 1];
        long[] fDelta = new long[count + 1];
        long[] tDelta = new long[count + 1];
        for (int i = 0; i < queryCount; i++) {
            if (type[i] == 2) {
                long answer = result[to[i]] - result[from[i] - 1];
                for (int j = start; j < i; j++) {
                    if (type[j] == 1) {
                        answer += get(to[i], from[j], to[j]) - get(from[i] - 1, from[j], to[j]);
                    }
                }
                answer %= MOD;
                if (answer < 0) {
                    answer += MOD;
                }
                out.printLine(answer);
            } else {
                qty[from[i] - 1]++;
                qty[to[i]]--;
                fDelta[to[i]] -= 2 * (to[i] - from[i] + 1);
                fDelta[to[i]] %= MOD;
                tDelta[to[i]] -= (long)(to[i] - from[i] + 2) * (to[i] - from[i] + 1);
                tDelta[to[i]] %= MOD;
            }
            if ((i % BUBEN) == BUBEN - 1) {
                start = i + 1;
                long tQty = qty[0];
                long tFDelta = 0;
                long tTDelta = 0;
                long total = 0;
                qty[0] = 0;
                for (int j = 1; j <= count; j++) {
                    tFDelta += tQty * 2;
                    if (tFDelta >= MOD) {
                        tFDelta -= MOD;
                    }
                    tTDelta += tFDelta;
                    if (tTDelta >= MOD) {
                        tTDelta -= MOD;
                    }
                    total += tTDelta;
                    tTDelta += tDelta[j];
                    tFDelta += fDelta[j];
                    tQty += qty[j];
                    if (total >= MOD) {
                        total -= MOD;
                    }
                    if (tTDelta < 0) {
                        tTDelta += MOD;
                    }
                    if (tFDelta < 0) {
                        tFDelta += MOD;
                    }
                    result[j] += total;
                    if (result[j] >= MOD) {
                        result[j] -= MOD;
                    }
                    qty[j] = fDelta[j] = tDelta[j] = 0;
                }
                start = i + 1;
            }
        }
    }

    private long get(long until, int from, int to) {
        if (until >= to) {
            until = to;
        }
        if (until < from) {
            return 0;
        }
        until -= from;
        until += 3;
        return until * (until - 1) * (until - 2) / 3 % MOD;
    }
}
