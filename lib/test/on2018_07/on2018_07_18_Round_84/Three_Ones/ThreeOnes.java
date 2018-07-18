package on2018_07.on2018_07_18_Round_84.Three_Ones;



import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class ThreeOnes {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        char[] s = in.readCharArray(n);
        IntList ones = new IntArrayList();
        ones.add(-1);
        for (int i = 0; i < n; i++) {
            if (s[i] == '1') {
                ones.add(i);
            }
        }
        ones.add(n);
        int answer = 0;
        for (int i = 3; i < ones.size(); i++) {
            answer = Math.max(answer, ones.get(i) - ones.get(i - 3));
        }
        out.printLine(answer);
    }
}
