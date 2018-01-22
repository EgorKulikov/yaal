package on2018_01.on2018_01_09_January_Challenge_2018.Rectangle;


import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.ArrayUtils.sort;

public class Rectangle {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int[] lengths = in.readIntArray(4);
        sort(lengths);
        out.printLine(lengths[0] == lengths[1] && lengths[2] == lengths[3] ? "YES" : "NO");
    }
}
