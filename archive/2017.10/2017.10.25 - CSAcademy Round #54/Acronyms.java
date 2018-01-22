package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.io.InputReader.readStringArray;

public class Acronyms {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        String[] words = in.readStringArray(n);
        int[] qty = new int[256];
        for (int i = 0; i < n; i++) {
            qty[words[i].charAt(0)]++;
        }
        int answer = 0;
        for (int i = 0; i < n; i++) {
            qty[words[i].charAt(0)]--;
            boolean bad = false;
            for (int j = 0; j < words[i].length(); j++) {
                if (--qty[words[i].charAt(j)] < 0) {
                    bad = true;
                }
            }
            for (int j = 0; j < words[i].length(); j++) {
                qty[words[i].charAt(j)]++;
            }
            qty[words[i].charAt(0)]++;
            if (!bad) {
                answer++;
            }
        }
        out.printLine(answer);
    }
}
