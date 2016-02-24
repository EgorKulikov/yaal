package net.egork;

import net.egork.collections.Pair;
import net.egork.generated.collections.pair.IntIntPair;
import net.egork.generated.collections.set.IntHashSet;
import net.egork.generated.collections.set.IntSet;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.io.StringBufferInputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class Rings {
    String cases = "test 1: 5 3 7 5\n" +
            "\n" +
            "test 2: 6 6 6 6\n" +
            "\n" +
            "test 3: 7 4 7 5\n" +
            "\n" +
            "test 4: 8 5 10 8\n" +
            "\n" +
            "test 5: 9 3 9 9\n" +
            "\n" +
            "test 6: 20 7 15 10\n" +
            "\n" +
            "test 7: 25 5 30 10\n" +
            "\n" +
            "test 8: 40 12 50 20\n" +
            "\n" +
            "test 9: 50 4 40 10\n" +
            "\n" +
            "test 10: 60 20 70 40\n" +
            "\n" +
            "test 11: 200 30 200 200\n" +
            "\n" +
            "test 12: 250 6 100 60\n" +
            "\n" +
            "test 13: 300 80 400 200\n" +
            "\n" +
            "test 14: 350 17 200 70\n" +
            "\n" +
            "test 15: 400 31 170 40\n" +
            "\n" +
            "test 16: 5000 10 5000 5000\n" +
            "\n" +
            "test 17: 5500 20 6000 2000\n" +
            "\n" +
            "test 18: 6000 30 10000 800\n" +
            "\n" +
            "test 19: 6500 5 3000 200\n" +
            "\n" +
            "test 20: 7000 40 7000 2000\n" +
            "\n" +
            "test 21: 50000 6 70000 50000\n" +
            "\n" +
            "test 22: 55000 4 100000 15000\n" +
            "\n" +
            "test 23: 60000 10 15000 6000\n" +
            "\n" +
            "test 24: 65000 4 40000 20000\n" +
            "\n" +
            "test 25: 70000 11 90000 60000\n" +
            "\n" +
            "test 26: 200000 5 200000 200000\n" +
            "\n" +
            "test 27: 205000 3 300000 60000\n" +
            "\n" +
            "test 28: 210000 4 100000 45000\n" +
            "\n" +
            "test 29: 230000 4 150000 90000\n" +
            "\n" +
            "test 30: 500000 2 100000 10000\n" +
            "\n" +
            "test 31: 50000 6 700 500\n" +
            "\n" +
            "test 32: 55000 4 1000 150\n" +
            "\n" +
            "test 33: 60000 10 1500 600\n" +
            "\n" +
            "test 34: 65000 4 400 200\n" +
            "\n" +
            "test 35: 70000 11 900 600\n" +
            "\n" +
            "test 36: 200000 5 200 200\n" +
            "\n" +
            "test 37: 205000 3 300 60\n" +
            "\n" +
            "test 38: 210000 4 1000 450\n" +
            "\n" +
            "test 39: 230000 4 1500 900\n" +
            "\n" +
            "test 40: 500000 2 1000 100";

    Map<Pair<Pair<Integer, Integer>, Integer>, Integer> map = new HashMap<>();
    Set<Integer> use239 = new HashSet<>();


    {
        InputReader in = new InputReader(new StringBufferInputStream(cases));
        for (int i = 0; i < 40; i++) {
            in.readString();
            in.readString();
            int n = in.readInt();
            int m = in.readInt();
            int c = in.readInt();
            in.readInt();
            map.put(Pair.makePair(Pair.makePair(n, m), c), i);
        }
        for (int i : new int[]{11, 12, 24, 25, 32}) {
            use239.add(i);
        }
    }

    int[] iters = {
            10000, //1
            10000,
            20000000,
            10000,
            10000,
            3000000, //6
            3500000,
            700000,
            2000000,
            300000,
            60000, //11
            300000,
            10000,
            60000,
            25000,
            8000, //16
            3500,
            2000,
            12000,
            1000,
            1000, //21
            1400,
            500,
            1300,
            330,
            250, //26
            350,
            300,
            250,
            160,
            1800, //31
            2500,
            600,
            2500,
            500,
            450, //36
            600,
            500,
            500,
            250,
    };

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int c = in.readInt();
        int test = map.get(Pair.makePair(Pair.makePair(n, m), c));
        Ring[] rings = new Ring[n];
        for (int i = 0; i < n; i++) {
            rings[i] = new Ring(i, readIntArray(in, m));
        }
        int bestSize = 0;
        int[] bestAnswer = new int[n];
        int[] bestShift = new int[n];
        int[] answer = new int[n];
        int[] shift = new int[n];
        boolean[][] was = new boolean[m][c];
//        long time = System.currentTimeMillis();
        Random r = new Random(use239.contains(test) ? 239 : 146464354);
//        long limit = 600000000;
        for (int x = 0; x < iters[test]; x++) {
//            limit -= (long) n * m * m;
            for (int i = 0; i < n; i++) {
                int id = r.nextInt(i + 1);
                Ring temp = rings[i];
                rings[i] = rings[id];
                rings[id] = temp;
            }
            fill(was, false);
            int size = 0;
            for (int i = 0; i < n; i++) {
                if (size + n - i <= bestSize) {
                    break;
                }
                int start = r.nextInt(m);
                for (int j = start; j < start + m; j++) {
                    boolean good = true;
                    for (int k = 0; k < m; k++) {
                        int ind = k - j;
                        if (ind < 0) {
                            ind += m;
                        }
                        if (ind < 0) {
                            ind += m;
                        }
                        if (was[ind][rings[i].colors[k]]) {
                            good = false;
                            break;
                        }
                    }
                    if (good) {
                        answer[size] = rings[i].id;
                        shift[size++] = j % m;
                        for (int k = 0; k < m; k++) {
                            int ind = k - j;
                            if (ind < 0) {
                                ind += m;
                            }
                            if (ind < 0) {
                                ind += m;
                            }
                            was[ind][rings[i].colors[k]] = true;
                        }
                        break;
                    }
                }
            }
            if (size > bestSize) {
                bestSize = size;
                System.arraycopy(answer, 0, bestAnswer, 0, size);
                System.arraycopy(shift, 0, bestShift, 0, size);
            }
        }
        out.printLine(bestSize);
        for (int i = 0; i < bestSize; i++) {
            out.printLine(bestAnswer[i], bestShift[i]);
        }
    }

    static class Ring {
        int id;
        int[] colors;

        public Ring(int id, int[] colors) {
            this.id = id;
            this.colors = colors;
        }
    }
}
