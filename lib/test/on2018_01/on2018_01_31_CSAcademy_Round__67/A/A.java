package on2018_01.on2018_01_31_CSAcademy_Round__67.A;



import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.ArrayUtils.maxElement;

public class A {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] p = in.readIntArray(n);
        int[] count = new int[n];
        for (int i = 0; i < n; i++) {
            int x = (i - p[i] + n) % n;
            count[x]++;
        }
        out.printLine(maxElement(count));
    }
}
