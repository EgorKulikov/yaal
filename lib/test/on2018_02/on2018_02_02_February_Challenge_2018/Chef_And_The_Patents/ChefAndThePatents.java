package on2018_02.on2018_02_02_February_Challenge_2018.Chef_And_The_Patents;



import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Math.min;
import static net.egork.string.StringUtils.count;

public class ChefAndThePatents {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int x = in.readInt();
        int k = in.readInt();
        String s = in.readString();
        int odd = count(s, 'O');
        int even = k - odd;
        int[] qty = new int[]{odd, even};
        for (int i = 0; i < m; i++) {
            int current = min(x, qty[i & 1]);
            qty[i & 1] -= current;
            n -= current;
        }
        out.printLine(n <= 0 ? "yes" : "no");
    }
}
