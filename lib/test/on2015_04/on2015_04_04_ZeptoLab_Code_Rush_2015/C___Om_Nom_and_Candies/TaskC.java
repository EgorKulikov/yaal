package on2015_04.on2015_04_04_ZeptoLab_Code_Rush_2015.C___Om_Nom_and_Candies;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long limit = in.readInt();
        long[] goodness = IOUtils.readLongArray(in, 2);
        long[] weight = IOUtils.readLongArray(in, 2);
        long full = weight[0] * weight[1] * 2;
        long perFull = Math.max(weight[1] * goodness[0], weight[0] * goodness[1]) * 2;
        long qtyFull = Math.max(limit / full - 1, 0);
        long answer = perFull * qtyFull;
        limit -= full * qtyFull;
        if (weight[0] < weight[1]) {
            ArrayUtils.reverse(weight);
            ArrayUtils.reverse(goodness);
        }
        long add = 0;
        for (int i = 0; i * weight[0] <= limit; i++) {
            long current = goodness[0] * i + (limit - i * weight[0]) / weight[1] * goodness[1];
            add = Math.max(add, current);
        }
        answer += add;
        out.printLine(answer);
    }
}
