package on2016_06.on2016_06_28_Week_of_Code_21.Luck_Balance;



import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class LuckBalance {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        IntList important = new IntArrayList();
        long answer = 0;
        for (int i = 0; i < n; i++) {
            int l = in.readInt();
            int t = in.readInt();
            if (t == 0) {
                answer += l;
            } else {
                important.add(l);
            }
        }
        important.sort();
        important.inPlaceReverse();
        for (int i = 0; i < k && i < important.size(); i++) {
            answer += important.get(i);
        }
        for (int i = k; i < important.size(); i++) {
            answer -= important.get(i);
        }
        out.printLine(answer);
    }
}
