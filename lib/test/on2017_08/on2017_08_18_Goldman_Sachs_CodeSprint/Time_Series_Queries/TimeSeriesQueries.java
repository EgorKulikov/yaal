package on2017_08.on2017_08_18_Goldman_Sachs_CodeSprint.Time_Series_Queries;





import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TimeSeriesQueries {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int q = in.readInt();
        int[] t = in.readIntArray(n);
        int[] p = in.readIntArray(n);
        IntList up = new IntArrayList();
        for (int i = 0; i < n; i++) {
            if (up.size() == 0 || p[up.last()] < p[i]) {
                up.add(i);
            }
        }
        IntList down = new IntArrayList();
        for (int i = n - 1; i >= 0; i--) {
            if (down.size() == 0 || p[down.last()] < p[i]) {
                down.add(i);
            }
        }
        down.inPlaceReverse();
        for (int i = 0; i < q; i++) {
            int type = in.readInt();
            int value = in.readInt();
            if (type == 1) {
                int left = 0;
                int right = up.size();
                while (left < right) {
                    int middle = (left + right) >> 1;
                    if (p[up.get(middle)] >= value) {
                        right = middle;
                    } else {
                        left = middle + 1;
                    }
                }
                if (left == up.size()) {
                    out.printLine(-1);
                } else {
                    out.printLine(t[up.get(left)]);
                }
            } else {
                int left = 0;
                int right = down.size();
                while (left < right) {
                    int middle = (left + right) >> 1;
                    if (t[down.get(middle)] >= value) {
                        right = middle;
                    } else {
                        left = middle + 1;
                    }
                }
                if (left == down.size()) {
                    out.printLine(-1);
                } else {
                    out.printLine(p[down.get(left)]);
                }
            }
        }
    }
}
