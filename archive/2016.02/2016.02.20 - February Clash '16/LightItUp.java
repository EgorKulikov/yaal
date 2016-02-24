package net.egork;

import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class LightItUp {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] l = new int[n];
        int[] r = new int[n];
        int[] h = new int[n];
        readIntArrays(in, l, r, h);
        State[] states = new State[n];
        for (int i = 0; i < n; i++) {
            states[i] = new State(l[i], r[i], h[i]);
        }
        sort(states);
        for (int i = 0; i < n; i++) {
            l[i] = states[i].l;
            r[i] = states[i].r;
            h[i] = states[i].h;
        }
//        orderBy(l, r, h);
        int answer = 2;
        int[] added = new int[n];
        int[] x = new int[n];
        int[] y = new int[n];
        boolean[] illuminated = new boolean[n];
        boolean[] light = new boolean[n];
        for (int t = 0; t < 2; t++) {
            int size = 0;
            for (int i = 1; i < n; i++) {
                for (int j = 0; j < size; j++) {
                    if (under(r[added[j]], h[added[j]], x[j], y[j], r[i - 1], h[i - 1])) {
                        x[j] = r[i - 1];
                        y[j] = h[i - 1];
                    }
                }
                if (light[i]) {
                    added[size] = i;
                    x[size] = l[i + 1];
                    y[size++] = h[i + 1];
                }
                boolean found = illuminated[i];
                for (int j = 0; j < size; j++) {
                    if (under(r[added[j]], h[added[j]], x[j], y[j], l[i], 0)) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    if (h[i - 1] > h[i]) {
                        added[size] = i - 1;
                        x[size] = l[i];
                        y[size++] = h[i];
                        illuminated[i - 1] = true;
                    } else {
                        light[i] = true;
                        if (t == 1) {
                            answer++;
                        }
                    }
                }
            }
            answer += size;
            reverse(h);
            reverse(l);
            reverse(r);
            ArrayUtils.reverse(illuminated);
            ArrayUtils.reverse(light);
            for (int i = 0; i < n; i++) {
                int temp = l[i];
                l[i] = -r[i];
                r[i] = -temp;
            }
        }
        out.printLine(answer);
    }

    static class State implements Comparable<State> {
        int l;
        int r;
        int h;

        public State(int l, int r, int h) {
            this.l = l;
            this.r = r;
            this.h = h;
        }

        @Override
        public int compareTo(State o) {
            return l - o.l;
        }
    }

    public static void reverse(int[] array) {
        for (int i = 0, j = array.length - 1; i < j; i++, j--) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    private boolean under(int x0, int y0, int x1, int y1, int x2, int y2) {
        return y2 * (x1 - x0) + y0 * (x2 - x1) >= y1 * (x2 - x0);
    }
}
