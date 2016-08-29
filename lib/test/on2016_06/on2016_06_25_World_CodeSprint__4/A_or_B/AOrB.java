package on2016_06.on2016_06_25_World_CodeSprint__4.A_or_B;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static java.lang.Character.isDigit;
import static java.lang.Math.max;
import static java.util.Arrays.copyOfRange;
import static net.egork.misc.ArrayUtils.concatenate;

public class AOrB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int k = in.readInt();
        char[] a = in.readString().toCharArray();
        char[] b = in.readString().toCharArray();
        char[] c = in.readString().toCharArray();
        process(a);
        process(b);
        process(c);
        int mx = max(a.length, max(b.length, c.length));
        a = concatenate(new char[mx - a.length], a);
        b = concatenate(new char[mx - b.length], b);
        c = concatenate(new char[mx - c.length], c);
        for (int i = 0; i < a.length; i++) {
            for (int j = 3; j >= 0; j--) {
                if ((c[i] >> j & 1) == 0) {
                    if ((a[i] >> j & 1) == 1) {
                        a[i] -= 1 << j;
                        k--;
                    }
                    if ((b[i] >> j & 1) == 1) {
                        b[i] -= 1 << j;
                        k--;
                    }
                } else {
                    if ((a[i] >> j & 1) == 0 && (b[i] >> j & 1) == 0) {
                        b[i] += 1 << j;
                        k--;
                    }
                }
            }
        }
        if (k < 0) {
            out.printLine(-1);
            return;
        }
        for (int i = 0; i < a.length && k > 0; i++) {
            for (int j = 3; j >= 0 && k > 0; j--) {
                if ((c[i] >> j & 1) == 1 && (a[i] >> j & 1) == 1) {
                    if ((b[i] >> j & 1) == 1) {
                        a[i] -= 1 << j;
                        k--;
                    } else if (k >= 2) {
                        a[i] -= 1 << j;
                        b[i] += 1 << j;
                        k -= 2;
                    }
                }
            }
        }
        a = processBack(a);
        b = processBack(b);
        out.printLine(a);
        out.printLine(b);
    }

    private char[] processBack(char[] a) {
        int prefix = 0;
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] == 0) {
                prefix = i + 1;
            } else {
                break;
            }
        }
        a = copyOfRange(a, prefix, a.length);
        for (int i = 0; i < a.length; i++) {
            if (a[i] < 10) {
                a[i] += '0';
            } else {
                a[i] += 'A' - 10;
            }
        }
        return a;
    }

    private void process(char[] a) {
        for (int i = 0; i < a.length; i++) {
            if (isDigit(a[i])) {
                a[i] -= '0';
            } else {
                a[i] -= 'A' - 10;
            }
        }
    }
}
