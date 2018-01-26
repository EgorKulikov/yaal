package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Arrays;

public class NamingTowers {
    char[] mirror = "AHIMOTUVWXY".toCharArray();

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String name = in.readString();
        boolean good = true;
        for (int i = 0, j = name.length() - 1; i <= j; i++, j--) {
            if (name.charAt(i) != name.charAt(j) || Arrays.binarySearch(mirror, name.charAt(i)) < 0) {
                good = false;
                break;
            }
        }
        out.printLine(good ? "YES" : "NO");
    }
}
