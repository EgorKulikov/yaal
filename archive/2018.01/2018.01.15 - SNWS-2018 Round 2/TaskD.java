package net.egork;



import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.io.IOUtils.readIntArray;
import static net.egork.misc.ArrayUtils.reversePermutation;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class TaskD {
    int[][] depend;
    int[] state;
    int[] result;
    int[] position;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] order = readIntArray(in, n);
        depend = new int[n][];
        for (int i = 0; i < n; i++) {
            depend[i] = readIntArray(in, in.readInt());
        }
        decreaseByOne(depend);
        decreaseByOne(order);
        position = reversePermutation(order);
        state = new int[n];
        result = new int[n];
        int answer = 0;
        for (int i = 0; i < n; i++) {
            int current = go(i);
            if (current == -1) {
                out.printLine(-1);
                return;
            }
            answer = Math.max(answer, current);
        }
        out.printLine(answer);
    }

    private int go(int id) {
        if (state[id] == 1) {
            return -1;
        }
        if (state[id] == 2) {
            return result[id];
        }
        state[id] = 1;
        result[id] = 1;
        for (int i : depend[id]) {
            int current = go(i);
            if (current == -1) {
                return -1;
            }
            if (position[id] < position[i]) {
                current++;
            }
            result[id] = Math.max(result[id], current);
        }
        state[id] = 2;
        return result[id];
    }
}
