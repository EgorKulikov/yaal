package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.System.arraycopy;
import static net.egork.misc.MiscUtils.MOD7;
import static net.egork.misc.MiscUtils.decreaseByOne;
import static net.egork.numbers.IntegerUtils.generateBinomialCoefficients;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        int[] a = new int[k];
        int[] b = new int[k];
        in.readIntArrays(a, b);
        decreaseByOne(a, b);
        long[] add = new long[n - k + 1];
        add[0] = 1;
        for (int i = 1; i <= n - k; i++) {
            add[i] = add[i - 1] * (2 * i - 1) % MOD7;
        }
        long[][] ways = new long[2 * n][2 * n];
        boolean[][] valid = new boolean[2 * n][2 * n];
        long answer = 0;
        for (int i = 0; i < 2 * n; i++) {
            for (int j = i + 2; j < 2 * n; j += 2) {
                boolean good = true;
                int inside = (j - i) >> 1;
                for (int l = 0; l < k; l++) {
                    if (a[l] >= i && a[l] < j || a[l] + 2 * n < j) {
                        if (b[l] >= i && b[l] < j || b[l] + 2 * n < j) {
                            inside--;
                        } else {
                            good = false;
                            break;
                        }
                    } else {
                        if (b[l] >= i && b[l] < j || b[l] + 2 * n < j) {
                            good = false;
                            break;
                        }
                    }
                }
                if (!good) {
                    continue;
                }
                valid[i][j] = true;
                ways[i][j] = add[inside];
                answer += ways[i][j] * add[n - k - inside] % MOD7;
            }
        }
        answer += add[n - k];
        long[][] curCycles = new long[2 * n][2 * n];
        for (int i = 0; i < 2 * n; i++) {
            arraycopy(ways[i], 0, curCycles[i], 0, 2 * n);
        }
        long[][] nextCycles = new long[2 * n][2 * n];
        long[] mult = new long[n + 1];
        long[][] c = generateBinomialCoefficients(n + 1, MOD7);
        for (int i = 2; i <= n; i++) {
            mult[i] = i - 1;
            for (int j = 2; j < i; j++) {
                mult[i] -= mult[j] * c[i][j] % MOD7;
            }
            mult[i] %= MOD7;
        }
        int[][] extra = new int[2 * n][2 * n];
        for (int i = 0; i < 2 * n; i++) {
            for (int j = i + 2; j < 2 * n; j += 2) {
                extra[i][j] = n - ((j - i) >> 1);
                for (int l = 0; l < k; l++) {
                    if ((a[l] < i || a[l] >= j) && (b[l] < i || b[l] >= j)) {
                        extra[i][j]--;
                    }
                }
            }
        }
        for (int i = 0; i < 2 * n; i++) {
            for (int j = i + 2; j < 2 * n; j += 2) {
                if (!valid[i][j]) {
                    continue;
                }
                for (int l = i + 2; l < j; l += 2) {
                    nextCycles[i][j] -= (nextCycles[i][l] + ways[i][l]) * ways[l][j] % MOD7;
                }
                nextCycles[i][j] %= MOD7;
                answer += nextCycles[i][j] * add[extra[i][j]] % MOD7;
            }
        }
/*        for (int x = 3; x <= n; x++) {
            for (int i = 0; i < 2 * n; i++) {
                for (int j = i + 2 * (x - 1); j < 2 * n; j += 2) {
                    nextCycles[i][j] = 0;
                    if (!valid[i][j]) {
                        continue;
                    }
                    for (int l = i + 2 * (x - 2); l < j; l += 2) {
                        nextCycles[i][j] += curCycles[i][l] * ways[l][j] % MOD7;
                    }
                    nextCycles[i][j] %= MOD7;
                    if (nextCycles[i][j] == 0) {
                        continue;
                    }
                    answer += nextCycles[i][j] * add[extra[i][j]] % MOD7 * mult[x] % MOD7;
                }
            }
            long[][] temp = curCycles;
            curCycles = nextCycles;
            nextCycles = temp;
        }*/
        answer %= MOD7;
        answer += MOD7;
        answer %= MOD7;
        out.printLine(answer);
    }
}
