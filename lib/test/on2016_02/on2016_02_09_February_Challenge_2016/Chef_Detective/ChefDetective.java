package on2016_02.on2016_02_09_February_Challenge_2016.Chef_Detective;



import net.egork.generated.collections.function.IntTask;
import net.egork.generated.collections.list.IntArray;
import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class ChefDetective {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] r = readIntArray(in, n);
        decreaseByOne(r);
        boolean[] killer = createArray(n, true);
        new IntArray(r).forEach((IntTask)  x -> {
            if (x != -1) {
                killer[x] = false;
            }
        });
        IntList answer = new IntArrayList();
        for (int i = 0; i < n; i++) {
            if (killer[i]) {
                answer.add(i + 1);
            }
        }
        out.printLine(answer);
    }
}
