package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    int[][] moves;
    int[][][][][] result;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int width = in.readInt();
        int height = in.readInt();
        moves = new int[2][];
        moves[0] = IOUtils.readIntArray(in, 2);
        moves[1] = IOUtils.readIntArray(in, 2);
        int x0 = in.readInt() - 1;
        int y0 = in.readInt() - 1;
        int x1 = in.readInt() - 1;
        int y1 = in.readInt() - 1;
        result = new int[2][width][height][width][height];
        Last[][][][][] last = new Last[2][width][height][width][height];
        int[] moveQueue = new int[width * height * width * height];
        int[] x0Queue = new int[width * height * width * height];
        int[] y0Queue = new int[width * height * width * height];
        int[] x1Queue = new int[width * height * width * height];
        int[] y1Queue = new int[width * height * width * height];
        x0Queue[0] = x0;
        y0Queue[0] = y0;
        x1Queue[0] = x1;
        y1Queue[0] = y1;
        int size = 1;
        ArrayUtils.fill(result, -1);
        result[0][x0][y0][x1][y1] = 0;
        for (int i = 0; i < size; i++) {
            x0 = x0Queue[i];
            y0 = y0Queue[i];
            x1 = x1Queue[i];
            y1 = y1Queue[i];
            int cMove = moveQueue[i];
            if (x0 == x1 && y0 == y1 && moveQueue[i] == 0) {
                out.printLine(result[0][x0][y0][x1][y1] / 2);
                Last[] moves = new Last[result[0][x0][y0][x1][y1] / 2 + 1];
                moves[moves.length - 1] = new Last(x0, y0, x1, y1);
                for (int j = result[0][x0][y0][x1][y1] - 1; j >= 0; j--) {
                    Last cLast = last[cMove][x0][y0][x1][y1];
                    cMove = 1 - cMove;
                    x0 = cLast.x0;
                    y0 = cLast.y0;
                    x1 = cLast.x1;
                    y1 = cLast.y1;
                    if ((j & 1) == 0) {
                        moves[j >> 1] = new Last(x0, y0, x1, y1);
                    }
                }
                for (Last move : moves) {
                    out.printLine(move.x0 + 1, move.y0 + 1, move.x1 + 1, move.y1 + 1);
                }
                return;
            }
            for (int dir0 = 0; dir0 < 2; dir0++) {
                for (int dx0 = -1; dx0 <= 1; dx0 += 2) {
                    int nx0 = x0 + moves[cMove][dir0] * dx0;
                    if (nx0 < 0 || nx0 >= width) {
                        continue;
                    }
                    for (int dy0 = -1; dy0 <= 1; dy0 += 2) {
                        int ny0 = y0 + moves[cMove][1 - dir0] * dy0;
                        if (ny0 < 0 || ny0 >= height) {
                            continue;
                        }
                        if (result[1 - cMove][x1][y1][nx0][ny0] == -1) {
                            result[1 - cMove][x1][y1][nx0][ny0] = result[cMove][x0][y0][x1][y1] + 1;
                            last[1 - cMove][x1][y1][nx0][ny0] = new Last(x0, y0, x1, y1);
                            x0Queue[size] = x1;
                            y0Queue[size] = y1;
                            x1Queue[size] = nx0;
                            y1Queue[size] = ny0;
                            moveQueue[size++] = 1 - cMove;
                        }
                    }
                }
            }
        }
        out.printLine(-1);
    }

    static class Last {
        int x0;
        int y0;
        int x1;
        int y1;

        public Last(int x0, int y0, int x1, int y1) {
            this.x0 = x0;
            this.y0 = y0;
            this.x1 = x1;
            this.y1 = y1;
        }
    }
}
