package net.egork;

import net.egork.collections.intcollection.IntArray;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int last = in.readInt() - 1;
        int[] number = IOUtils.readIntArray(in, count);
        long[] answer = new long[count];
        int min = new IntArray(number).min();
        int from;
        for (int i = last; ; i--) {
            if (i == -1)
                i = count - 1;
            if (number[i] == min) {
                from = i;
                break;
            }
        }
        int delta = min + 1;
        if (from == last)
            delta--;
        long total = 0;
        for (int i = from + 1; ; i++) {
            if (i == count)
                i = 0;
            number[i] -= delta;
            total += delta;
            if (i == last)
                delta--;
            if (i == from)
                break;
        }
        for (int i = 0; i < count; i++)
            answer[i] = number[i];
        answer[from] = total;
        out.printLine(answer);
    }
}
