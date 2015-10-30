package on2015_10.on2015_10_05_October_Challenge_2015.WhoDaresToBeAMillionaire;


import net.egork.collections.intcollection.Range;
import net.egork.generated.collections.list.IntArray;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class WhoDaresToBeAMillionaire {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        char[] correct = IOUtils.readCharArray(in, n);
        char[] chef = IOUtils.readCharArray(in, n);
        int[] points = IOUtils.readIntArray(in, n + 1);
        int max = (int) Range.range(n).map((int i) -> chef[i] == correct[i] ? 1 : 0).sum();
        int answer = new IntArray(points).subList(0, max + 1).max();
        if (max == n) {
            answer = points[n];
        }
        out.printLine(answer);
    }
}
