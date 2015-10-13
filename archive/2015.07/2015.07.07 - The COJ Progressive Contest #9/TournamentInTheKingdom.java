package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TournamentInTheKingdom {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        out.print("Caballero -->");
        for (int i = 1; i <= count; i++) {
            out.print("    ");
            if (i < 10) {
                out.print(" ");
            }
            out.print(i);
            out.print(" ");
        }
        out.printLine();
        int knights = count + (count & 1);
        int mod = knights - 1;
        for (int i = 1; i < knights; i++) {
            out.print("Ronda    ");
            if (i < 10) {
                out.print(" ");
            }
            out.print(i);
            out.print(": ");
            int special = -1;
            for (int j = 1; j <= count; j++) {
                int opponent = i - j;
                if (opponent <= 0) {
                    opponent += mod;
                }
                if (opponent == j) {
                    special = j;
                    opponent = knights;
                }
                if (j == knights) {
                    opponent = special;
                }
                out.print("    ");
                if (opponent > count) {
                    out.print(" -");
                    opponent = -1;
                } else {
                    if (opponent < 10) {
                        out.print(" ");
                    }
                    out.print(opponent);
                }
                out.print(" ");
            }
            out.printLine();
        }
    }
}
