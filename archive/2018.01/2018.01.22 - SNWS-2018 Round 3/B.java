package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class B {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int ks = in.readInt();
        int[] s = in.readIntArray(ks);
        int kh = in.readInt();
        int[] h = in.readIntArray(kh);
        int[] mask = new int[1000000];
        for (int i : s) {
            mask[i] |= 1;
        }
        for (int i : h) {
            mask[i] |= 2;
        }
        int has = 0;
        int answer = 0;
        for (int i = 0; i < 1000000; i++) {
            if (mask[i] != 0 && (mask[i] & has) == has) {
                answer++;
                has = 3 - mask[i];
            }
        }
        out.printLine(answer);
    }
}
