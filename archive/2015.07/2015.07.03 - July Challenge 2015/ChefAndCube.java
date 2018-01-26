package net.egork;

import net.egork.io.IOUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class ChefAndCube {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String[] names = IOUtils.readStringArray(in, 6);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    if (names[i].equals(names[2 + j]) && names[i].equals(names[4 + k])) {
                        out.printLine("YES");
                        return;
                    }
                }
            }
        }
        out.printLine("NO");
    }
}
