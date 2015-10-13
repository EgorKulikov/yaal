package on2015_08.on2015_08_07_August_Challenge_2015.Chef_and_insomnia;



import net.egork.collections.intcollection.IntHashMap;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ChefAndInsomnia {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        int[] a = IOUtils.readIntArray(in, n);
        long answer = 0;
        int to = n - 1;
        IntHashMap pos = new IntHashMap();
        int last = n;
        for (int i = n - 1; i >= 0; i--) {
            int rem = a[i] - k;
            int nTo = to;
            for (int j = 1; j * j <= rem; j++) {
                if (rem % j != 0) {
                    continue;
                }
                if (j > k && pos.contains(j)) {
                    nTo = Math.min(nTo, pos.get(j) - 1);
                }
                int jj = rem / j;
                if (jj > k && pos.contains(jj)) {
                    nTo = Math.min(nTo, pos.get(jj) - 1);
                }
            }
            if (rem == 0) {
                nTo = Math.min(nTo, last - 1);
            }
            for (int j = nTo + 1; j <= to; j++) {
                if (pos.contains(a[j]) && pos.get(a[j]) > nTo) {
                    pos.remove(a[j]);
                }
            }
            pos.put(a[i], i);
            if (a[i] > k) {
                last = i;
            }
            to = nTo;
            answer += to - i + 1;
        }
        out.printLine(answer);
    }
}
