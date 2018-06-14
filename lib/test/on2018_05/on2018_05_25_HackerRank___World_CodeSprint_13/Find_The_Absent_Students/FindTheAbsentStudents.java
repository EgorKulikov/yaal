package on2018_05.on2018_05_25_HackerRank___World_CodeSprint_13.Find_The_Absent_Students;



import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class FindTheAbsentStudents {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = in.readIntArray(n);
        boolean[] present = new boolean[n + 1];
        for (int i : a) {
            present[i] = true;
        }
        IntList answer = new IntArrayList();
        for (int i = 1; i <= n; i++) {
            if (!present[i]) {
                answer.add(i);
            }
        }
        out.printLine(answer);
    }
}
