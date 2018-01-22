package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.io.InputReader.readIntArray;

public class VideoGame {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int h = in.readInt();
        int[] qty = in.readIntArray(n);
        int at = 0;
        boolean box = false;
        while (true) {
            int command = in.readInt();
            boolean stop = false;
            switch (command) {
            case 1:
                at--;
                at = Math.max(at, 0);
                break;
            case 2:
                at++;
                at = Math.min(at, n - 1);
                break;
            case 3:
                if (!box && qty[at] != 0) {
                    qty[at]--;
                    box = true;
                }
                break;
            case 4:
                if (box && qty[at] < h) {
                    qty[at]++;
                    box = false;
                }
                break;
            case 0:
                stop = true;
                break;
            }
            if (stop) {
                break;
            }
        }
        out.printLine(qty);
    }
}
