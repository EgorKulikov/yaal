package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class ChotaBheem {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.readString();
        long a = in.readLong();
        long b = in.readLong();
        long[] v1 = new long[s.length() + 1];
        long[] v2 = new long[s.length() + 1];
        long ten = 1;
        for (int i = s.length() - 1; i > 0; i--) {
            v2[i] = mulAndAdd(ten, s.charAt(i) - '0', v2[i + 1], b);
            ten = mulAndAdd(ten, 10, 0, b);
        }
        for (int i = 0; i < s.length() - 1; i++) {
            v1[i + 1] = mulAndAdd(v1[i], 10, s.charAt(i) - '0', a);
        }
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != '0' && v1[i] == 0 && v2[i] == 0) {
                out.printLine("YES");
                out.printLine(s.substring(0, i));
                out.printLine(s.substring(i));
                return;
            }
        }
        out.printLine("NO");
    }

    private long mulAndAdd(long a, int b, long c, long m) {
        long res = 0;
        for (int i = 0; i < b; i++) {
            res += a;
            while (res >= m) {
                res -= m;
            }
        }
        res += c;
        while (res >= m) {
            res -= m;
        }
        return res;
    }
}
