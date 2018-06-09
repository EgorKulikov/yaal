package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.numbers.IntegerUtils.sumDigits;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int answer = sumDigits(in.readString());
        if (answer == 1) {
            answer = 10;
        }
        out.printLine(answer);
    }
}
