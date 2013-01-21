package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.io.IOUtils;
import net.egork.misc.MapVisitor;
import net.egork.misc.SimpleMapVisitor;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Island {
    int count = -1;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int rowCount = in.readInt();
        int columnCount = in.readInt();
        final char[][] map = IOUtils.readTable(in, rowCount, columnCount);
        final IntList rows = new IntArrayList();
        final IntList columns = new IntArrayList();
        new SimpleMapVisitor(rowCount, columnCount) {
            @Override
            public void process(int row, int column, int fromRow, int fromColumn) {
                if (map[row][column] != 'X')
                    return;
                if (fromRow == -1) {
                    count++;
                    rows.add(row);
                    columns.add(column);
                }
                map[row][column] = (char) count;
                add(row, column);
            }
        }.processAll();
        count++;
        final int[][] distance = new int[count][count];
        ArrayUtils.fill(distance, Integer.MAX_VALUE);
        final int[][] curDistance = new int[rowCount][columnCount];
        for (int i = 0; i < count; i++) {
            int startRow = rows.get(i);
            int startColumn = columns.get(i);
            ArrayUtils.fill(curDistance, Integer.MAX_VALUE);
            curDistance[startRow][startColumn] = 0;
            final int currentIsland = i;
            new MapVisitor(rowCount, columnCount){
                @Override
                protected void process(int row, int column, int fromRow, int fromColumn) {
                    if (fromRow == -1) {
                        add(row, column);
                        return;
                    }
                    if (map[row][column] == '.')
                        return;
                    int d = curDistance[fromRow][fromColumn] + (map[row][column] == 'S' ? 1 : 0);
                    if (curDistance[row][column] > d) {
                        curDistance[row][column] = d;
                        if (map[row][column] != 'S') {
                            distance[currentIsland][map[row][column]] = Math.min(distance[currentIsland][map[row][column]], d);
                            addFront(row, column);
                        } else
                            add(row, column);
                    }
                }
            }.process(startRow, startColumn);
        }
        int[][] answer = new int[count][1 << count];
        for (int i = 1; i < (1 << count); i++) {
            if (Integer.bitCount(i) == 1)
                continue;
            for (int j = 0; j < count; j++) {
                if ((i >> j & 1) == 0)
                    continue;
                answer[j][i] = Integer.MAX_VALUE;
                for (int k = 0; k < count; k++) {
                    if (k == j || (i >> k & 1) == 0)
                        continue;
                    answer[j][i] = Math.min(answer[j][i], answer[k][i - (1 << j)] + distance[j][k]);
                }
            }
        }
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < count; i++)
            result = Math.min(result, answer[i][(1 << count) - 1]);
        out.printLine(result);
    }
}
