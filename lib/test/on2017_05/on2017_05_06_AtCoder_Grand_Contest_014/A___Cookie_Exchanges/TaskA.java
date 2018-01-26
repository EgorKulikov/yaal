package on2017_05.on2017_05_06_AtCoder_Grand_Contest_014.A___Cookie_Exchanges;



import net.egork.collections.Pair;
import net.egork.collections.set.EHashSet;
import net.egork.generated.collections.pair.IntIntPair;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Set;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int a = in.readInt();
        int b = in.readInt();
        int c = in.readInt();
        int answer = 0;
        Set<Pair<IntIntPair, Integer>> was = new EHashSet<>();
        while (true) {
            Pair<IntIntPair, Integer> key = Pair.makePair(new IntIntPair(a, b), c);
            if (was.contains(key)) {
                out.printLine(-1);
                return;
            }
            was.add(key);
            if (a % 2 != 0 || b % 2 != 0 || c % 2 != 0) {
                out.printLine(answer);
                return;
            }
            int na = b / 2 + c / 2;
            int nb = a / 2 + c / 2;
            int nc = a / 2 + b / 2;
            a = na;
            b = nb;
            c = nc;
            answer++;
        }
    }
}
