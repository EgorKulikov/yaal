package on2017_05.on2017_05_14_RCC_2017_Elimination_round.B___________________;



import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Long.MAX_VALUE;
import static net.egork.misc.ArrayUtils.createArray;
import static net.egork.misc.ArrayUtils.minElement;

public class TaskB {
    private static final long INFTY = MAX_VALUE / 2;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int a = in.readInt();
        int b = in.readInt();
        int c = in.readInt();
        int[] layouts = new int[n];
        for (int i = 0; i < n; i++) {
            String layout = in.readString();
            for (int j = 0; j < layout.length(); j++) {
                layouts[i] |= 1 << (layout.charAt(j) - 'a');
            }
        }
        String s = in.readString();
        long[] result = createArray(n, INFTY);
        result[0] = 0;
        for (char d : s.toCharArray()) {
            d -= 'a';
            long any = INFTY;
            long last = INFTY;
            for (int i = 0; i < n; i++) {
                any += b;
                last += a;
                any = Math.min(any, last);
                result[i] = Math.min(result[i], any);
                last = result[i];
            }
            for (int i = 0; i < n; i++) {
                any += b;
                last += a;
                any = Math.min(any, last);
                result[i] = Math.min(result[i], any);
                last = result[i];
            }
            for (int i = 0; i < n; i++) {
                if ((layouts[i] >> d & 1) == 0) {
                    result[i] = INFTY;
                } else {
                    result[i] += c;
                }
            }
        }
        long answer = minElement(result);
        if (answer >= INFTY) {
            out.printLine(-1);
        } else {
            out.printLine(answer);
        }
    }
}
