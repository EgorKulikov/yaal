package on2016_08.on2016_08_22_SNSS_2016_R4.E___Map;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static net.egork.io.IOUtils.readIntArrays;
import static net.egork.io.IOUtils.readTable;
import static net.egork.misc.MiscUtils.*;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int k = in.readInt();
        char[][] map = readTable(in, n, m);
        int[] r = new int[k];
        int[] c = new int[k];
        int[] d = new int[k];
        readIntArrays(in, r, c, d);
        decreaseByOne(r, c);
        int[][] number = new int[n][m];
        for (int i = 0; i < k; i++) {
            if (map[r[i]][c[i]] == 'W') {
                out.printLine("Fake");
                return;
            }
            number[r[i]][c[i]] = d[i];
        }
        int[] rq = new int[n * m];
        int[] cq = new int[n * m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 'W') {
                    continue;
                }
                map[i][j] = 'W';
                rq[0] = i;
                cq[0] = j;
                int id = -1;
                int size = 1;
                for (int l = 0; l < size; l++) {
                    int row = rq[l];
                    int col = cq[l];
                    if (number[row][col] != 0) {
                        if (id != -1) {
                            out.printLine("Fake");
                            return;
                        }
                        id = number[row][col];
                    }
                    for (int o = 0; o < 4; o++) {
                        int nrow = row + DX4[o];
                        int ncol = col + DY4[o];
                        if (isValidCell(nrow, ncol, n, m) && map[nrow][ncol] == '.') {
                            map[nrow][ncol] = 'W';
                            rq[size] = nrow;
                            cq[size++] = ncol;
                        }
                    }
                }
                if (id != size) {
                    out.printLine("Fake");
                    return;
                }
            }
        }
        out.printLine("True");
    }
}
