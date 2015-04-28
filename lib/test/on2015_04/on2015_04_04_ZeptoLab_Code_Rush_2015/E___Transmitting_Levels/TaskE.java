package on2015_04.on2015_04_04_ZeptoLab_Code_Rush_2015.E___Transmitting_Levels;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int queryCount = in.readInt();
        int[] sizes = IOUtils.readIntArray(in, count);
        long[] partial = ArrayUtils.partialSums(sizes);
        int[] result = new int[count];
        long[] remaining = new long[count];
        for (int i = 0; i < queryCount; i++) {
            long limit = in.readLong();
            int at = count;
            int answer = Integer.MAX_VALUE;
            for (int j = count - 1; j >= 0; j--) {
                if (partial[count] - partial[j] <= limit) {
                    result[j] = 1;
                    remaining[j] = limit - (partial[count] - partial[j]);
                } else {
                    while (partial[at] - partial[j] > limit) {
                        at--;
                    }
                    result[j] = result[at] + 1;
                    remaining[j] = remaining[at];
                    if (remaining[j] >= partial[j]) {
                        answer = Math.min(answer, result[j]);
                    }
                }
            }
            out.printLine(answer);
        }
    }
}
