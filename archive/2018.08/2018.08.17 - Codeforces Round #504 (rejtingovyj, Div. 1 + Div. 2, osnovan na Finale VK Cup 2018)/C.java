package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class C {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        char[] s = in.readCharArray(n);
        int delta = 0;
        int remaining = k;
        for (int i = 0; i < n; i++) {
            if (delta == remaining) {
                out.print(new String(s, 0, i));
                for (int j = 0; j < remaining; j++) {
                    out.print(')');
                }
                out.printLine();
                return;
            }
            delta += s[i] == '(' ? 1 : -1;
            remaining--;
        }
    }
}
