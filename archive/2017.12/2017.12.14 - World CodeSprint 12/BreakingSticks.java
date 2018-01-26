package net.egork;



import net.egork.generated.collections.list.LongArrayList;
import net.egork.generated.collections.list.LongList;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.io.IOUtils.readLongArray;

public class BreakingSticks {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        long[] a = readLongArray(in, n);
        long answer = 0;
        for (long i : a) {
            LongList divisors = new LongArrayList();
            for (long j = 2; j * j <= i; j++) {
                while (i % j == 0) {
                    i /= j;
                    divisors.add(j);
                }
            }
            if (i != 1) {
                divisors.add(i);
            }
            long c = 1;
            for (int j = divisors.size() - 1; j >= 0; j--) {
                answer += c;
                c *= divisors.get(j);
            }
            answer += c;
        }
        out.printLine(answer);
    }
}
