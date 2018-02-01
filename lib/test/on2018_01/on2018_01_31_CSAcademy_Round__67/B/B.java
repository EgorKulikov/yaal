package on2018_01.on2018_01_31_CSAcademy_Round__67.B;



import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.generated.collections.comparator.IntComparator.REVERSE;
import static net.egork.misc.ArrayUtils.partialSums;
import static net.egork.misc.ArrayUtils.sort;

public class B {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = in.readIntArray(n);
        sort(a, REVERSE);
        long[] partial = partialSums(a);
        long answer = 0;
        for (int i = 0; i <= n; i += 2) {
            answer = Math.max(answer, partial[i]);
        }
        out.printLine(answer);
    }
}
