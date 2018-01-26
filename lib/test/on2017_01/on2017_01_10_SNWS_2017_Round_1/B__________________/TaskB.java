package on2017_01.on2017_01_10_SNWS_2017_Round_1.B__________________;



import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.ArrayUtils.maxElement;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.readString();
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        int max = maxElement(count);
        String result = "";
        for (int i = 0; i < 26; i++) {
            if (count[i] != max) {
                continue;
            }
            IntList positions = new IntArrayList();
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == 'a' + i) {
                    positions.add(j);
                }
            }
            for (int k = 1; ; k++) {
                boolean good = true;
                for (int j = 0; j < positions.size(); j++) {
                    int pos = positions.get(j);
                    if (pos + k >= s.length()) {
                        good = false;
                        break;
                    }
                    if (s.charAt(pos + k) != s.charAt(positions.get(0) + k)) {
                        good = false;
                        break;
                    }
                }
                if (!good) {
                    String candidate = s.substring(positions.get(0), positions.get(0) + k);
                    if (candidate.length() > result.length() || candidate.length() == result.length() && candidate
                            .compareTo(result) < 0) {
                        result = candidate;
                    }
                    break;
                }
            }
        }
        out.printLine(result);
    }
}
