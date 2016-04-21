package on2016_04.on2016_04_08_April_Challenge_2016.Chef_And_Coloring;



import net.egork.generated.collections.list.CharArray;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static net.egork.io.IOUtils.readCharArray;
import static net.egork.misc.ArrayUtils.maxElement;

public class ChefAndColoring {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        char[] colors = readCharArray(in, n);
        out.printLine(n - maxElement(new CharArray(colors).qty()));
    }
}
