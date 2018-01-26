package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import net.egork.concurrency.Scheduler;
import net.egork.concurrency.Task;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
            int rowCount;
            int columnCount;
            int count;

            int answer;

            @Override
            public void read(InputReader in) {
                rowCount = in.readInt();
                columnCount = in.readInt();
                count = in.readInt();
            }

            @Override
            public void solve() {
                if (2 * count <= rowCount * columnCount + 1) {
                    answer = 0;
                    return;
                }
                answer = rowCount * (columnCount - 1) + columnCount * (rowCount - 1);
                int free = rowCount * columnCount - count;
                if (rowCount == 1 || columnCount == 1) {
                    answer -= free * 2;
                    return;
                }
                if (2 * count == rowCount * columnCount + 3) {
                    answer = 3;
                    return;
                }
                int times4 = ((rowCount - 2) * (columnCount - 2) + 1) / 2;
                answer -= Math.min(times4, free) * 4;
                free -= Math.min(times4, free);
                int times3 = ((rowCount - 2) / 2 + (columnCount - 2) / 2) * 2;
                if (rowCount % 2 + columnCount % 2 == 1) {
                    times3++;
                }
                answer -= Math.min(times3, free) * 3;
                free -= Math.min(times3, free);
                answer -= free * 2;
            }

            @Override
            public void write(OutputWriter out, int testNumber) {
                out.printLine("Case #" + testNumber + ":", answer);
            }
        }, 4);
    }
}
