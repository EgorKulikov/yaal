package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int[] length = new int[count];
        int[] qty = new int[count];
        IOUtils.readIntArrays(in, length, qty);
        if (count > 1 && check(length, qty, count - 2, count - 1)) {
            out.printLine("YES");
            return;
        }
        for (int i = 0; i < count; i++) {
            if (check(length, qty, count - 1, i)) {
                out.printLine("YES");
                return;
            }
        }
        out.printLine("NO");
    }

    private boolean check(int[] length, int[] qty, int top, int vertical) {
        qty = qty.clone();
        qty[top] -= 2;
        if (qty[top] < 0) {
            return false;
        }
        qty[vertical] -= 2;
        if (qty[vertical] < 0) {
            return false;
        }
        long needQty = 0;
        long needLength = 0;
        for (int i = 0; i < length.length; i++) {
            if (i == vertical) {
                continue;
            }
            if (qty[i] != 0 && length[vertical] == 1) {
                return false;
            }
            if (qty[i] == 0) {
                continue;
            }
            long needThis = (qty[i] + length[vertical] - 2) / (length[vertical] - 1);
            needQty += needThis;
            needLength += needThis * length[i];
        }
        if (needLength == length[top]) {
            needQty--;
        }
        long excess = qty[vertical] - needQty;
        if (excess < 0) {
            return false;
        }
        needLength += excess == 0 ? 0 : excess + 1;
        if (needLength > length[top]) {
            return false;
        }
        return true;
    }
}
