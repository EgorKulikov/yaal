package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int single = in.readInt();
        int mass = in.readInt();
        while (mass != 0) {
            int current = mass % single;
            if (current != 0 && current != 1 && current != single - 1) {
                out.printLine("NO");
                return;
            }
            mass /= single;
            if (current == single - 1 && current != 1) {
                mass++;
            }
        }
        out.printLine("YES");
    }
}
