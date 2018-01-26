package on2017_04.on2017_04_29_Qualification.F_____________;



import net.egork.string.StringUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.NavigableSet;
import java.util.TreeSet;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.readString();
        int[] p = StringUtils.prefixFunction(s);
        long result = 0;
        NavigableSet<Integer> present = new TreeSet<>();
        for (int i = 1; i < s.length(); i++) {
            if (!present.tailSet(p[i], true).isEmpty()) {
                result += p[i];
            } else if (p[i] != 0) {
                result += p[p[i] - 1];
            }
            present.add(p[i]);
        }
        out.printLine(result);
    }
}
