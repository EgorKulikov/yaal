package on2017_08.on2017_08_29_SnarkNews_Summer_Series_2017__Round_5.F;



import net.egork.collections.intcollection.IntHashMap;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.MAX_VALUE;
import static java.util.Collections.reverse;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.readString();
        int[] answer = new int[s.length() + 1];
        int[] last = new int[s.length() + 1];
        IntHashMap pos = new IntHashMap();
        pos.put(0, 0);
        int current = 0;
        for (int i = 0; i < s.length(); i++) {
            current ^= 1 << (s.charAt(i) - 'a');
            answer[i + 1] = MAX_VALUE;
            if (pos.contains(current)) {
                answer[i + 1] = answer[pos.get(current)] + 1;
                last[i + 1] = pos.get(current);
            }
            for (int j = 0; j < 26; j++) {
                int candidate = current ^ (1 << j);
                if (pos.contains(candidate) && answer[pos.get(candidate)] < answer[i + 1]) {
                    answer[i + 1] = answer[pos.get(candidate)] + 1;
                    last[i + 1] = pos.get(candidate);
                }
            }
            if (!pos.contains(current) || answer[pos.get(current)] > answer[i + 1]) {
                pos.put(current, i + 1);
            }
        }
        List<String> result = new ArrayList<>();
        current = s.length();
        while (current != 0) {
            result.add(s.substring(last[current], current));
            current = last[current];
        }
        reverse(result);
        out.printLine(result.toArray());
    }
}
