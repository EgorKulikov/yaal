package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Serpinski {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        double side = in.readInt();
        if (side == 0)
            throw new UnknownError();
        double x = in.readDouble();
        double y = in.readDouble();
        while (side > 1e-8) {
            double small = side / 3;
            if (x > small && x < 2 * small && y > small && y < 2 * small) {
                out.printLine(Math.min(Math.min(x - small, 2 * small - x), Math.min(y - small, 2 * small - y)));
                return;
            }
            while (x > small)
                x -= small;
            while (y > small)
                y -= small;
            side = small;
        }
        out.printLine(0);
    }
}
