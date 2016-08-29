package on2016_06.on2016_06_25_World_CodeSprint__4.Equal_Stacks;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static net.egork.io.IOUtils.readIntArray;
import static net.egork.misc.ArrayUtils.sumArray;

public class EqualStacks {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int[] n = readIntArray(in, 3);
        int[][] heights = new int[3][];
        for (int i = 0; i < 3; i++) {
            heights[i] = readIntArray(in, n[i]);
        }
        long[] sums = new long[3];
        for (int i = 0; i < 3; i++) {
            sums[i] = sumArray(heights[i]);
        }
        int[] ind = new int[3];
        while (true) {
            int at = -1;
            long max = -1;
            for (int i = 0; i < 3; i++) {
                if (sums[i] > max) {
                    max = sums[i];
                    at = i;
                }
            }
            boolean equal = true;
            for (int i = 0; i < 3; i++) {
                if (sums[i] != max) {
                    equal = false;
                    break;
                }
            }
            if (equal) {
                out.printLine(sums[0]);
                return;
            }
            sums[at] -= heights[at][ind[at]++];
        }
    }
}
