package on2014_12.on2014_12_24_Codeforces_Round__284__Div__1_.E___Stairs_and_Lines;



import net.egork.io.IOUtils;
import net.egork.numbers.Matrix;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int[] width = IOUtils.readIntArray(in, 7);
        long[][] matrices = new long[7][];
        for (int i = 1; i <= 7; i++) {
            long[][] matrix = new long[1 << i][1 << i];
            for (int j = 0; j < (1 << i); j++) {
                for (int k = 0; k < (1 << i); k++) {
                    for (int l = 0; l < (1 << (i - 1)); l++) {
                        int mask = 2 * l + 1 + (1 << i);
                        boolean good = true;
                        for (int m = 0; m < i; m++) {
                            if ((j >> m & 1) == 1 && (k >> m & 1) == 1 && (mask >> m & 1) == 1 && (mask >> (m + 1) & 1) == 1) {
                                good = false;
                                break;
                            }
                        }
                        if (good) {
                            matrix[j][k]++;
                        }
                    }
                }
            }
            matrices[i - 1] = Matrix.convert(matrix);
        }
        Matrix.mod = (long) (1e9 + 7);
        long[] result = new long[128];
        result[127] = 1;
        for (int i = 7; i >= 1; i--) {
            long[][] current = Matrix.convert(Matrix.power(matrices[i - 1], width[i - 1], Matrix.mod, 1 << i), 1 << i);
            long[] next = new long[result.length >> 1];
            for (int j = 0; j < next.length; j++) {
                for (int k = 0; k < result.length; k++) {
                    next[j] += current[j + next.length][k] * result[k] % Matrix.mod;
                }
                next[j] %= Matrix.mod;
            }
            result = next;
        }
        out.printLine(result[0]);
    }
}
