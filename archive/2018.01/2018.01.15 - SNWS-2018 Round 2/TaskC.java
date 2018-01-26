package net.egork;



import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.abs;
import static net.egork.misc.ArrayUtils.fill;
import static net.egork.misc.MiscUtils.DX_KNIGHT;
import static net.egork.misc.MiscUtils.DY_KNIGHT;
import static net.egork.misc.MiscUtils.isValidCell;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int sx = in.readInt();
        int sy = in.readInt();
        int fx = in.readInt();
        int fy = in.readInt();
        int[][] result = new int[17][17];
        fill(result, MAX_VALUE);
        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 17; j++) {
                int x = fx + i - 8;
                int y = fy + j - 8;
                if (!isValidCell(x, y, n, m)) {
                    continue;
                }
                result[i][j] = get(abs(x - sx), abs(y - sy));
            }
        }
        while (true) {
            boolean updated = false;
            for (int i = 0; i < 17; i++) {
                for (int j = 0; j < 17; j++) {
                    int x = fx + i - 8;
                    int y = fy + j - 8;
                    if (!isValidCell(x, y, n, m)) {
                        continue;
                    }
                    for (int k = 0; k < 8; k++) {
                        int ni = i + DX_KNIGHT[k];
                        int nj = j + DY_KNIGHT[k];
                        int nx = fx + ni - 8;
                        int ny = fy + nj - 8;
                        if (!isValidCell(ni, nj, 17, 17) || !isValidCell(nx, ny, n, m)) {
                            continue;
                        }
                        if (result[ni][nj] - result[i][j] > 1) {
                            result[ni][nj] = result[i][j] + 1;
                            updated = true;
                        }
                    }
                }
            }
            if (!updated) {
                break;
            }
        }
        out.printLine(result[8][8]);
    }

    private int get(int x, int y) {
        if (x > y) {
            return get(y, x);
        }
        if (2 * x <= y) {
            int result = x;
            y -= 2 * x;
            x = 0;
            if (y % 4 == 0) {
                return result + y / 2;
            } else {
                return MAX_VALUE;
            }
        } else {
            int result = y - x;
            int current = 2 * x - y;
            if (current % 3 == 0) {
                return result + (current / 3) * 2;
            } else {
                return MAX_VALUE;
            }
        }
    }
}
