package on2018_05.on2018_05_02_Codeforces_Round__478__Div__2_.Problem_A________________________;



import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.sort;
import static net.egork.misc.ArrayUtils.unique;

public class ProblemAAramejskijManuskript {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        String[] words = in.readStringArray(n);
        Set<String> roots = new HashSet<>();
        for (String word : words) {
            char[] c = word.toCharArray();
            sort(c);
            c = unique(c);
            roots.add(new String(c));
        }
        out.printLine(roots.size());
    }
}
