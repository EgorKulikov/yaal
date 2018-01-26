package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int remaining = in.readInt();
        int odd = 0;
        int even = 0;
        for (int i = 0; i < count; i++) {
            if (in.readInt() % 2 == 0) {
                even++;
            } else {
                odd++;
            }
        }
        if (count == remaining) {
            if (odd % 2 == 0) {
                out.printLine("Daenerys");
            } else {
                out.printLine("Stannis");
            }
        }
        int stMoves = (count - remaining + 1) / 2;
        int daMoves = (count - remaining) / 2;
        if (stMoves == daMoves) {
            if (remaining % 2 == 0 || stMoves < even) {
                out.printLine("Daenerys");
            } else {
                out.printLine("Stannis");
            }
        } else {
            if (daMoves < odd && (remaining % 2 == 1 || daMoves < even)) {
                out.printLine("Stannis");
            } else {
                out.printLine("Daenerys");
            }
        }
    }
}
