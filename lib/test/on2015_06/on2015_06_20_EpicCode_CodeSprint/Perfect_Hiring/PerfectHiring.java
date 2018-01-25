package on2015_06.on2015_06_20_EpicCode_CodeSprint.Perfect_Hiring;



import net.egork.misc.ArrayUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class PerfectHiring {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int basePatience = in.readInt();
        int delta = in.readInt();
        long[] rating = in.readLongArray(count);
        for (int i = 0; i < count; i++) {
            rating[i] *= basePatience - i * delta;
        }
        out.printLine(ArrayUtils.maxPosition(rating) + 1);
    }
}
