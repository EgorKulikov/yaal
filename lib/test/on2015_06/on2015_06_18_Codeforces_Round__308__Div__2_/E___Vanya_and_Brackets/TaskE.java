package on2015_06.on2015_06_18_Codeforces_Round__308__Div__2_.E___Vanya_and_Brackets;



import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        char[] s = in.readString().toCharArray();
        long answer = 0;
        long[] partial = new long[(s.length + 1) / 2 + 1];
        long current = 0;
        long multiple = s[0] - '0';
        partial[1] = multiple;
        for (int i = 2; i < s.length; i += 2) {
            int digit = s[i] - '0';
            if (s[i - 1] == '*') {
                multiple *= digit;
            } else {
                current += multiple;
                multiple = digit;
            }
            partial[(i >> 1) + 1] = current + multiple;
        }
        int[] positions = new int[ArrayUtils.count(s, '*') + 2];
        positions[0] = -1;
        int at = 1;
        for (int i = 1; i < s.length; i += 2) {
            if (s[i] == '*') {
                positions[at++] = i;
            }
        }
        positions[at] = s.length;
        for (int i : positions) {
            for (int j : positions) {
                if (i == j) {
                    break;
                }
                long multiply = 1;
                int lft = j;
                while (lft > 0 && s[lft] == '*') {
                    multiply *= s[lft - 1] - '0';
                    lft -= 2;
                }
                int rht = i;
                while (rht < s.length && s[rht] == '*') {
                    multiply *= s[rht + 1] - '0';
                    rht += 2;
                }
                long middle = 0;
                int lft2 = j;
                long cur = 1;
                while (lft2 < i && (lft2 < 0 || s[lft2] == '*')) {
                    cur *= s[lft2 + 1] - '0';
                    lft2 += 2;
                }
                middle += cur;
                int rht2 = i;
                cur = 1;
                while (rht2 > lft2 && (rht2 >= s.length || s[rht2] == '*')) {
                    cur *= s[rht2 - 1] - '0';
                    rht2 -= 2;
                }
                if (lft2 != i) {
                    middle += cur;
                    middle += partial[(rht2 + 1) >> 1] - partial[(lft2 + 1) >> 1];
                }
                long candidate = partial[(lft + 1) >> 1] + middle * multiply +
                    partial[partial.length - 1] - partial[(rht + 1) >> 1];
                answer = Math.max(answer, candidate);
            }
        }
        out.printLine(answer);
    }
}
