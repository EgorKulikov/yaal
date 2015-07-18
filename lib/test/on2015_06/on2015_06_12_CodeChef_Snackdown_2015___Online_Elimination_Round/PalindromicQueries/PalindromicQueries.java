package on2015_06.on2015_06_12_CodeChef_Snackdown_2015___Online_Elimination_Round.PalindromicQueries;


import net.egork.string.CompositeStringHash;
import net.egork.string.SimpleStringHash;
import net.egork.string.StringHash;
import net.egork.string.StringUtils;
import net.egork.string.SubstringStringHash;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class PalindromicQueries {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.readString();
        StringHash direct = new SimpleStringHash(s);
//        String ds = s;
        StringHash reverse = new SimpleStringHash(StringUtils.reverse(s));
//        String rs = StringUtils.reverse(s);
        int count = in.readInt();
        for (int i = 0; i < count; i++) {
            int from = in.readInt() - 1;
            int to = in.readInt();
            StringHash curDirect = new CompositeStringHash(new SubstringStringHash(direct, 0, from),
                new CompositeStringHash(new SubstringStringHash(reverse, s.length() - to, s.length() - from),
                new SubstringStringHash(direct, to)));
//            String cds = ds.substring(0, from) + rs.substring(s.length() - to, s.length() - from) + ds.substring(to);
            StringHash curReverse = new CompositeStringHash(new SubstringStringHash(reverse, 0, s.length() - to),
                new CompositeStringHash(new SubstringStringHash(direct, from, to),
                new SubstringStringHash(reverse, s.length() - from)));
//            String crs = rs.substring(0, s.length() - to) + ds.substring(from, to) + rs.substring(s.length() - from);
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
