package on2016_06.on2016_06_23_Codeforces_Round__359__Div__1_.A___Robbers__watch;



import net.egork.generated.collections.list.CharArray;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static java.lang.Math.max;
import static net.egork.misc.ArrayUtils.maxElement;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int digCount = digCount(n - 1) + digCount(m - 1);
        if (digCount > 7) {
            out.printLine(0);
            return;
        }
        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                String s = Integer.toString(i, 7) + Integer.toString(j, 7);
                while (s.length() < digCount) {
                    s += "0";
                }
                if (maxElement(new CharArray(s.toCharArray()).qty()) <= 1) {
                    answer++;
                }
            }
        }
        out.printLine(answer);
    }

    private int digCount(int n) {
        int answer = 0;
        while (n > 0) {
            n /= 7;
            answer++;
        }
        return max(answer, 1);
    }
}
