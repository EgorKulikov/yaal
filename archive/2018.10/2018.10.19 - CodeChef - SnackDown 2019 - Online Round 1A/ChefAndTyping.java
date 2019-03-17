package net.egork;

import net.egork.collections.set.EHashSet;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Set;

public class ChefAndTyping {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        String[] words = in.readStringArray(n);
        Set<String> was = new EHashSet<>();
        int answer = 0;
        for (String word : words) {
            int current = 0;
            int lastType = -1;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                int type;
                if (c == 'f' || c == 'd') {
                    type = 0;
                } else {
                    type = 1;
                }
                if (type != lastType) {
                    current += 2;
                } else {
                    current += 4;
                }
                lastType = type;
            }
            if (was.contains(word)) {
                current /= 2;
            }
            was.add(word);
            answer += current;
        }
        out.printLine(answer);
    }
}
