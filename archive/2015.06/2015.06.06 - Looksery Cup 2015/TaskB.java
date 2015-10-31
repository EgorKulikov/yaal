package net.egork;

import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        char[][] friends = IOUtils.readTable(in, count, count);
        int[] guess = IOUtils.readIntArray(in, count);
        IntList answer = new IntArrayList();
        while (true) {
            int bad = -1;
            for (int i = 0; i < count; i++) {
                if (guess[i] == 0) {
                    bad = i;
                    break;
                }
            }
            if (bad == -1) {
                break;
            }
            answer.add(bad + 1);
            for (int i = 0; i < count; i++) {
                if (friends[bad][i] == '1') {
                    guess[i]--;
                }
            }
        }
        answer.inPlaceSort();
        out.printLine(answer.size());
        out.printLine(answer);
    }
}
