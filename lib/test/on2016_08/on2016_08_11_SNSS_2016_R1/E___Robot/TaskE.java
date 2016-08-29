package on2016_08.on2016_08_11_SNSS_2016_R1.E___Robot;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static net.egork.io.IOUtils.readTable;
import static net.egork.misc.MiscUtils.isValidCell;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int m = in.readInt();
        int k = in.readInt();
        char[][] map = readTable(in, m, m);
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j <= i; j++) {
                boolean[][] subMap = new boolean[j + 1][i - j + 1];
                for (int l = 0; l * j < m && l * (i - j) < m; l++) {
                    for (int n = 0; n <= j; n++) {
                        for (int o = 0; o <= i - j; o++) {
                            int row = l * j + n;
                            int column = l * (i - j) + o;
                            if (isValidCell(row, column, m, m)) {
                                subMap[n][o] |= map[row][column] == 'X';
                            }
                        }
                    }
                }
                if (subMap[0][0] || subMap[j][i - j]) {
                    continue;
                }
                boolean[][] reachable = new boolean[j + 1][i - j + 1];
                reachable[j][i - j] = true;
                for (int l = j; l >= 0; l--) {
                    for (int n = i - j; n >= 0; n--) {
                        if (reachable[l][n]) {
                            if (l > 0 && !subMap[l - 1][n]) {
                                reachable[l - 1][n] = true;
                            }
                            if (n > 0 && !subMap[l][n - 1]) {
                                reachable[l][n - 1] = true;
                            }
                        }
                    }
                }
                if (!reachable[0][0]) {
                    continue;
                }
                char[] answer = new char[i];
                int row = 0;
                int column = 0;
                for (int l = 0; l < i; l++) {
                    if (column < i - j && reachable[row][column + 1]) {
                        answer[l] = '0';
                        column++;
                    } else {
                        answer[l] = '1';
                        row++;
                    }
                }
                out.printLine(answer);
            }
        }
    }
}
