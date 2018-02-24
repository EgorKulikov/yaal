package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import net.egork.numbers.NumberIterator;

public class TaskA {
    int answer;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long n = in.readLong();
        answer = 0;
        NumberIterator iterator = new NumberIterator() {
            @Override
            protected void process(long prefix, int remainingDigits) {
                int result = remainingDigits * 9;
                while (prefix != 0) {
                    result += prefix % 10;
                    prefix /= 10;
                }
                answer = Math.max(answer, result);
            }
        };
        iterator.run(1, n);
        out.printLine(answer);
    }
}
