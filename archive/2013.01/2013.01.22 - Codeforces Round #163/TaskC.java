package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int[] row = new int[count - 1];
        int[] column = new int[count - 1];
        IOUtils.readIntArrays(in, row, column);
        MiscUtils.decreaseByOne(row, column);
        int[] queue = new int[count - 1];
        int size = 0;
        int nextRow = count - 1;
        int nextColumn = 0;
        List<Swap> answer = new ArrayList<Swap>();
        boolean[] added = new boolean[count - 1];
        for (int i = 0; i < count - 1; i++) {
            if (size == i) {
                for (int j = 0; j < count - 1; j++) {
                    if (!added[j]) {
                        added[j] = true;
                        queue[size++] = j;
                        break;
                    }
                }
            }
            int currentRow = row[queue[i]];
            int currentColumn = column[queue[i]];
            if (currentRow <= nextRow) {
                if (currentRow != nextRow)
                    answer.add(new Swap(1, currentRow + 1, nextRow + 1));
                for (int j = 0; j < count - 1; j++) {
                    if (currentRow == row[j]) {
                        row[j] = nextRow;
                        if (!added[j]) {
                            queue[size++] = j;
                            added[j] = true;
                        }
                    } else if (nextRow == row[j])
                        row[j] = currentRow;
                }
                nextRow--;
            }
            if (currentColumn >= nextColumn) {
                if (currentColumn != nextColumn)
                    answer.add(new Swap(2, currentColumn + 1, nextColumn + 1));
                for (int j = 0; j < count - 1; j++) {
                    if (currentColumn == column[j]) {
                        column[j] = nextColumn;
                        if (!added[j]) {
                            queue[size++] = j;
                            added[j] = true;
                        }
                    } else if (nextColumn == column[j])
                        column[j] = currentColumn;
                }
                nextColumn++;
            }
        }
        for (int i = 0, j = nextColumn - 1; i < j; i++, j--)
            answer.add(new Swap(2, i + 1, j + 1));
        out.printLine(answer.size());
        for (Swap swap : answer)
            out.printLine(swap.type, swap.from, swap.to);
    }

    static class Swap {
        final int type, from, to;

        Swap(int type, int from, int to) {
            this.type = type;
            this.from = from;
            this.to = to;
        }
    }
}
