package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int moves = in.readInt();
        char[] queue = in.readString().toCharArray();
        char[] next = new char[count];
        for (int i = 0; i < moves; i++) {
            next[0] = queue[0];
            for (int j = 1; j < count; j++) {
                next[j] = queue[j];
                if (queue[j] == 'G' && queue[j - 1] == 'B') {
                    next[j - 1] = 'G';
                    next[j] = 'B';
                }
            }
            char[] temp = queue;
            queue = next;
            next = temp;
        }
        out.printLine(queue);
    }
}
