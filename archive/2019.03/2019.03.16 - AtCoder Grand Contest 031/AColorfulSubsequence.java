package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.MiscUtils.MOD7;

public class AColorfulSubsequence {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        char[] s = in.readCharArray(n);
        int[] qty = new int[26];
        for (char c : s) {
            qty[c - 'a']++;
        }
        long answer = 1;
        for (int i = 0; i < 26; i++) {
            answer *= qty[i] + 1;
            answer %= MOD7;
        }
        answer--;
        out.printLine(answer);
    }
}
