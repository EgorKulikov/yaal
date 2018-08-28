package net.egork;

import net.egork.collections.Pair;
import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.generated.collections.pair.IntIntPair;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.egork.misc.ArrayUtils.createOrder;
import static net.egork.misc.ArrayUtils.sort;
import static net.egork.misc.ArrayUtils.sumArray;

public class E {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int[] sx = new int[m];
        int[] sy = new int[m];
        in.readIntArrays(sx, sy);
        int[] fx = new int[m];
        int[] fy = new int[m];
        in.readIntArrays(fx, fy);
        if (n == 1) {
            out.printLine(0);
            return;
        }
        List<Pair<IntIntPair, IntIntPair>> answer = new ArrayList<>();
        if (n == 2) {
            Map<IntIntPair, IntIntPair> moves = new HashMap<>();
            moves.put(new IntIntPair(1, 1), new IntIntPair(1, 2));
            moves.put(new IntIntPair(1, 2), new IntIntPair(2, 2));
            moves.put(new IntIntPair(2, 2), new IntIntPair(2, 1));
            moves.put(new IntIntPair(2, 1), new IntIntPair(1, 1));
            while (sx[0] != fx[0] || sy[0] != fy[0]) {
                IntIntPair move = moves.get(new IntIntPair(sx[0], sy[0]));
                if (m == 2 && sx[1] == move.first && sy[1] == move.second) {
                    IntIntPair move2 = moves.get(move);
                    answer.add(Pair.makePair(move, move2));
                    sx[1] = move2.first;
                    sy[1] = move2.second;
                }
                answer.add(Pair.makePair(new IntIntPair(sx[0], sy[0]), move));
                sx[0] = move.first;
                sy[0] = move.second;
            }
            if (m == 2) {
                while (sx[1] != fx[1] || sy[1] != fy[1]) {
                    IntIntPair move = moves.get(new IntIntPair(sx[1], sy[1]));
                    answer.add(Pair.makePair(new IntIntPair(sx[1], sy[1]), move));
                    sx[1] = move.first;
                    sy[1] = move.second;
                }
            }
            out.printLine(answer.size());
            for (Pair<IntIntPair, IntIntPair> pair : answer) {
                out.printLine(pair.first.first, pair.first.second, pair.second.first, pair.second.second);
            }
            return;
        }
        boolean invert = false;
        int sum = (int) (sumArray(sx) - m + n * m - sumArray(fx));
        if (sum > (n - 1) * m) {
            invert = true;
            for (int i = 0; i < m; i++) {
                sx[i] = n + 1 - sx[i];
                fx[i] = n + 1 - fx[i];
            }
        }
        int current = 1;
        IntList firstRow = new IntArrayList();
        IntList other = new IntArrayList();
        for (int i = 0; i < m; i++) {
            if (sx[i] == 1) {
                firstRow.add(i);
            } else {
                other.add(i);
            }
        }
        firstRow.sort((a, b) -> sy[a] - sy[b]);
        for (int i : firstRow) {
            while (sy[i] > current) {
                answer.add(Pair.makePair(new IntIntPair(sx[i], sy[i]), new IntIntPair(sx[i], sy[i] - 1)));
                sy[i]--;
            }
            current++;
        }
        other.sort((a, b) -> sx[a] == sx[b] ? sy[b] - sy[a] : sx[a] - sx[b]);
        for (int i : other) {
            while (sy[i] < current) {
                answer.add(Pair.makePair(new IntIntPair(sx[i], sy[i]), new IntIntPair(sx[i], sy[i] + 1)));
                sy[i]++;
            }
            while (sx[i] > 1) {
                answer.add(Pair.makePair(new IntIntPair(sx[i], sy[i]), new IntIntPair(sx[i] - 1, sy[i])));
                sx[i]--;
            }
            while (sy[i] > current) {
                answer.add(Pair.makePair(new IntIntPair(sx[i], sy[i]), new IntIntPair(sx[i], sy[i] - 1)));
                sy[i]--;
            }
            current++;
        }
        int[] all = createOrder(m);
        sort(all, (a, b) -> fx[a] != fx[b] ? fx[a] - fx[b] : (fx[a] == n ? fy[a] - fy[b] : fy[b] - fy[a]));
        current = n - m + 1;
        for (int i : all) {
            answer.add(Pair.makePair(new IntIntPair(sx[i], sy[i]), new IntIntPair(sx[i] + 1, sy[i])));
            sx[i]++;
            while (sy[i] < current) {
                answer.add(Pair.makePair(new IntIntPair(sx[i], sy[i]), new IntIntPair(sx[i], sy[i] + 1)));
                sy[i]++;
            }
            while (sy[i] > current) {
                answer.add(Pair.makePair(new IntIntPair(sx[i], sy[i]), new IntIntPair(sx[i], sy[i] - 1)));
                sy[i]--;
            }
            while (sx[i] < n) {
                answer.add(Pair.makePair(new IntIntPair(sx[i], sy[i]), new IntIntPair(sx[i] + 1, sy[i])));
                sx[i]++;
            }
            current++;
        }
        IntList lastRow = new IntArrayList();
        other = new IntArrayList();
        for (int i = 0; i < m; i++) {
            if (fx[i] == n) {
                lastRow.add(i);
            } else {
                other.add(i);
            }
        }
        other.sort((a, b) -> fx[a] != fx[b] ? fx[a] - fx[b] : fy[b] - fy[a]);
        for (int i : other) {
            while (sy[i] > fy[i]) {
                answer.add(Pair.makePair(new IntIntPair(sx[i], sy[i]), new IntIntPair(sx[i], sy[i] - 1)));
                sy[i]--;
            }
            answer.add(Pair.makePair(new IntIntPair(sx[i], sy[i]), new IntIntPair(sx[i] - 1, sy[i])));
            sx[i]--;
            while (sy[i] < fy[i]) {
                answer.add(Pair.makePair(new IntIntPair(sx[i], sy[i]), new IntIntPair(sx[i], sy[i] + 1)));
                sy[i]++;
            }
            while (sx[i] > fx[i]) {
                answer.add(Pair.makePair(new IntIntPair(sx[i], sy[i]), new IntIntPair(sx[i] - 1, sy[i])));
                sx[i]--;
            }
        }
        lastRow.sort((a, b) -> fy[a] - fy[b]);
        for (int i : lastRow) {
            while (sy[i] > fy[i]) {
                answer.add(Pair.makePair(new IntIntPair(sx[i], sy[i]), new IntIntPair(sx[i], sy[i] - 1)));
                sy[i]--;
            }
        }
        out.printLine(answer.size());
        for (Pair<IntIntPair, IntIntPair> pair : answer) {
            if (invert) {
                out.printLine(n + 1 - pair.first.first, pair.first.second, n + 1 - pair.second.first, pair.second.second);
            } else {
                out.printLine(pair.first.first, pair.first.second, pair.second.first, pair.second.second);
            }
        }
    }
}
