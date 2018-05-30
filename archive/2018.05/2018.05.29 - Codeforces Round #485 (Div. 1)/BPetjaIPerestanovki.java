package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.ArrayUtils.getOddity;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class BPetjaIPerestanovki {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] order = in.readIntArray(n);
        decreaseByOne(order);
        if (getOddity(order) ^ n % 2 == 0) {
            out.printLine("Petr");
        } else {
            out.printLine("Um_nik");
        }
    }
}
