package net.egork;

import net.egork.generated.collections.list.LongArray;

public class LR {
    public String construct(long[] s, long[] t) {
        if (new LongArray(s).sum() == 0) {
            if (new LongArray(t).sum() == 0) {
                return "";
            }
            return "No solution";
        }
        for (int i = 0; ; i++) {
            if (new LongArray(s).sum() > new LongArray(t).sum()) {
                return "No solution";
            }
            if (new LongArray(s).sum() == new LongArray(t).sum()) {
                for (int j = 0; j <= i; j++) {
                    boolean good = true;
                    for (int k = 0; k < s.length; k++) {
                        if (s[k] != t[(k + j) % s.length]) {
                            good = false;
                            break;
                        }
                    }
                    if (good) {
                        StringBuilder answer = new StringBuilder();
                        for (int k = 0; k < j; k++) {
                            answer.append("L");
                        }
                        for (int k = 0; k < i - j; k++) {
                            answer.append("R");
                        }
                        return answer.toString();
                    }
                }
                return "No solution";
            }
            long last = s[s.length - 1] + s[0];
            for (int j = 0; j < s.length - 1; j++) {
                s[j] += s[j + 1];
            }
            s[s.length - 1] = last;
        }
    }
}
