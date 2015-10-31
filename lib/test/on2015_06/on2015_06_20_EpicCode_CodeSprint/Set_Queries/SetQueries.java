package on2015_06.on2015_06_20_EpicCode_CodeSprint.Set_Queries;



import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class SetQueries {
    static final int BUBEN = 400;
    static final int MOD = (int) (1e9 + 9);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int setCount = in.readInt();
        int queryCount = in.readInt();
        int[][] sets = new int[setCount][];
        for (int i = 0; i < setCount; i++) {
            sets[i] = IOUtils.readIntArray(in, in.readInt());
            Arrays.sort(sets[i]);
        }
        MiscUtils.decreaseByOne(sets);
        int[] qty = new int[count];
        int[] id = new int[setCount];
        Arrays.fill(id, -1);
        int at = 0;
        int[] byId = new int[setCount];
        for (int i = 0; i < setCount; i++) {
            if (sets[i].length >= BUBEN) {
                byId[at] = i;
                id[i] = at++;
                for (int j : sets[i]) {
                    qty[j]++;
                }
            }
        }
        int[][] inBig = new int[count][];
        for (int i = 0; i < count; i++) {
            inBig[i] = new int[qty[i]];
        }
        for (int i = 0; i < setCount; i++) {
            if (sets[i].length >= BUBEN) {
                for (int j : sets[i]) {
                    inBig[j][--qty[j]] = id[i];
                }
            }
        }
        int[][] delta = new int[setCount][at];
        for (int i = 0; i < setCount; i++) {
            for (int j : sets[i]) {
                for (int k : inBig[j]) {
                    delta[i][k]++;
                }
            }
        }
        long[] add = new long[at];
        long[] value = new long[at];
//        IntervalTree tree = new LongIntervalTree(count) {
//            @Override
//            protected long joinValue(long left, long right) {
//                long result = left + right;
//                if (result >= MOD) {
//                    result -= MOD;
//                }
//                return result;
//            }
//
//            @Override
//            protected long joinDelta(long was, long delta) {
//                long result = was + delta;
//                if (result >= MOD) {
//                    result -= MOD;
//                }
//                return result;
//            }
//
//            @Override
//            protected long accumulate(long value, long delta, int length) {
//                return (value + delta * length) % MOD;
//            }
//
//            @Override
//            protected long neutralValue() {
//                return 0;
//            }
//
//            @Override
//            protected long neutralDelta() {
//                return 0;
//            }
//        };
        long[] array = new long[count];
        long[] big = new long[count];
        long[] tot = new long[count];
        for (int i = 0; i < queryCount; i++) {
            int type = in.readInt();
            if (type == 1) {
                int set = in.readInt() - 1;
                int by = in.readInt();
                if (id[set] == -1) {
                    for (int j : sets[set]) {
                        array[j] += by;
                        if (array[j] >= MOD) {
                            array[j] -= MOD;
                        }
                        int cur = j / BUBEN;
                        tot[(cur)] += by;
                        if (tot[cur] >= MOD) {
                            tot[cur] -= MOD;
                        }
//                        tree.update(j, j, by);
                    }
                    for (int j = 0; j < at; j++) {
                        value[j] += delta[set][j] * by;
                        value[j] %= MOD;
                    }
                } else {
                    add[id[set]] += by;
                    add[id[set]] %= MOD;
                }
            } else if (type == 2) {
                int set = in.readInt() - 1;
                long result = 0;
                if (id[set] == -1) {
                    for (int j : sets[set]) {
                        result += array[j] + big[j / BUBEN];
                    }
                    for (int j = 0; j < at; j++) {
                        result += add[j] * delta[set][j] % MOD;
                    }
                } else {
                    result = value[id[set]];
                    for (int j = 0; j < at; j++) {
                        result += add[j] * delta[set][j] % MOD;
                    }
                }
                result %= MOD;
                out.printLine(result);
            } else if (type == 3) {
                int from = in.readInt() - 1;
                int to = in.readInt() - 1;
                int by = in.readInt();
                int frBig = from / BUBEN;
                if (from / BUBEN == to / BUBEN) {
                    for (int j = from; j <= to; j++) {
                        array[j] += by;
                        tot[frBig] += by;
                        if (array[j] >= MOD) {
                            array[j] -= MOD;
                        }
                    }
                    tot[frBig] %= MOD;
                } else {
                    int toBig = to / BUBEN;
                    for (int j = from; j < (frBig + 1) * BUBEN; j++) {
                        array[j] += by;
                        tot[frBig] += by;
                        if (array[j] >= MOD) {
                            array[j] -= MOD;
                        }
                    }
                    tot[frBig] %= MOD;
                    for (int j = toBig * BUBEN; j <= to; j++) {
                        array[j] += by;
                        tot[toBig] += by;
                        if (array[j] >= MOD) {
                            array[j] -= MOD;
                        }
                    }
                    tot[toBig] %= MOD;
                    for (int j = frBig + 1; j < toBig; j++) {
                        big[j] += by;
                        if (big[j] >= MOD) {
                            big[j] -= MOD;
                        }
                    }
                }
//                tree.update(from, to, by);
                for (int j = 0; j < at; j++) {
                    int start = Arrays.binarySearch(sets[byId[j]], from);
                    if (start < 0) {
                        start = -start - 1;
                    }
                    int end = Arrays.binarySearch(sets[byId[j]], to);
                    if (end < 0) {
                        end = -end - 2;
                    }
                    value[j] += (end - start + 1L) * by;
                    value[j] %= MOD;
                }
            } else if (type == 4) {
                int from = in.readInt() - 1;
                int to = in.readInt() - 1;
                long result = 0;
                int frBig = from / BUBEN;
                int toBig = to / BUBEN;
                if (frBig == toBig) {
                    result += (to - from + 1) * big[frBig] % MOD;
                    for (int j = from; j <= to; j++) {
                        result += array[j];
                    }
                } else {
                    for (int j = from; j < (frBig + 1) * BUBEN; j++) {
                        result += array[j] + big[frBig];
                    }
                    for (int j = toBig * BUBEN; j <= to; j++) {
                        result += array[j] + big[toBig];
                    }
                    for (int j = frBig + 1; j < toBig; j++) {
                        result += big[j] * BUBEN + tot[j];
                    }
                }
                for (int j = 0; j < at; j++) {
                    int start = Arrays.binarySearch(sets[byId[j]], from);
                    if (start < 0) {
                        start = -start - 1;
                    }
                    int end = Arrays.binarySearch(sets[byId[j]], to);
                    if (end < 0) {
                        end = -end - 2;
                    }
                    result += (end - start + 1L) * add[j] % MOD;
                }
                result %= MOD;
                out.printLine(result);
            }
        }
    }
}
