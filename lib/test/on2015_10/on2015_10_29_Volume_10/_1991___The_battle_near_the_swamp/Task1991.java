package on2015_10.on2015_10_29_Volume_10._1991___The_battle_near_the_swamp;



import net.egork.generated.collections.IntStream;
import net.egork.generated.collections.function.IntToIntFunction;
import net.egork.generated.collections.list.IntArray;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Task1991 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        IntStream a = new IntArray(IOUtils.readIntArray(in, n)).map((IntToIntFunction) x -> x - k);
        out.printLine(a.filter(x -> x > 0).sum(), -a.filter(x -> x < 0).sum());
    }
}
