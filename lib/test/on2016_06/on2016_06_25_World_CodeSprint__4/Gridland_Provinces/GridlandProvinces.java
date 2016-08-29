package on2016_06.on2016_06_25_World_CodeSprint__4.Gridland_Provinces;



import net.egork.generated.collections.set.LongHashSet;
import net.egork.generated.collections.set.LongSet;
import net.egork.string.CompositeStringHash;
import net.egork.string.SimpleStringHash;
import net.egork.string.StringHash;
import net.egork.string.SubstringStringHash;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static net.egork.io.IOUtils.readStringArray;
import static net.egork.string.StringUtils.reverse;

public class GridlandProvinces {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        String[] s = readStringArray(in, 2);
        LongSet set = new LongHashSet();
        addAll(n, s, set);
        for (int i = 0; i < 2; i++) {
            s[i] = reverse(s[i]);
        }
        addAll(n, s, set);
        out.printLine(set.size());
    }

    protected void addAll(int n, String[] s, LongSet set) {
        StringHash[] simple = new StringHash[2];
        for (int i = 0; i < 2; i++) {
            simple[i] = new SimpleStringHash(s[i]);
        }
        StringHash[] reverse = new StringHash[2];
        for (int i = 0; i < 2; i++) {
            reverse[i] = new SimpleStringHash(reverse(s[i]));
        }
        StringHash[] snail = new StringHash[2];
        for (int i = 0; i < 2; i++) {
            StringBuilder current = new StringBuilder(2 * n);
            for (int j = 0; j < n; j++) {
                current.append(s[i ^ (j & 1)].charAt(j));
                current.append(s[1 - (i ^ (j & 1))].charAt(j));
            }
            snail[i] = new SimpleStringHash(current.toString());
        }
        for (int i = 0; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                for (int k = 0; k < 2; k++) {
                    StringHash hash = new CompositeStringHash(new SubstringStringHash(reverse[k], n - i, n),
                                      new CompositeStringHash(new SubstringStringHash(simple[1 - k], 0, i),
                                      new CompositeStringHash(new SubstringStringHash(snail[1 - (k ^ (i & 1))], 2 *
                                              i, 2 * j),
                                      new CompositeStringHash(new SubstringStringHash(simple[1 - (k ^ ((j - i) & 1))
                                              ], j, n),
                                      new SubstringStringHash(reverse[(k ^ ((j - i) & 1))], 0, n - j)))));
                    set.add(hash.hash(0));
                }
            }
        }
    }
}
