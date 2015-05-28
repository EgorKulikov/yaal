package net.egork;

import net.egork.collections.intervaltree.IntervalTree;
import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class DevuAndBinaryString {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int length = in.readInt();
        int maxSame = in.readInt();
        char[] sequence = IOUtils.readCharArray(in, length);
        int answer;
        if (maxSame == 1) {
            int qty = 0;
            for (int i = 0; i < length; i++) {
                qty += (sequence[i] != ('0' + (i & 1))) ? 1 : 0;
            }
            if (qty < length - qty) {
                answer = qty;
                for (int i = 0; i < length; i++) {
                    sequence[i] = (char)('0' + (i & 1));
                }
            } else {
                answer = length - qty;
                for (int i = 0; i < length; i++) {
                    sequence[i] = (char)('1' - (i & 1));
                }
            }
        } else {
            answer = 0;
            int qty = 0;
            char last = '0';
            int start = 0;
            for (char c : sequence) {
                if (last != c) {
                    for (int i = start + maxSame; i < start + qty - 1; i += maxSame + 1) {
                        sequence[i] = (char)('1' + '0' - sequence[i]);
                    }
                    if (qty != 0 && qty % (maxSame + 1) == 0) {
                        sequence[start + qty - 2] = (char)('1' + '0' - sequence[start + qty - 2]);
                    }
                    start += qty;
                    answer += qty / (maxSame + 1);
                    qty = 0;
                    last = c;
                }
                qty++;
            }
            for (int i = start + maxSame; i < length; i += maxSame + 1) {
                sequence[i] = (char)('1' + '0' - sequence[i]);
            }
            answer += qty / (maxSame + 1);
        }
        out.printLine(answer);
        out.printLine(sequence);
    }
}
