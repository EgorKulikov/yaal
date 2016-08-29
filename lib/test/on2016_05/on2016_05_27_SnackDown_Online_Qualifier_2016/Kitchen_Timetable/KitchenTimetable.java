package on2016_05.on2016_05_27_SnackDown_Online_Qualifier_2016.Kitchen_Timetable;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static net.egork.io.IOUtils.readIntArray;
import static net.egork.misc.ArrayUtils.concatenate;

public class KitchenTimetable {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = concatenate(new int[]{0}, readIntArray(in, n));
        int[] b = readIntArray(in, n);
        int answer = 0;
        for (int i = 0; i < n; i++) {
            if (a[i + 1] - a[i] >= b[i]) {
                answer++;
            }
        }
        out.printLine(answer);
    }
}
