package on2015_08.on2015_08_20_101_Hack_Aug_2015.Grid_Maze;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class GridMaze {
    int n, m;
    char[][] map;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        n = in.readInt();
        m = in.readInt();
        map = IOUtils.readTable(in, n, m);
        int[][] d1 = new int[n][m];
        ArrayUtils.fill(d1, Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 'S') {
                    d1[i][j] = 0;
                }
            }
        }
        findDistances(d1);
        int[][] d2 = new int[n][m];
        ArrayUtils.fill(d2, Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 'P') {
                    d2[i][j] = 0;
                }
            }
        }
        findDistances(d2);
        int[][] d3 = new int[n][m];
        ArrayUtils.fill(d3, Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            d3[i][0] = map[i][0] == '#' ? 1 : 0;
            d3[i][m - 1] = map[i][m - 1] == '#' ? 1 : 0;
        }
        for (int i = 0; i < m; i++) {
            d3[0][i] = map[0][i] == '#' ? 1 : 0;
            d3[n - 1][i] = map[n - 1][i] == '#' ? 1 : 0;
        }
        findDistances(d3);
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                answer = Math.min(answer, d1[i][j] + d2[i][j] + d3[i][j] - (map[i][j] == '#' ? 2 : 0));
            }
        }
        out.printLine(answer);
    }

    void findDistances(int[][] distances) {
        int[] curRowQueue = new int[n * m];
        int[] curColumnQueue = new int[n * m];
        int[] nextRowQueue = new int[n * m];
        int[] nextColumnQueue = new int[n * m];
        int size = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (distances[i][j] == 0) {
                    curRowQueue[size] = i;
                    curColumnQueue[size++] = j;
                }
            }
        }
        boolean first = true;
        while (size > 0 || first) {
            for (int i = 0; i < size; i++) {
                int row = curRowQueue[i];
                int column = curColumnQueue[i];
                for (int j = 0; j < 4; j++) {
                    int nRow = row + MiscUtils.DX4[j];
                    int nColumn = column + MiscUtils.DY4[j];
                    if (MiscUtils.isValidCell(nRow, nColumn, n, m) && map[nRow][nColumn] != '#' &&
                        distances[nRow][nColumn] > distances[row][column])
                    {
                        curRowQueue[size] = nRow;
                        curColumnQueue[size++] = nColumn;
                        distances[nRow][nColumn] = distances[row][column];
                    }
                }
            }
            int wasSize = size;
            size = 0;
            if (first) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        if (distances[i][j] == 1) {
                            nextRowQueue[size] = i;
                            nextColumnQueue[size++] = j;
                        }
                    }
                }
                first = false;
            }
            for (int i = 0; i < wasSize; i++) {
                int row = curRowQueue[i];
                int column = curColumnQueue[i];
                for (int j = 0; j < 4; j++) {
                    int nRow = row + MiscUtils.DX4[j];
                    int nColumn = column + MiscUtils.DY4[j];
                    if (MiscUtils.isValidCell(nRow, nColumn, n, m) && map[nRow][nColumn] == '#' &&
                        distances[nRow][nColumn] == Integer.MAX_VALUE)
                    {
                        nextRowQueue[size] = nRow;
                        nextColumnQueue[size++] = nColumn;
                        distances[nRow][nColumn] = distances[row][column] + 1;
                    }
                }
            }
            int[] temp = curRowQueue;
            curRowQueue = nextRowQueue;
            nextRowQueue = temp;
            temp = curColumnQueue;
            curColumnQueue = nextColumnQueue;
            nextColumnQueue = temp;
        }
    }
}
