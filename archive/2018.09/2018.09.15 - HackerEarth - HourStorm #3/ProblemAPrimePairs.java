package net.egork;

import net.egork.generated.collections.pair.IntIntPair;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import net.egork.numbers.IntegerUtils;

import java.util.ArrayList;
import java.util.List;

public class ProblemAPrimePairs {
    boolean[] isPrime = IntegerUtils.generatePrimalityTable(1000001);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int l = in.readInt();
        int r = in.readInt();
        List<IntIntPair> answer = new ArrayList<>();
        while (true) {
            while (l < r && !isPrime[l]) {
                l++;
            }
            while (l < r && !isPrime[r]) {
                r--;
            }
            if (l >= r) {
                break;
            }
            answer.add(new IntIntPair(l++, r--));
        }
        out.printLine(answer.size());
        out.printPairList(answer);
    }
}
