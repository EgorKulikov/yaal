package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Integer.bitCount;
import static net.egork.misc.ArrayUtils.fill;

public class ChefAndStrangeAddition {
    int c;
    int[][][][] answer;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int a = bitCount(in.readInt());
        int b = bitCount(in.readInt());
        c = in.readInt();
        answer = new int[2][a + 1][b + 1][31];
        fill(answer, -1);
        out.printLine(go(0, a, b, 0));
    }

    private int go(int carry, int a, int b, int bit) {
        if (a < 0 || b < 0) {
            return 0;
        }
        if (answer[carry][a][b][bit] != -1) {
            return answer[carry][a][b][bit];
        }
        if ((c >> bit) == 0) {
            return answer[carry][a][b][bit] = (carry == 0 && a == 0 && b == 0) ? 1 : 0;
        }
        if (carry == 0) {
            if ((c >> bit & 1) == 0) {
                return answer[carry][a][b][bit] = go(0, a, b, bit + 1) + go(1, a - 1, b - 1, bit + 1);
            } else {
                return answer[carry][a][b][bit] = go(0, a - 1, b, bit + 1) + go(0, a, b - 1, bit + 1);
            }
        } else {
            if ((c >> bit & 1) == 0) {
                return answer[carry][a][b][bit] = go(1, a - 1, b, bit + 1) + go(1, a, b - 1, bit + 1);
            } else {
                return answer[carry][a][b][bit] = go(0, a, b, bit + 1) + go(1, a - 1, b - 1, bit + 1);
            }
        }
    }
}
