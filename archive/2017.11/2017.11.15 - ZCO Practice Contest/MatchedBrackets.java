package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.io.InputReader.readIntArray;

public class MatchedBrackets {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] brackets = in.readIntArray(n);
        int maxDepth = 0;
        int depthAt = -1;
        int maxLength = 0;
        int lengthAt = -1;
        int[] start = new int[n];
        int depth = 0;
        for (int i = 0; i < n; i++) {
            if (brackets[i] == 1) {
                start[depth++] = i;
                if (depth > maxDepth) {
                    maxDepth = depth;
                    depthAt = i + 1;
                }
            } else {
                depth--;
                if (i - start[depth] > maxLength) {
                    maxLength = i - start[depth];
                    lengthAt = start[depth] + 1;
                }
            }
        }
        out.printLine(maxDepth, depthAt, maxLength + 1, lengthAt);
    }
}
