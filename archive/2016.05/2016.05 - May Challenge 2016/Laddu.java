package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static java.lang.Math.max;

public class Laddu {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        boolean isIndian = in.readString().equals("INDIAN");
        int ladus = 0;
        for (int i = 0; i < n; i++) {
            String type = in.readString();
            switch (type) {
            case "CONTEST_WON":
                ladus += 300 + max(0, 20 - in.readInt());
                break;
            case "TOP_CONTRIBUTOR":
                ladus += 300;
                break;
            case "BUG_FOUND":
                ladus += in.readInt();
                break;
            case "CONTEST_HOSTED":
                ladus += 50;
                break;
            }
        }
        out.printLine(ladus / (isIndian ? 200 : 400));
    }
}
