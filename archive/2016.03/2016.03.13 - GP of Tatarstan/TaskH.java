package net.egork;

import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.InputMismatchException;

import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskH {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.readLine();
        int n = in.readInt();
        int shift = 0;
        boolean revert = false;
        for (int i = 0; i < n; i++) {
            int x;
            try {
                x = in.readInt();
            } catch (InputMismatchException e) {
                x = 0;
            }
            if (x < 0 || x > s.length()) {
                while (true);
            }
            if (revert) {
                x = s.length() - x;
            }
            shift += x;
            shift %= s.length();
            revert = !revert;
        }
        if (revert) {
            shift = s.length() - shift;
            s = StringUtils.reverse(s);
        }
        out.printLine(s.substring(shift) + s.substring(0, shift));
    }
}
