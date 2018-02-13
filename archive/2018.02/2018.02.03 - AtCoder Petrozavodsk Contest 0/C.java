package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class C {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        out.printLine(0);
        out.flush();
        String type = in.readString();
        if (type.equals("Vacant")) {
            return;
        }
        String leftType = type;
        String rightType = type;
        int left = 0;
        int right = n;
        while (true) {
            int middle = (left + right) >> 1;
            out.printLine(middle);
            out.flush();
            type = in.readString();
            if (type.equals("Vacant")) {
                return;
            }
            if ((middle - left) % 2 == 0 ^ leftType.equals(type)) {
                rightType = type;
                right = middle;
            } else {
                leftType = type;
                left = middle;
            }
        }
    }
}
