package on2016_05.on2016_05_02_HourRank_8.Beautiful_Binary_String;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.bitCount;
import static java.util.Arrays.fill;
import static net.egork.io.IOUtils.readCharArray;
import static net.egork.misc.ArrayUtils.minElement;

public class BeautifulBinaryString {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        char[] b = readCharArray(in, n);
        if (n < 3) {
            out.printLine(0);
            return;
        }
        int start = ((b[0] - '0') << 2) + ((b[1] - '0') << 1) + b[2] - '0';
        int[] current = new int[8];
        for (int i = 0; i < 8; i++) {
            if (i == 2) {
                current[i] = MAX_VALUE / 2;
            } else {
                current[i] = bitCount(i ^ start);
            }
        }
        int[] next = new int[8];
        for (int i = 3; i < n; i++) {
            fill(next, MAX_VALUE / 2);
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 2; k++) {
                    int score = current[j] + (k ^ (b[i] - '0'));
                    int target = ((j << 1) & 6) + k;
                    if (target != 2) {
                        next[target] = Math.min(next[target], score);
                    }
                }
            }
            int[] temp = current;
            current = next;
            next = temp;
        }
        out.printLine(minElement(current));
    }
}
