package on2017_08.on2017_08_14_SnarkNews_Summer_Series_2017__Round_1.F________________________;



import net.egork.string.StringUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.util.Arrays.fill;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String a = in.readString();
        String b = in.readString();
//        String t = a + "$" + b;
/*        int[] prefixFunction = StringUtils.prefixFunction(t);
        StringHash hash = new SimpleStringHash(t);
        int n = a.length();
        char[] answer = new char[n];
        fill(answer, '0');
        int[] delta = new int[26];
        int qDelta = 0;
        for (int i = 0; i < n; i++) {
            int pr = prefixFunction[i + n + 1];
            int l1 = a.charAt(i) - 'a';
            if (delta[l1] == 0) {
                qDelta++;
            }
            delta[l1]++;
            if (delta[l1] == 0) {
                qDelta--;
            }
            int l2 = b.charAt(i) - 'a';
            if (delta[l2] == 0) {
                qDelta++;
            }
            delta[l2]--;
            if (delta[l2] == 0) {
                qDelta--;
            }
            while (qDelta == 0 && pr != 0) {
                if (hash.hash(pr, i + 1) == hash.hash(n + 1, n + 1 + i + 1 - pr)) {
                    answer[i] = '1';
                    break;
                }
                pr = prefixFunction[pr - 1];
            }
        }
        out.printLine(answer);*/
        int[] z1 = StringUtils.zAlgorithm(a + b + "$");
        int[] z2 = StringUtils.zAlgorithm(b + a + "$");
        int n = a.length();
        char[] answer = new char[n];
        fill(answer, '0');
        for (int i = 0; i <= n; i++) {
            for (int j = (i == 0 ? 1 : 0); i + j <= n && j <= z1[i + n]; j++) {
                if (z2[j + n] >= i) {
                    answer[i + j - 1] = '1';
                }
            }
        }
        out.printLine(answer);
    }
}
