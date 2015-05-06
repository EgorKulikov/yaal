package net.egork;

import net.egork.geometry.Point;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long x0 = in.readInt();
        long y0 = in.readInt();
        long x1 = in.readInt();
        long y1 = -in.readInt();
        long dx = x0 - x1;
        long dy = y0 - y1;
        out.print(dx * dx + dy * dy);
        out.print('.');
        for (int i = 0; i < 20; i++) {
            out.print('0');
        }
        out.printLine();
    }
}
