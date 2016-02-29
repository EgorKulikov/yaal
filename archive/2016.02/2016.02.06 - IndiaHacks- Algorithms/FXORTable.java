package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class FXORTable {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int p = in.readInt();
        int x = in.readInt();
        int[] a = new int[m];
        for (int i = 0; i < m; i++) {
            a[i] = in.readCharacter() - '0';
        }
        int[] next = new int[m];
        int at = n - 1;
        for (int i = 29; i >= 0; i--) {
            int shift = 1 << i;
            if (at >= shift) {
                at -= shift;
                int totalShift = (int) ((long)shift * p % m);
                for (int j = 0; j < m; j++) {
                    int id = j - totalShift;
                    if (id < 0) {
                        id += m;
                    }
                    next[j] = a[j] ^ a[id];
                }
                int[] temp = a;
                a = next;
                next = temp;
            }
        }
        for (int i : a) {
            out.print(i ^ (n == 1 ? 0 : x));
        }
        out.printLine();
    }
}
