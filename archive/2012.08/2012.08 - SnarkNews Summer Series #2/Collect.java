package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Collect {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        int rowCount = in.readInt();
        int columnCount = in.readInt();
        char[][] map = IOUtils.readTable(in, rowCount, columnCount);
        int liftRow = -1;
        int liftColumn = -1;
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                if (map[i][j] == 'L') {
                    liftRow = i;
                    liftColumn = j;
                }
            }
        }
        int answer = 0;
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                if (map[i][j] == 'B')
                    answer += 2 * (Math.abs(liftRow - i) + Math.abs(liftColumn - j));
            }
        }
        out.printLine(answer);
    }
}
