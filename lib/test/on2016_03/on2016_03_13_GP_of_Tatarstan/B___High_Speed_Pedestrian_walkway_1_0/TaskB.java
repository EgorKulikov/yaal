package on2016_03.on2016_03_13_GP_of_Tatarstan.B___High_Speed_Pedestrian_walkway_1_0;



import net.egork.numbers.Matrix;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static net.egork.io.IOUtils.readIntArray;

public class TaskB {
    private static final long MOD = (long) (1e9 + 9);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long n = in.readLong();
        int m = in.readInt();
        int[] c = readIntArray(in, m);
        long[][] matrix = new long[m * m][m * m];
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < m; j++) {
                matrix[i * m + j][(i - 1) * m + j - 1]++;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j * m + i - 1] += c[j];
                matrix[i * m][(i - 1) * m + j] += c[j];
            }
        }
        if (m >= 2) {
            matrix[0][0] += c[1];
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                matrix[0][i * m + j] += (long)c[i] * c[j] % MOD;
            }
        }
        long[] flat = Matrix.convert(matrix);
        for (int i = 0; i < flat.length; i++) {
            flat[i] %= MOD;
        }
        flat = Matrix.power(flat, n, MOD, m * m);
        out.printLine(flat[0]);
    }
}
