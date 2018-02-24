package net.egork;

import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.numbers.IntegerUtils.trueDivide;
import static net.egork.numbers.IntegerUtils.trueMod;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long p = in.readLong();
        int k = in.readInt();
        IntList answer = new IntArrayList();
        while (p != 0) {
            answer.add((int) trueMod(p, k));
            p = -trueDivide(p, k);
        }
        out.printLine(answer.size());
        out.printLine(answer);
    }
}
