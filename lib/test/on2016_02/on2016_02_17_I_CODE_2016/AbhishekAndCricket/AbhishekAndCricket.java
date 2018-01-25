package on2016_02.on2016_02_17_I_CODE_2016.AbhishekAndCricket;



import net.egork.generated.collections.set.LongHashSet;
import net.egork.generated.collections.set.LongSet;
import net.egork.string.SimpleStringHash;
import net.egork.string.StringHash;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class AbhishekAndCricket {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.readString();
        char[] isGood = in.readCharArray(26);
        int k = in.readInt();
        LongSet set = new LongHashSet();
        StringHash hash = new SimpleStringHash(s);
        for (int i = 0; i < s.length(); i++) {
            int bad = 0;
            for (int j = i; j < s.length(); j++) {
                if (isGood[s.charAt(j) - 'a'] == 'b') {
                    if (++bad > k) {
                        break;
                    }
                }
                set.add(hash.hash(i, j + 1));
            }
        }
        out.printLine(set.size());
    }
}
