package on2015_08.on2015_08_20_101_Hack_Aug_2015.Bags_of_Apples;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class BagsOfApples {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = IOUtils.readIntArray(in, n);
        int answer = 0;
        int sum = (int) ArrayUtils.sumArray(a);
        if (sum % 3 == 0) {
            answer = sum;
        } else {
            for (int i = 0; i < n; i++) {
                if ((sum - a[i]) % 3 == 0) {
                    answer = Math.max(answer, sum - a[i]);
                }
                for (int j = 0; j < i; j++) {
                    if ((sum - a[i] - a[j]) % 3 == 0) {
                        answer = Math.max(answer, sum - a[i] - a[j]);
                    }
                }
            }
        }
        out.printLine(answer);
    }
}
