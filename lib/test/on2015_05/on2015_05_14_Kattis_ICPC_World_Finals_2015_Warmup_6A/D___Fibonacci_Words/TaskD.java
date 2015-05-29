package on2015_05.on2015_05_14_Kattis_ICPC_World_Finals_2015_Warmup_6A.D___Fibonacci_Words;



import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        if (in.isExhausted()) {
            throw new UnknownError();
        }
        int index = in.readInt();
        String pattern = in.readString();
        if (index == 0) {
            out.printLine("Case " + testNumber + ":", pattern.equals("0") ? 1 : 0);
            return;
        }
        String last = "0";
        String current = "1";
        int at = 1;
        while (at < index && last.length() < pattern.length()) {
            String next = current + last;
            last = current;
            current = next;
            at++;
        }
        int lastPrefixType = 0;
        int lastSuffixType = 0;
        int curPrefixType = 1;
        int curSuffixType = 1;
        String[] quants = {last, current};
        int[] base = new int[2];
        for (int i = 0; i < 2; i++) {
            base[i] = count(quants[i], pattern);
        }
        int[][] shift = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                shift[i][j] = count(quants[i] + quants[j], pattern) - base[i] - base[j];
            }
        }
        long lastQty = base[0];
        long curQty = base[1];
        while (at < index) {
            long nextQty = lastQty + curQty + shift[curSuffixType][lastPrefixType];
            int nextPrefixType = curPrefixType;
            int nextSuffixType = lastSuffixType;
            lastQty = curQty;
            curQty = nextQty;
            lastPrefixType = curPrefixType;
            curPrefixType = nextPrefixType;
            lastSuffixType = curSuffixType;
            curSuffixType = nextSuffixType;
            at++;
        }
        out.printLine("Case " + testNumber + ":", curQty);
    }

    private int count(String s, String pattern) {
        String all = pattern + s;
        int[] z = StringUtils.zAlgorithm(all);
        int answer = 0;
        for (int i = pattern.length(); i < z.length; i++) {
            if (z[i] >= pattern.length()) {
                answer++;
            }
        }
        return answer;
    }
}
