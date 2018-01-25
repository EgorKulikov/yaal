package on2016_04.on2016_04_08_April_Challenge_2016.Chef_And_Coloring;



import net.egork.generated.collections.list.CharArray;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.ArrayUtils.maxElement;

public class ChefAndColoring {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        char[] colors = in.readCharArray(n);
        out.printLine(n - maxElement(new CharArray(colors).qty()));
    }
}
