package on2015_08.on2015_08_10_SNSS_2015_R2.A___________________;



import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        char[] type = IOUtils.readCharArray(in, n);
        char[] first = new char[m];
        int[] length = new int[m];
        for (int i = 0; i < m; i++) {
            first[i] = in.readCharacter();
            length[i] = in.readInt();
        }
        char[] best = new char[n];
        MiscUtils.decreaseByOne(length);
        for (int i = 0; i < m; i++) {
            best[length[i]] = (char) Math.max(best[length[i]], first[i]);
        }
        int answer = 0;
        for (int i = 0; i < n; i++) {
            if (best[i] >= type[i]) {
                answer++;
            }
        }
        out.printLine(answer);
    }
}
