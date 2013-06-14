package on2013_01.on2013_01_14_SnarkNews_Winter_Series_Round__2.Drunk;



import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Drunk {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int rowCount = in.readInt();
        int columnCount = in.readInt();
        int startRow = in.readInt();
        int startColumn = in.readInt();
        int endRow = in.readInt();
        int endColumn = in.readInt();
        int moveCount = in.readInt();
        double[][] moves = new double[rowCount][columnCount];
        ArrayUtils.fill(moves, .25);
        Arrays.fill(moves[0], 1d / 3);
        Arrays.fill(moves[rowCount - 1], 1d / 3);
        for (int i = 1; i < rowCount; i++)
            moves[i][0] = moves[i][columnCount - 1] = 1d / 3;
        moves[0][0] = moves[0][columnCount - 1] = moves[rowCount - 1][0] = moves[rowCount - 1][columnCount - 1] = .5;
        double[][] current = new double[rowCount][columnCount];
        double[][] next = new double[rowCount][columnCount];
        current[startRow][startColumn] = 1;
        int[][][] rows = new int[rowCount][columnCount][];
        int[][][] columns = new int[rowCount][columnCount][];
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                int size = 4;
                if (i == 0 || i == rowCount - 1)
                    size--;
                if (j == 0 || j == columnCount - 1)
                    size--;
                rows[i][j] = new int[size];
                columns[i][j] = new int[size];
                for (int k = 0; k < 4; k++) {
                    int row = MiscUtils.DX4[k] + i;
                    int column = MiscUtils.DY4[k] + j;
                    if (row >= 0 && row < rowCount && column >= 0 && column < columnCount) {
                        rows[i][j][--size] = row;
                        columns[i][j][size] = column;
                    }
                }
            }
        }
        for (int i = 0; i < moveCount; i++) {
            ArrayUtils.fill(next, 0);
            for (int j = 0; j < rowCount; j++) {
                for (int k = 0; k < columnCount; k++) {
                    if (j == endRow && k == endColumn) {
                        next[j][k] += current[j][k];
                        continue;
                    }
                    for (int l = 0; l < rows[j][k].length; l++)
                        next[rows[j][k][l]][columns[j][k][l]] += current[j][k] * moves[j][k];
                }
            }
            double[][] temp = current;
            current = next;
            next = temp;
        }
        out.printLine(current[endRow][endColumn]);
    }
}
