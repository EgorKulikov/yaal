package on2015_08.on2015_08_20_101_Hack_Aug_2015.Binary_String;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class BinaryString {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        int t = in.readInt();
        char[] s = IOUtils.readCharArray(in, n);
        int[] firstOne = ArrayUtils.createArray(k, n);
        for (int i = 0; i < n; i++) {
            if (s[i] == '1') {
                firstOne[i % k] = Math.min(firstOne[i % k], i);
            }
        }
        for (int i = 0; i < n; i++) {
            int base = i % k;
            if (firstOne[base] == n) {
                continue;
            }
            int required = (firstOne[base] - i) / k;
            if (required <= t) {
                s[firstOne[base]] = '0';
                s[i] = '1';
                t -= required;
                boolean found = false;
                for (int j = firstOne[base] + k; j < n; j += k) {
                    if (s[j] == '1') {
                        found = true;
                        firstOne[base] = j;
                        break;
                    }
                }
                if (!found) {
                    firstOne[base] = n;
                }
            }
        }
        out.printLine(s);
    }
}
