package on2015_09.on2015_09_13_XVI_OpenCup__Grand_Prix_of_Ukraine.J___Joining_Powers;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TaskJ {
    static final long LIMIT = 100000000000000000L;
    static long[] cubes;
    static long[] hexes;
//    static long[] quads;
//    static long[] twelves;
    static long[][] pows = new long[51][];
    static long[] lims = new long[51];

    static {
        List<Long> c = new ArrayList<>();
        for (long i = 1; i * i * i <= LIMIT; i++) {
            c.add(i * i * i);
        }
        cubes = new long[c.size()];
        for (int i = 0; i < c.size(); i++) {
            cubes[i] = c.get(i);
        }
        List<Long> h = new ArrayList<>();
        for (long i = 1; i * i * i * i * i * i <= LIMIT; i++) {
            h.add(i * i * i * i * i * i);
        }
        hexes = new long[h.size()];
        for (int i = 0; i < h.size(); i++) {
            hexes[i] = h.get(i);
        }
        for (int i = 4; i <= 50; i++) {
            for (int j = 1; ; j++) {
                long current = 1;
                boolean good = true;
                for (int l = 0; l < i; l++) {
                    if (LIMIT / j < current) {
                        good = false;
                        break;
                    }
                    current *= j;
                }
                if (!good) {
                    lims[i] = j - 1;
                    break;
                }
            }
            pows[i] = new long[(int) lims[i]];
            for (long j = 1; j <= lims[i]; j++) {
                long current = 1;
                for (int l = 0; l < i; l++) {
                    current *= j;
                }
                pows[i][((int) (j - 1))] = current;
            }
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int[] is = IOUtils.readIntArray(in, m);
        if (ArrayUtils.count(is, 1) != 0) {
            out.printLine(n);
            return;
        }
        Arrays.sort(is);
        List<Long> list = new ArrayList<>();
        boolean has2 = false;
        boolean has3 = false;
        boolean has4 = false;
        for (int i : is) {
            if (has2 && i % 2 == 0) {
                continue;
            }
            if (has3 && i % 3 == 0) {
                continue;
            }
            if (has4 && i % 4 == 0) {
                continue;
            }
            if (i == 2) {
                has2 = true;
                continue;
            }
            if (i == 3) {
                has3 = true;
                continue;
            }
            if (i == 4) {
                has4 = true;
                continue;
            }
            for (long j : pows[i]) {
                tryAdd(list, j, has2, has3, has4);
            }
        }
        Collections.sort(list);
        long[] interesting = new long[list.size()];
        long last = -1;
        int at = 0;
        for (long l : list) {
            if (l != last) {
                interesting[at++] = l;
                last = l;
            }
        }
        interesting = Arrays.copyOf(interesting, at);
        long left = 1;
        long right = LIMIT;
        while (left < right) {
            long middle = (left + right) >> 1;
            long current = 0;
            if (has2) {
                long round = Math.round(Math.floor(Math.sqrt(middle)));
                if (round * round > middle) {
                    round--;
                }
                if ((round + 1) * (round + 1) <= middle) {
                    round++;
                }
                current += round;
            }
            if (has4) {
                current += getArray(pows[4], middle);
            }
            if (has3) {
                current += getArray(cubes, middle);
                if (has2) {
                    current -= getArray(hexes, middle);
                } else if (has4) {
                    current -= getArray(pows[12], middle);
                }
            }
            current += getArray(interesting, middle);
            if (current >= n) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        out.printLine(left);
    }

    private long getArray(long[] array, long point) {
        int at = Arrays.binarySearch(array, point);
        if (at >= 0) {
            return at + 1;
        } else {
            return -at - 1;
        }
    }

    private void tryAdd(List<Long> list, long current, boolean has2, boolean has3, boolean has4) {
        if (has2 && IntegerUtils.isSquare(current)) {
            return;
        }
        if (has4 && Arrays.binarySearch(pows[4], current) >= 0) {
            return;
        }
        if (has3 && Arrays.binarySearch(cubes, current) >= 0) {
            return;
        }
        list.add(current);
    }
}
