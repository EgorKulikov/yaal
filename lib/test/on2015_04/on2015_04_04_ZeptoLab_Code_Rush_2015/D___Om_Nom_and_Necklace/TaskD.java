package on2015_04.on2015_04_04_ZeptoLab_Code_Rush_2015.D___Om_Nom_and_Necklace;


import net.egork.generated.collections.pair.IntIntPair;
import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int repeats = in.readInt();
        String s = in.readString();
        int[] z = StringUtils.zAlgorithm(s);
        List<IntIntPair> valid = new ArrayList<>();
        int lastBegin = -1;
        int lastEnd = -1;
        int at = 0;
        int[] answer = new int[count];
        for (int i = 1; i <= count; i++) {
            if (i < count && z[i] >= (repeats - 1L) * i) {
                int nBegin = repeats * i;
                int nEnd = Math.min((repeats + 1) * i, z[i] + i);
                if (nBegin > lastEnd) {
                    if (lastBegin != -1) {
                        valid.add(new IntIntPair(lastBegin, lastEnd));
                    }
                    lastBegin = nBegin;
                    lastEnd = nEnd;
                } else {
                    lastEnd = Math.max(lastEnd, nEnd);
                }
            }
            while (at < valid.size() && i > valid.get(at).second) {
                at++;
            }
            if (at < valid.size()) {
                answer[i - 1] = (i >= valid.get(at).first) ? 1 : 0;
            } else {
                answer[i - 1] = (i >= lastBegin && i <= lastEnd) ? 1 : 0;
            }
        }
        if (repeats == 1) {
            Arrays.fill(answer, 1);
        }
        for (int i : answer) {
            out.print(i);
        }
        out.printLine();
    }
}
