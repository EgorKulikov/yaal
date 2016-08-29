package on2016_05.on2016_05_30_SnackDown_Online_Qualifier_2016.K_good_Words;



import net.egork.generated.collections.list.CharArray;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static java.lang.Integer.MAX_VALUE;
import static java.util.Arrays.copyOfRange;
import static net.egork.misc.ArrayUtils.sort;

public class KgoodWords {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String w = in.readString();
        int k = in.readInt();
        int[] qty = copyOfRange(new CharArray(w.toCharArray()).qty('z' + 1), 'a', 'z' + 1);
        sort(qty);
        int start = 0;
        int finish = 0;
        int sum = w.length();
        int delta = qty.length;
        int answer = MAX_VALUE;
        for (int i = 0; i < k; i++) {
            while (finish < qty.length && qty[finish] == i) {
                delta--;
                finish++;
            }
            sum -= delta;
        }
        for (int j = 0; j <= w.length(); j++) {
            answer = Math.min(answer, sum);
            while (start < qty.length && qty[start] == j) {
                sum += qty[start++];
            }
            while (finish < qty.length && qty[finish] == j + k) {
                delta--;
                finish++;
            }
            sum -= delta;
        }
        out.printLine(answer);
    }
}
