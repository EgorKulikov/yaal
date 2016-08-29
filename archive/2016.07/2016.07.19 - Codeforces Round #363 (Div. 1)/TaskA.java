package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static java.lang.Math.max;
import static net.egork.io.IOUtils.readIntArray;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = readIntArray(in, n);
        int rest = 0;
        int contest = 0;
        int sport = 0;
        for (int i : a) {
            int nRest = max(rest, max(contest, sport));
            int nContest = 0;
            if ((i & 1) == 1) {
                nContest = 1 + max(rest, sport);
            }
            int nSport = 0;
            if ((i & 2) == 2) {
                nSport = 1 + max(rest, contest);
            }
            rest = nRest;
            contest = nContest;
            sport = nSport;
        }
        out.printLine(n - max(rest, max(contest, sport)));
    }
}
