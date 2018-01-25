package on2015_07.on2015_07_23_101_Hack_Jul_2015.Balanced_Contest_or_Not;



import net.egork.misc.ArrayUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class BalancedContestOrNot {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int[] beauty = new int[5];
        int[] difficulty = new int[5];
        in.readIntArrays(beauty, difficulty);
        ArrayUtils.orderBy(beauty, difficulty);
        for (int i = 1; i < 5; i++) {
            if (beauty[i] <= beauty[i - 1] || difficulty[i] <= difficulty[i - 1]) {
                out.printLine(0);
                return;
            }
        }
        out.printLine(1);
    }
}
