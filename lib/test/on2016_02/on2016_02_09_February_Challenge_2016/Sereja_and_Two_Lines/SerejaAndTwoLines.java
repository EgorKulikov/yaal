package on2016_02.on2016_02_09_February_Challenge_2016.Sereja_and_Two_Lines;



import net.egork.collections.intcollection.IntHashMap;
import net.egork.collections.map.CPPMap;
import net.egork.collections.map.Counter;
import net.egork.generated.collections.iterator.IntIterator;
import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Map;

import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class SerejaAndTwoLines {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int[][] a = readIntTable(in, n, m);
        int count = compress(a).length;
        Counter<Integer>[] rows = new Counter[count];
        Counter<Integer>[] columns = new Counter[count];
        for (int i = 0; i < count; i++) {
            rows[i] = new Counter<>();
            columns[i] = new Counter<>();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                rows[a[i][j]].add(i);
                columns[a[i][j]].add(j);
            }
        }
        long answer = 0;
        for (int i = 0; i < count; i++) {
            if (rows[i].size() == 1 && columns[i].size() == 1) {
                answer = Math.max(answer, 1);
                continue;
            }
            long maxRow = 0;
            IntList rowsAt = null;
            for (Map.Entry<Integer, Long> entry : rows[i].entrySet()) {
                if (entry.getValue() > maxRow) {
                    maxRow = entry.getValue();
                    rowsAt = new IntArrayList();
                    rowsAt.add(entry.getKey());
                } else if (entry.getValue() == maxRow) {
                    rowsAt.add(entry.getKey());
                }
            }
            long maxColumn = 0;
            IntList colsAt = null;
            for (Map.Entry<Integer, Long> entry : columns[i].entrySet()) {
                if (entry.getValue() > maxColumn) {
                    maxColumn = entry.getValue();
                    colsAt = new IntArrayList();
                    colsAt.add(entry.getKey());
                } else if (entry.getValue() == maxColumn) {
                    colsAt.add(entry.getKey());
                }
            }
            if (maxRow + maxColumn <= answer) {
                continue;
            }
            boolean found = false;
            for (int j : rowsAt) {
                for (int k : colsAt) {
                    if (a[j][k] != i) {
                        found = true;
                        break;
                    }
                }
                if (found) {
                    break;
                }
            }
            if (found) {
                answer = maxRow + maxColumn;
            } else {
                answer = maxRow + maxColumn - 1;
            }
        }
        out.printLine(answer);
    }
}
