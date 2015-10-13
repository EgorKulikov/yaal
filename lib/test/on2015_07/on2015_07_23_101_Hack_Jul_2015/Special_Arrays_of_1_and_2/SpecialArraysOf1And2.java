package on2015_07.on2015_07_23_101_Hack_Jul_2015.Special_Arrays_of_1_and_2;



import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class SpecialArraysOf1And2 {
    private static final long MOD = (long) (1e9 + 7);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int prohibited = in.readInt();
        prohibited -= 5;
        long[][] qty = new long[2][16];
        long[][] sum = new long[2][16];
        for (int i = 0; i < 16; i++) {
            int skip = 0;
            int cSum = 0;
            for (int j = 3; j >= 0; j--) {
                if (skip == 0) {
                    skip = (i >> j) & 1;
                    cSum += ((i >> j) & 1) + 1;
                } else {
                    skip = 0;
                }
            }
            sum[skip][i] = cSum;
            qty[skip][i] = 1;
        }
        long[][] nQty = new long[2][16];
        long[][] nSum = new long[2][16];
        for (int i = 4; i < n; i++) {
            ArrayUtils.fill(nQty, 0);
            ArrayUtils.fill(nSum, 0);
            for (int ss = 0; ss < 2; ss++) {
                for (int j = 0; j < 16; j++) {
                    for (int k = 0; k < 2; k++) {
                        if (Integer.bitCount(j) + k == prohibited) {
                            continue;
                        }
                        int nm = ((j << 1) & 15) + k;
                        int nSS = ss == 1 ? 0 : k;
                        nQty[(nSS)][nm] += qty[ss][j];
                        if (nQty[(nSS)][nm] >= MOD) {
                            nQty[(nSS)][nm] -= MOD;
                        }
                        nSum[nSS][nm] += sum[ss][j] + qty[ss][j] * (k + 1) * (1 - ss);
                        nSum[nSS][nm] %= MOD;
                    }
                }
            }
            long[][] temp = sum;
            sum = nSum;
            nSum = temp;
            temp = qty;
            qty = nQty;
            nQty = temp;
        }
        long answer = 0;
        for (long[] i : sum) {
            for (long j : i) {
                answer += j;
            }
        }
        out.printLine(answer % MOD);
    }
}
