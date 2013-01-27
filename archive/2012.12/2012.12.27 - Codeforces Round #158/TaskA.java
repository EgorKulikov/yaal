package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int a = in.readInt();
        int b = in.readInt();
        int n = in.readInt();
        boolean found = false;
        for (int i = 0; i < 10; i++) {
            if ((a * 10 + i) % b == 0) {
                a = a * 10 + i;
                found = true;
                break;
            }
        }
        if (!found) {
            out.printLine(-1);
            return;
        }
        out.print(a);
        for (int i = 1; i < n; i++)
            out.print(0);
        out.printLine();
    }
}
