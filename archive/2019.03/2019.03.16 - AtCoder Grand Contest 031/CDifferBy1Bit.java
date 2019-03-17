package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Integer.bitCount;
import static java.lang.Integer.highestOneBit;
import static java.lang.Integer.lowestOneBit;

public class CDifferBy1Bit {
    int[] answer;
    int at;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int a = in.readInt();
        int b = in.readInt();
        if (bitCount(a) % 2 == bitCount(b) % 2) {
            out.printLine("NO");
            return;
        }
        out.printLine("YES");
        answer = new int[1 << n];
        answer[0] = a;
        at = 1;
        int dif = a ^ b;
        int same = (1 << n) - 1 - dif;
        while (bitCount(dif) > 1) {
            int one = lowestOneBit(dif);
            int other = highestOneBit(dif);
            doCode(dif + same - one, other);
            answer[at] = answer[at - 1] ^ one;
            at++;
            dif -= one + other;
            same += other;
        }
        doCode(same + dif, dif);
        out.printLine(answer);
    }

    private void doCode(int onBits, int last) {
        if (onBits == 0) {
            return;
        }
        int start = at - 1;
        doCode(onBits - last, lowestOneBit(onBits - last));
        for (int i = at - 1; i >= start; i--) {
            answer[at++] = answer[i] ^ last;
        }
    }
}
