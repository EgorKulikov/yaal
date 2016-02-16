package on2016_02.on2016_02_14_GP_of_China.TaskJ;



import net.egork.generated.collections.list.IntArray;
import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskJ {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int[] a = readIntArray(in, 6666);
        State[] states = new State[6666];
        for (int i = 0; i < 6666; i++) {
            states[i] = new State(a[i], new IntArray(new int[]{i + 1}));
        }
        for (int i = 0; i < 5; i++) {
            State[] next = new State[(states.length - 1) >> 1];
            int other = 0;
            for (int j = 1; j + 1 < states.length; j += 2) {
                int first;
                int second;
                if ((states[j].value & 1) == (states[j + 1].value & 1)) {
                    first = j;
                    second = j + 1;
                } else if ((states[j].value & 1) == (states[other].value & 1)) {
                    first = j;
                    second = other;
                    other = j + 1;
                } else {
                    first = j + 1;
                    second = other;
                    other = j;
                }
                IntList all = new IntArrayList(states[first].indicies);
                all.addAll(states[second].indicies);
                next[j >> 1] = new State((states[first].value + states[second].value) >> 1, all);
            }
            states = next;
        }
        int[][] last = new int[64][63];
        fill(last, -1);
        last[0][0] = 0;
        for (int i = 0; i < states.length; i++) {
            for (int j = 62; j >= 0; j--) {
                for (int k = 0; k < 63; k++) {
                    if (last[j][k] == -1) {
                        continue;
                    }
                    int nk = (k + states[i].value) % 63;
                    if (last[j + 1][nk] == -1) {
                        last[j + 1][nk] = i;
                    }
                }
            }
        }
        if (last[63][0] == -1) {
            while (true);
        }
        IntList all = new IntArrayList();
        int at = 0;
        for (int i = 63; i > 0; i--) {
            all.addAll(states[last[i][at]].indicies);
            at -= states[last[i][at]].value;
            at %= 63;
            if (at < 0) {
                at += 63;
            }
        }
        all.sort();
        for (int i = 0; i < 2016; i++) {
            out.printLine(all.get(i));
        }
    }

    static class State {
        int value;
        IntList indicies;

        public State(int value, IntList indicies) {
            this.value = value;
            this.indicies = indicies;
        }
    }
}
