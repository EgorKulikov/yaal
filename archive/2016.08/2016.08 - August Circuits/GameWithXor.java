package net.egork;

import net.egork.generated.collections.list.IntArray;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Integer.highestOneBit;
import static net.egork.io.IOUtils.readIntArray;

public class GameWithXor {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = readIntArray(in, n);
        int xor = new IntArray(a).reduce((x, y) -> x ^ y);
        if (xor == 0) {
            out.printLine("Draw");
        } else {
            int qty = new IntArray(a).count(x -> (x & highestOneBit(xor)) != 0);
            if ((qty & 3) == 1) {
                out.printLine("Alice");
                for (int i : a) {
                    if ((i & highestOneBit(xor)) != 0) {
                        out.printLine(i);
                        return;
                    }
                }
            } else if ((n & 1) == 0) {
                out.printLine("Alice");
                for (int i : a) {
                    if ((i & highestOneBit(xor)) == 0) {
                        out.printLine(i);
                        return;
                    }
                }
            } else {
                out.printLine("Bob");
            }
        }
    }
}
