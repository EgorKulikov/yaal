package net.egork;

import net.egork.generated.collections.function.IntCharToIntFunction;
import net.egork.generated.collections.list.CharArray;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class GGameWithLetters {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        String s = in.readString();
        int[] count = new int[256];
        for (char c : s.toCharArray()) {
            count[c]++;
        }
//        int[] count = new CharArray(s.toCharArray()).qty(256);
        int bestScore = -1;
        int bestSize = 0;
        int index = -1;
        for (int i = 0; i < n; i++) {
            String t = in.readString();
//            int current = new CharArray(t.toCharArray()).reduce(0, (IntCharToIntFunction)((x, y) -> x + count[y]));
            int current = 0;
            for (char c : t.toCharArray()) {
                current += count[c];
            }
            if (current > bestScore || current == bestScore && t.length() < bestSize) {
                bestScore = current;
                bestSize = t.length();
                index = i + 1;
            }
        }
        out.printLine(index);
    }
}
