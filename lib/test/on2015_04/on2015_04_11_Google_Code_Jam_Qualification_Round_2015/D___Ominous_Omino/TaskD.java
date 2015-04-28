package on2015_04.on2015_04_11_Google_Code_Jam_Qualification_Round_2015.D___Ominous_Omino;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import net.egork.concurrency.Scheduler;
import net.egork.concurrency.Task;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
            int size;
            int rowCount;
            int columnCount;
            boolean answer;

            @Override
            public void read(InputReader in) {
                size = in.readInt();
                rowCount = in.readInt();
                columnCount = in.readInt();
            }

            @Override
            public void solve() {
                if (rowCount * columnCount % size != 0) {
                    answer = false;
                    return;
                }
                if (rowCount > columnCount) {
                    int temp = rowCount;
                    rowCount = columnCount;
                    columnCount = temp;
                }
                if (size == 1) {
                    answer = true;
                } else if (size == 2) {
                    answer = true;
                } else if (size == 3) {
                    answer = rowCount >= 2;
                } else if (size == 4) {
                    answer = rowCount >= 3;
                } else if (size == 5) {
                    answer = rowCount >= 4 || columnCount >= 10 && rowCount == 3;
                } else if (size == 6) {
                    answer = rowCount >= 4;
                } else {
                    answer = false;
                }
            }

            @Override
            public void write(OutputWriter out, int testNumber) {
                out.printLine("Case #" + testNumber + ":", answer ? "GABRIEL" : "RICHARD");
            }
        }, 4);
    }
}
