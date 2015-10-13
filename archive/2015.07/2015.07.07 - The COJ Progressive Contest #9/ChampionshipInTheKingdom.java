package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ChampionshipInTheKingdom {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        if (n > 2 * k) {
            out.printLine(n * k);
        } else {
            out.printLine(-1);
        }
    }
}
