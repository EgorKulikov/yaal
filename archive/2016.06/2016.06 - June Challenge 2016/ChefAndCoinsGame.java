package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class ChefAndCoinsGame {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        if (n % 6 == 0) {
            out.printLine("Misha");
        } else {
            out.printLine("Chef");
        }
    }
}
