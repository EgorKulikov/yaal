package net.egork;



import net.egork.misc.SimpleMapVisitor;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

import static net.egork.misc.ArrayUtils.fill;
import static net.egork.misc.MiscUtils.isValidCell;

public class RedKnightsShortestPath {
    int[] dRow = {-2, -2, 0, 2, 2, 0};
    int[] dCol = {-1, 1, 2, 1, -1, -2};
    String[] label = {"UL", "UR", "R", "LR", "LL", "L"};

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int sRow = in.readInt();
        int sCol = in.readInt();
        int fRow = in.readInt();
        int fCol = in.readInt();
        int[][] dst = new int[n][n];
        fill(dst, -1);
        SimpleMapVisitor visitor = new SimpleMapVisitor(n, n, dRow, dCol) {
            @Override
            protected void process(int row, int column, int fromRow, int fromColumn) {
                add(row, column);
                if (fromRow == -1) {
                    dst[row][column] = 0;
                } else {
                    dst[row][column] = dst[fromRow][fromColumn] + 1;
                }
            }
        };
        visitor.process(fRow, fCol);
        if (dst[sRow][sCol] == -1) {
            out.printLine("Impossible");
        } else {
            out.printLine(dst[sRow][sCol]);
            List<String> path = new ArrayList<>();
            while (sRow != fRow || sCol != fCol) {
                for (int i = 0; i < 6; i++) {
                    int nRow = sRow + dRow[i];
                    int nCol = sCol + dCol[i];
                    if (isValidCell(nRow, nCol, n, n) && dst[nRow][nCol] == dst[sRow][sCol] - 1) {
                        path.add(label[i]);
                        sRow = nRow;
                        sCol = nCol;
                        break;
                    }
                }
            }
            out.printLine(path.toArray());
        }
    }
}
