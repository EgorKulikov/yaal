package net.egork;

import net.egork.geometry.Polygon;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskP {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int r = in.readInt();
        double a = r;
        double aB = Math.PI / n;
        double aC = Math.PI / (2 * n);
        double aA = Math.PI - aB - aC;
        double ratio = a / Math.sin(aA);
        double b = ratio * Math.sin(aB);
        double c = ratio * Math.sin(aC);
        double h = sin(aC) * b;
        double answer = h * r * n;
        out.printLine(answer);
//        double p = (a + b + c) / 2;
//        out.printLine(2 * n * Math.sqrt(p * (p - a) * (p - b) * (p - c)));
    }
}
