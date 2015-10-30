package on2015_10.on2015_10_04_Grand_Prix_of_Eurasia_2015._4______________________;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Task4 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int d = in.readInt();
        int n = in.readInt();
        int[] s = new int[n];
        int[] p = new int[n];
        int[] sigma = new int[n];
        IOUtils.readIntArrays(in, s, p, sigma);
        for (int i = 0; i < n; i++) {
            p[i] *= s[i];
            sigma[i] *= s[i];
        }
        ArrayUtils.orderBy(p, sigma);
        double left = 0;
        double right = 1e6;
        for (int i = 0; i < 50; i++) {
            double mid = (left + right) / 2;
            double weight = mid;
            double length = 0;
            for (int j = 0; j < n; j++) {
                if (sigma[j] > weight) {
                    length += (sigma[j] - weight) / p[j];
                    weight = sigma[j];
                }
            }
            if (length >= d) {
                left = mid;
            } else {
                right = mid;
            }
        }
        out.printLine((left + right) / 2);
    }
}
