package on2018_04.on2018_04_07_Qualification_Round_2018.Saving_The_Universe_Again;



import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.string.StringUtils.count;

public class SavingTheUniverseAgain {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int d = in.readInt();
        String p = in.readString();
        if (count(p, 'S') > d) {
            out.printLine("Case #" + testNumber + ": IMPOSSIBLE");
            return;
        }
        int answer = 0;
        while (score(p) > d) {
            int at = p.lastIndexOf("CS");
            p = p.substring(0, at) + "SC" + p.substring(at + 2);
            answer++;
        }
        out.printLine("Case #" + testNumber + ":", answer);
    }

    private int score(String s) {
        int result = 0;
        int add = 1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'S') {
                result += add;
            } else {
                add <<= 1;
            }
        }
        return result;
    }
}
