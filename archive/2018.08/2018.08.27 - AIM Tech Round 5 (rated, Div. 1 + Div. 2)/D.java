package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.NavigableSet;
import java.util.TreeSet;

import static java.lang.Integer.MAX_VALUE;
import static net.egork.misc.MiscUtils.MOD7;

public class D {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int buy = 0;
        int sell = MAX_VALUE;
        long answer = 1;
        NavigableSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            String type = in.readString();
            int p = in.readInt();
            if ("ADD".equals(type)) {
                set.add(p);
            } else {
                if (p < buy || p > sell) {
                    out.printLine(0);
                    return;
                }
                if (p > buy && p < sell) {
                    answer *= 2;
                    answer %= MOD7;
                }
                Integer newBuy = set.lower(p);
                Integer newSell = set.higher(p);
                if (newBuy == null) {
                    newBuy = 0;
                }
                if (newSell == null) {
                    newSell = MAX_VALUE;
                }
                buy = newBuy;
                sell = newSell;
                set.remove(p);
            }
        }
        answer *= set.subSet(buy, false, sell, false).size() + 1;
        answer %= MOD7;
        out.printLine(answer);
    }
}
