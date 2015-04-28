package on2015_04.on2015_04_09_Single_Round_Match_655.Nine;



import net.egork.numbers.IntegerUtils;
import net.egork.numbers.Matrix;

import java.util.Arrays;

public class Nine {
    private static final int MOD = (int) (1e9 + 7);

    public int count(int N, int[] d) {
        int size = (int) IntegerUtils.power(9, N);
        int[] result = new int[size];
        result[0] = 1;
        int[] next = new int[size];
        int[] qty = new int[32];
        for (int i : d) {
            qty[i]++;
        }
        Matrix matrix = new Matrix(9, 9);
        Matrix.mod = MOD;
        for (int i = 0; i < 9; i++) {
            Arrays.fill(matrix.data[i], 1);
            matrix.data[i][i] = 2;
        }
        for (int i = 0; i < 32; i++) {
            Arrays.fill(next, 0);
            Matrix power = matrix.power(qty[i]);
            for (int j = 0; j < 9; j++) {
                go(N - 1, j, i, (int) power.data[0][j], 0, 0, result, next);
            }
            int[] temp = result;
            result = next;
            next = temp;
        }
		return result[0];
    }

    private void go(int at, int digit, int mask, int multiplier, int inResult, int inNext, int[] result, int[] next) {
        if (at == -1) {
            next[inNext] += (long)result[inResult] * multiplier % MOD;
            if (next[inNext] >= MOD) {
                next[inNext] -= MOD;
            }
            return;
        }
        for (int i = 0; i < 9; i++) {
            if ((mask >> at & 1) == 0) {
                go(at - 1, digit, mask, multiplier, inResult * 9 + i, inNext * 9 + i, result, next);
            } else {
                int addNext = i + digit;
                if (addNext >= 9) {
                    addNext -= 9;
                }
                go(at - 1, digit, mask, multiplier, inResult * 9 + i, inNext * 9 + addNext, result, next);
            }
        }
    }
}
