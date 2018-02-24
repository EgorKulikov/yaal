package on2018_02.on2018_02_18_Qualification.E______________________;



import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.HashMap;
import java.util.Map;

import static net.egork.misc.ArrayUtils.createArray;
import static net.egork.misc.ArrayUtils.maxElement;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        String[] w = in.readStringArray(n);
        String[] s = in.readStringArray(k);
        Map<String, Integer> query = new HashMap<>();
        for (int i = 0; i < k; i++) {
            query.put(s[i], i);
        }
        int[] id = new int[n];
        for (int i = 0; i < n; i++) {
            if (query.containsKey(w[i])) {
                id[i] = query.get(w[i]);
            } else {
                id[i] = -1;
            }
        }
        int[] next = new int[n];
        int[] first = createArray(k, n);
        for (int i = n - 1; i >= 0; i--) {
            if (id[i] != -1) {
                next[i] = first[id[i]];
                first[id[i]] = i;
            }
        }
        int ends = maxElement(first);
        long answer = 0;
        for (int i = 0; i < n; i++) {
            answer += n - ends;
            if (id[i] != -1) {
                ends = Math.max(ends, next[i]);
            }
        }
        out.printLine(answer);
    }
}
