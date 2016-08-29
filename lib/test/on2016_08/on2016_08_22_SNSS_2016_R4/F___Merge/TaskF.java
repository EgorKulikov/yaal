package on2016_08.on2016_08_22_SNSS_2016_R4.F___Merge;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Random;

import static java.lang.Math.min;
import static net.egork.io.IOUtils.readIntArray;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = readIntArray(in, n);
        int m = in.readInt();
        int[] b = readIntArray(in, m);
        StringHash ah = new SimpleStringHash(a);
        StringHash bh = new SimpleStringHash(b);
        int ata = 0;
        int atb = 0;
        int[] answer = new int[n + m];
        while (ata < n && atb < m) {
            int l = 0;
            int r = min(n - ata, m - atb);
            while (l < r) {
                int mid = (l + r + 1) >> 1;
                if (ah.hash(ata, ata + mid) == bh.hash(atb, atb + mid)) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            if (atb + l == m || ata + l != n && a[ata + l] < b[atb + l]) {
                answer[ata + atb] = a[ata];
                ata++;
            } else {
                answer[ata + atb] = b[atb];
                atb++;
            }
        }
        while (ata < n) {
            answer[ata + atb] = a[ata];
            ata++;
        }
        while (atb < m) {
            answer[ata + atb] = b[atb];
            atb++;
        }
        out.printLine(answer);
    }

    interface StringHash {
        long hash(int from, int to);

        long hash(int from);

        int length();
    }

    static abstract class AbstractStringHash implements StringHash {
        public static final long MULTIPLIER;
        protected static final long FIRST_REVERSE_MULTIPLIER;
        protected static final long SECOND_REVERSE_MULTIPLIER;
        public static final long FIRST_MOD;
        public static final long SECOND_MOD;

        static {
            Random random = new Random(System.currentTimeMillis());
            FIRST_MOD = IntegerUtils.nextPrime((long) (1e9 + random.nextInt((int) 1e9)));
            SECOND_MOD = IntegerUtils.nextPrime((long) (1e9 + random.nextInt((int) 1e9)));
            MULTIPLIER = random.nextInt((int) 1e9 - 1025) + 1025;
            FIRST_REVERSE_MULTIPLIER = IntegerUtils.reverse(MULTIPLIER, FIRST_MOD);
            SECOND_REVERSE_MULTIPLIER = IntegerUtils.reverse(MULTIPLIER, SECOND_MOD);
        }

        public long hash(int from) {
            return hash(from, length());
        }
    }

    static class SimpleStringHash extends AbstractStringHash {
        private static long[] firstReversePower = new long[0];
        private static long[] secondReversePower = new long[0];

        private final long[] firstHash;
        private final long[] secondHash;

        public SimpleStringHash(int[] string) {
            int length = string.length;
            ensureCapacity(length);
            firstHash = new long[length + 1];
            secondHash = new long[length + 1];
            long firstPower = 1;
            long secondPower = 1;
            for (int i = 0; i < length; i++) {
                firstHash[i + 1] = (firstHash[i] + string[i] * firstPower) % FIRST_MOD;
                secondHash[i + 1] = (secondHash[i] + string[i] * secondPower) % SECOND_MOD;
                firstPower *= MULTIPLIER;
                firstPower %= FIRST_MOD;
                secondPower *= MULTIPLIER;
                secondPower %= SECOND_MOD;
            }
        }

        private void ensureCapacity(int length) {
            if (firstReversePower.length >= length) {
                return;
            }
            length = Math.max(length + 1, firstReversePower.length << 1);
            long[] oldFirst = firstReversePower;
            long[] oldSecond = secondReversePower;
            firstReversePower = new long[length];
            secondReversePower = new long[length];
            System.arraycopy(oldFirst, 0, firstReversePower, 0, oldFirst.length);
            System.arraycopy(oldSecond, 0, secondReversePower, 0, oldSecond.length);
            firstReversePower[0] = secondReversePower[0] = 1;
            for (int i = Math.max(oldFirst.length, 1); i < length; i++) {
                firstReversePower[i] = firstReversePower[i - 1] * FIRST_REVERSE_MULTIPLIER % FIRST_MOD;
                secondReversePower[i] = secondReversePower[i - 1] * SECOND_REVERSE_MULTIPLIER % SECOND_MOD;
            }
        }

        public long hash(int from, int to) {
            return (((firstHash[to] - firstHash[from] + FIRST_MOD) * firstReversePower[from] % FIRST_MOD) << 32) +
                    ((secondHash[to] - secondHash[from] + SECOND_MOD) * secondReversePower[from] % SECOND_MOD);
        }

        public int length() {
            return firstHash.length - 1;
        }
    }

}

