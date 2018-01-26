package net.egork;

import net.egork.string.*;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class PalindromicQueries {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.readString();
        StringHash direct = new SimpleStringHash(s);
        StringHash reverse = new SimpleStringHash(StringUtils.reverse(s));
        int count = in.readInt();
        for (int i = 0; i < count; i++) {
            int from = in.readInt() - 1;
            int to = in.readInt();
            StringHash curDirect = new CompositeStringHash(new SubstringStringHash(direct, 0, from),
                new CompositeStringHash(new SubstringStringHash(reverse, s.length() - to, s.length() - from),
                new SubstringStringHash(direct, to)));
            StringHash curReverse = new CompositeStringHash(new SubstringStringHash(reverse, 0, s.length() - to),
                new CompositeStringHash(new SubstringStringHash(direct, from, to),
                new SubstringStringHash(reverse, s.length() - from)));
            int start = in.readInt() - 1;
            int end = in.readInt();
            if (curDirect.hash(start, end) == curReverse.hash(s.length() - end, s.length() - start)) {
                out.printLine("Yes");
            } else {
                out.printLine("No");
            }
        }
    }
}
