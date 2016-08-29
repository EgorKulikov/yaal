package on2016_05.on2016_05_09_May_Challenge_2016.Chef_and_Amazingness_of_Numbers;



import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.numbers.NumberIterator;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static net.egork.misc.ArrayUtils.fill;
import static net.egork.misc.MiscUtils.MOD7;

public class ChefAndAmazingnessOfNumbers {
    private long[][][] result = new long[10][16][1 << 16];

    {
        fill(result, -1);
    }

    private long go(int remaining, int xor, int mask) {
        if (result[remaining][xor][mask] != -1) {
            return result[remaining][xor][mask];
        }
        result[remaining][xor][mask] = 0;
        if (remaining == 0) {
            long present = 0;
            for (int i = 0; i < 16; i++) {
                if ((mask >> i & 1) == 0) {
                    continue;
                }
                for (int j = 0; j <= i; j++) {
                    if ((mask >> j & 1) == 1) {
                        present |= 1 << (i ^ j);
                    }
                }
            }
            for (int i = 0; i < 16; i++) {
                if ((present >> i & 1) == 1) {
                    result[remaining][xor][mask] += i;
                }
            }
            return result[remaining][xor][mask];
        }
        for (int i = 0; i < 10; i++) {
            result[remaining][xor][mask] += go(remaining - 1, xor ^ i, mask | (1 << (xor ^ i)));
        }
        return result[remaining][xor][mask];
    }

    private long answer;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int a = in.readInt();
        int b = in.readInt();
        NumberIterator iterator = new NumberIterator() {
            @Override
            protected void process(long prefix, int remainingDigits) {
                int mask = 1;
                int xor = 0;
                IntList digits = new IntArrayList();
                while (prefix != 0) {
                    digits.add((int) (prefix % 10));
                    prefix /= 10;
                }
                digits.inPlaceReverse();
                for (int i : digits) {
                    xor ^= i;
                    mask |= 1 << xor;
                }
                answer += go(remainingDigits, xor, mask);
            }
        };
        answer = 0;
        iterator.run(a, b);
        out.printLine(answer % MOD7);
    }
}
