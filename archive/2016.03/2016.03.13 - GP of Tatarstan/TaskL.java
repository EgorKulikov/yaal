package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.*;

import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskL {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int a = in.readInt();
        int b = in.readInt();
        int c = in.readInt();
        int d = in.readInt();
        if (signum(a - b) != signum(c - d)) {
            out.printLine(0);
            return;
        }
        boolean revert = false;
        if (a > b) {
            revert = true;
            int temp = a;
            a = b;
            b = temp;
            temp = c;
            c = d;
            d = temp;
        }
        int delta = b - a;
        if (delta != 0) {
            while (delta % 2 == 0) {
                delta /= 2;
            }
            if ((d - c) % delta != 0) {
                out.printLine(0);
                return;
            }
        }
        boolean[] has = new boolean[4001];
        List<Operation> answer = new ArrayList<>();
        if (delta != 0) {
            while (b - a > delta) {
                if (a % 2 != 0) {
                    answer.add(new Operation(1, a, b));
                    a++;
                    b++;
                }
                answer.add(new Operation(2, a, b));
                a /= 2;
                b /= 2;
            }
        }
        has[a] = true;
        if (delta == 0) {
            while (a != 1) {
                if (a % 2 == 1) {
                    answer.add(new Operation(1, a, a));
                    a++;
                }
                answer.add(new Operation(2, a, a));
                a /= 2;
            }
            for (int i = 1; i < c; i++) {
                answer.add(new Operation(1, i, i));
            }
        } else {
            while (a != 1) {
                get(b, delta, has, answer);
                if (a != b) {
                    answer.add(new Operation(3, a, b, b, 2 * b - a));
                }
                if (a % 2 == 1) {
                    answer.add(new Operation(1, a, 2 * b - a));
                    a++;
                }
                answer.add(new Operation(2, a, a + 2 * delta));
                a /= 2;
                b = a + delta;
                has[a] = true;
            }
            get(c, delta, has, answer);
            for (int cc = c + delta; cc < d; cc += delta) {
                get(cc, delta, has, answer);
                answer.add(new Operation(3, c, cc, cc, cc + delta));
            }
        }
        out.printLine(answer.size());
        for (Operation operation : answer) {
            if (operation.type <= 2) {
                if (revert) {
                    out.printLine(operation.type, operation.b, operation.a);
                } else {
                    out.printLine(operation.type, operation.a, operation.b);
                }
            } else {
                if (revert) {
                    out.printLine(operation.type, operation.d, operation.c, operation.b, operation.a);
                } else {
                    out.printLine(operation.type, operation.a, operation.b, operation.c, operation.d);
                }
            }
        }
    }

    protected void get(int what, int delta, boolean[] has, List<Operation> answer) {
        for (int j = 0; ; j++) {
            if (has[what - j]) {
                for (int i = 0; i < j; i++) {
                    answer.add(new Operation(1, what - j + i, what + delta - j + i));
                    has[what - j + i + 1] = true;
                }
                break;
            }
        }
    }

    static class Operation {
        int type;
        int a;
        int b;
        int c;
        int d;

        public Operation(int type, int a, int b, int c, int d) {
            this.type = type;
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }

        public Operation(int type, int a, int b) {
            this.type = type;
            this.a = a;
            this.b = b;
        }
    }
}
