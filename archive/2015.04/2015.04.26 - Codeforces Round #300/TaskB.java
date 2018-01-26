package net.egork;

import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int number = in.readInt();
        IntList answer = new IntArrayList();
        while (number > 0) {
            int current = 0;
            for (int t = 1; t <= number; t *= 10) {
                if (number % (t * 10) >= t) {
                    current += t;
                }
            }
            answer.add(current);
            number -= current;
        }
        out.printLine(answer.size());
        out.printLine(answer);
    }
}
