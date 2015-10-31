package net.egork;

import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int height = in.readInt();
        int length = in.readInt();
        int heightScore = in.readInt() + in.readInt();
        int lengthScore = in.readInt() + in.readInt();
        double angle = Math.atan2(height, length);
        double answer = (heightScore * angle + lengthScore * (Math.PI / 2 - angle)) / Math.PI;
        out.printLine(answer);
    }
}
