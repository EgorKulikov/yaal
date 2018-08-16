package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.HashMap;
import java.util.Map;

public class TaskA {
    Map<Integer, Long> answer = new HashMap<>();

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        answer.put(1, 1L);
        answer.put(2, 1L);
        answer.put(3, 2L);
        int w = in.readInt();
        out.printLine(go(w));
    }

    private long go(int w) {
        if (answer.containsKey(w)) {
            return answer.get(w);
        }
        long result = 0;
        int i;
        for (i = 2; i * i <= w; i++) {
            result += go(w / i);
        }
        int last = i - 1;
        for (; i >= 1; i--) {
            int next = w / i;
            if (next < last) {
                continue;
            }
            result += (next - last) * go(i);
            last = next;
        }
        answer.put(w, result);
        return result;
    }
}
