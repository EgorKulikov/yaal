package on2015_06.on2015_06_28_June_Lunchtime_2015.ChefAndBitwiseOrOperation;


import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class ChefAndBitwiseOrOperation {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int size = in.readInt();
        int count = in.readInt();
        int[] array = IOUtils.readIntArray(in, size);
        int[][] positions = new int[size][32];
        int[][] ors = new int[size][32];
        int[] qty = new int[size];
        for (int i = 0; i < size; i++) {
            int current = 0;
            for (int j = i; j >= 0; j--) {
                if ((current | array[j]) != current) {
                    current |= array[j];
                    ors[i][qty[i]] = current;
                    positions[i][qty[i]++] = j;
                }
            }
            positions[i] = Arrays.copyOf(positions[i], qty[i]);
        }
        long[] answer = new long[size + 1];
        for (int i = 0; i < count; i++) {
            for (int j = size - count + i + 1; j > i; j--) {
                for (int k = 0; k < positions[j - 1].length; k++) {
                    answer[j] = Math.max(answer[j], answer[positions[j - 1][k]] + ors[j - 1][k]);
                }
            }
        }
        out.printLine(answer[size]);
    }
}
