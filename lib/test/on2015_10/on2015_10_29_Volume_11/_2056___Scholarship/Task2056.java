package on2015_10.on2015_10_29_Volume_11._2056___Scholarship;



import net.egork.generated.collections.function.IntToIntFunction;
import net.egork.generated.collections.list.IntArray;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class Task2056 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] qty = new IntArray(in.readIntArray(n)).map((IntToIntFunction) x -> x - 3).qty(3);
        if (qty[0] != 0) {
            out.printLine("None");
        } else if (qty[2] == n) {
            out.printLine("Named");
        } else if (qty[2] >= qty[1]) {
            out.printLine("High");
        } else {
            out.printLine("Common");
        }
    }
}
