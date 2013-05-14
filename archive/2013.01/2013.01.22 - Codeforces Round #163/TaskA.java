package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        char last = 0;
        int answer = 0;
        for (int i = 0; i < count; i++) {
            char current = in.readCharacter();
            if (current == last)
                answer++;
            last = current;
        }
        out.printLine(answer);
    }
}
