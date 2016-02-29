package on2016_02.on2016_02_26_Manthan__Codefest_16.C___Spy_Syndrome_2;



import net.egork.collections.map.EHashMap;
import net.egork.string.SimpleStringHash;
import net.egork.string.StringHash;
import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        String encrypted = in.readString();
        int m = in.readInt();
        String[] w = readStringArray(in, m);
        Map<Long, Integer> word = new EHashMap<>();
        for (int i = 0; i < m; i++) {
            word.put(new SimpleStringHash(StringUtils.reverse(w[i].toLowerCase())).hash(0), i);
        }
        StringHash hash = new SimpleStringHash(encrypted);
        int[] last = createArray(n + 1, -1);
        last[0] = m;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (last[j] != -1 && word.containsKey(hash.hash(j, i))) {
                    last[i] = word.get(hash.hash(j, i));
                    break;
                }
            }
        }
        List<String> answer = new ArrayList<>();
        for (int i = n; i != 0; i -= w[last[i]].length()) {
            answer.add(w[last[i]]);
        }
        Collections.reverse(answer);
        out.printLine(answer.toArray());
    }
}
