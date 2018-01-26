package net.egork;



import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.readString();
        if ("#".equals(s)) {
            throw new UnknownError();
        }
        if (s.length() == 1 || s.length() == 2 && s.charAt(0) == s.charAt(1)) {
            out.printLine(-1);
            return;
        }
        boolean[][] edges = new boolean[256][256];
        for (int i = 1; i < s.length(); i++) {
            edges[s.charAt(i - 1)][s.charAt(i)] = true;
        }
        int total = 0;
        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < 256; j++) {
                total += edges[i][j] ? 1 : 0;
            }
        }
        if (total + 1 < s.length() || s.charAt(0) == s.charAt(s.length() - 1)) {
            out.printLine(total + 1);
            return;
        }
        for (int i = 0; i < 26; i++) {
            int outE = 0;
            int inE = 0;
            for (int j = 0; j < 26; j++) {
                if (i != j) {
                    outE += edges[i][j] ? 1 : 0;
                    inE += edges[j][i] ? 1 : 0;
                }
            }
            if (outE >= 2 && inE >= 2 || (outE >= 2 || inE >= 2) && edges[i][i]) {
                out.printLine(s.length());
                return;
            }
        }
        out.printLine(s.length() + 1);
    }
}
