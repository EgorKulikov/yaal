package on2016_02.on2016_02_13_8VC_Venture_Cup_2016___Elimination_Round.D___Jerry_s_Protest;



import net.egork.generated.collections.comparator.IntComparator;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = readIntArray(in, n);
        sort(a, IntComparator.DEFAULT);
        double[] diff = new double[5000];
        double quant = 2d / n / (n - 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                diff[a[i] - a[j]] += quant;
            }
        }
        double[] sum = new double[5000];
        for (int i = 0; i < 5000; i++) {
            for (int j = 0; i + j < 5000; j++) {
                sum[i + j] += diff[i] * diff[j];
            }
        }
        double prefix = 0;
        double answer = 0;
        for (int i = 0; i < 5000; i++) {
            answer += diff[i] * prefix;
            prefix += sum[i];
        }
        out.printLine(answer);
    }
}
