package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Arrays;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int x = in.readInt();
        int y = in.readInt();
        char[] a = in.readString().toCharArray();
        char[] b = in.readString().toCharArray();
        int g = IntegerUtils.gcd(a.length, b.length);
        int[] qty = new int[26];
        long total = 0;
        for (int i = 0; i < g; i++) {
            Arrays.fill(qty, 0);
            for (int j = i; j < a.length; j += g) {
                qty[a[j] - 'a']++;
            }
            for (int j = i; j < b.length; j += g) {
                total += qty[b[j] - 'a'];
            }
        }
        total *= y / (a.length / g);
        out.printLine(total);
    }
}
