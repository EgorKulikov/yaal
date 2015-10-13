package on2015_09.on2015_09_28_Single_Round_Match_669.PowerPartition;



import net.egork.misc.ArrayUtils;

import java.util.Arrays;

public class PowerPartition {
    private static final long MOD = (long) (1e9 + 7);
    long[][][] part;
    long[][][][] dyn;
    int m;

    public int count(int M, long X) {
        m = M;
        int levels = 0;
        long current = X;
        while (current > 0) {
            levels++;
            current /= M;
        }
        part = new long[levels][levels][levels];
        dyn = new long[levels][levels][levels][M + 1];
        ArrayUtils.fill(dyn, -1);
        ArrayUtils.fill(part, -1);
        long[] result = new long[levels + 1];
        long[] next = new long[levels + 1];
        result[0] = 1;
        int cLevel = 0;
        while (X != 0) {
            int cur = (int) (X % M);
            for (int i = 0; i < cur; i++) {
                Arrays.fill(next, 0);
                for (int j = 0; j <= cLevel; j++) {
                    for (int k = 0; k <= j; k++) {
                        next[j] += result[k] * calcPart(cLevel, k, j) % MOD;
                    }
                    next[j] %= MOD;
                }
                long[] temp = result;
                result = next;
                next = temp;
            }
            X /= M;
            cLevel++;
        }
        long answer = 0;
        for (long l : result) {
            answer += l;
        }
		return (int) (answer % MOD);
    }

    private long calcPart(int cLevel, int fLevel, int tLevel) {
        if (part[cLevel][fLevel][tLevel] != -1) {
            return part[cLevel][fLevel][tLevel];
        }
        if (tLevel == cLevel) {
            return part[cLevel][fLevel][tLevel] = 1;
        }
        return part[cLevel][fLevel][tLevel] = calcDyn(cLevel, fLevel, tLevel, m);
    }

    private long calcDyn(int cLevel, int fLevel, int tLevel, int remaining) {
        if (dyn[cLevel][fLevel][tLevel][remaining] != -1) {
            return dyn[cLevel][fLevel][tLevel][remaining];
        }
        if (remaining == 0) {
            return dyn[cLevel][fLevel][tLevel][remaining] = fLevel == tLevel ? 1 : 0;
        }
        dyn[cLevel][fLevel][tLevel][remaining] = 0;
        for (int i = fLevel; i <= tLevel; i++) {
            dyn[cLevel][fLevel][tLevel][remaining] += calcDyn(cLevel, i, tLevel, remaining - 1) * calcPart(cLevel - 1, fLevel, i) % MOD;
        }
        return dyn[cLevel][fLevel][tLevel][remaining] %= MOD;
    }
}
