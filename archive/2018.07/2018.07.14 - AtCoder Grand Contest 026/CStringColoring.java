package net.egork;

import net.egork.collections.Pair;
import net.egork.collections.map.Counter;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Map;

import static net.egork.string.StringUtils.reverse;

public class CStringColoring {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        String s = in.readString();
        Counter<Pair<String, String>> first = compute(s.substring(0, n), n);
        Counter<Pair<String, String>> second = compute(reverse(s.substring(n)), n);
        long answer = 0;
        for (Map.Entry<Pair<String, String>, Long> entry : first.entrySet()) {
            if (second.containsKey(entry.getKey())) {
                answer += entry.getValue() * second.get(entry.getKey());
            }
        }
        out.printLine(answer);
    }

    private Counter<Pair<String, String>> compute(String s, int n) {
        Counter<Pair<String, String>> counter = new Counter<>();
        for (int i = 1; i < (1 << n); i += 2) {
            StringBuilder red = new StringBuilder();
            StringBuilder blue = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if ((i >> j & 1) == 1) {
                    red.append(s.charAt(j));
                } else {
                    blue.append(s.charAt(j));
                }
            }
            String r = red.toString();
            String b = blue.toString();
            counter.add(Pair.makePair(r, b));
            counter.add(Pair.makePair(b, r));
        }
        return counter;
    }
}
