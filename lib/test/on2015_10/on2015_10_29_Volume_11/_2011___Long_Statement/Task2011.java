package on2015_10.on2015_10_29_Volume_11._2011___Long_Statement;



import net.egork.generated.collections.function.IntToIntFunction;
import net.egork.generated.collections.list.IntArray;
import net.egork.generated.collections.list.IntList;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Task2011 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        IntList qty = new IntArray(new IntArray(IOUtils.readIntArray(in, n)).map((IntToIntFunction) x -> x - 1).qty(3)).sort();
        if (qty.get(1) == 0) {
            out.printLine("No");
        } else if (qty.get(0) > 0) {
            out.printLine("Yes");
        } else if (qty.get(2) >= 5 || qty.get(1) >= 2) {
            out.printLine("Yes");
        } else {
            out.printLine("No");
        }
    }
}
