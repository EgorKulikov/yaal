package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class PlacingKnights {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int rowCount = in.readInt();
        int columnCount = in.readInt();
        if (rowCount > columnCount) {
            int temp = rowCount;
            rowCount = columnCount;
            columnCount = temp;
        }
        int answer;
        if (rowCount == 1) {
            answer = columnCount;
        } else if (rowCount == 2) {
            answer = columnCount / 4 * 4 + Math.min(columnCount % 4, 2) * 2;
        } else {
            answer = (rowCount * columnCount + 1) / 2;
        }
        out.printLine("Case", testNumber + ":", answer);
    }
}
