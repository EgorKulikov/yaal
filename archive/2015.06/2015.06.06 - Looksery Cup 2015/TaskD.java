package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int rowCount = in.readInt();
        int columnCount = in.readInt();
        char[][] table = IOUtils.readTable(in, rowCount, columnCount);
        int answer = 0;
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                if (i == rowCount - 1) {
                    if (j == columnCount - 1) {
                        answer++;
                    } else {
                        if (table[i][j] != table[i][j + 1]) {
                            answer++;
                        }
                    }
                } else {
                    if (j == columnCount - 1) {
                        if (table[i][j] != table[i + 1][j]) {
                            answer++;
                        }
                    } else {
                        if (table[i][j] == table[i + 1][j]) {
                            if (table[i][j + 1] != table[i + 1][j + 1]) {
                                answer++;
                            }
                        } else {
                            if (table[i][j] != table[i][j + 1] || table[i + 1][j] != table[i + 1][j + 1]) {
                                answer++;
                            }
                        }
                    }
                }
            }
        }
        out.printLine(answer);
    }
}
