package on2018_01.on2018_01_02_HourRank_25.Maximum_Palindromes;



import net.egork.numbers.Combinations;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Math.max;
import static java.lang.System.arraycopy;
import static net.egork.misc.MiscUtils.MOD7;

public class MaximumPalindromes {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.readString();
        int[][] qty = new int[s.length() + 1][26];
        for (int i = 0; i < s.length(); i++) {
            arraycopy(qty[i], 0, qty[i + 1], 0, 26);
            qty[i + 1][s.charAt(i) - 'a']++;
        }
        Combinations c = new Combinations(s.length() + 1, MOD7);
        int q = in.readInt();
        for (int i = 0; i < q; i++) {
            int l = in.readInt() - 1;
            int r = in.readInt();
            int odd = 0;
            int total = 0;
            long answer = 1;
            for (int j = 0; j < 26; j++) {
                int num = qty[r][j] - qty[l][j];
                total += num >> 1;
                answer *= c.c(total, num >> 1);
                answer %= MOD7;
                odd += num & 1;
            }
            answer *= max(1, odd);
            answer %= MOD7;
            out.printLine(answer);
        }
    }
}
