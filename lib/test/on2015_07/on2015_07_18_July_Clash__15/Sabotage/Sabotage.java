package on2015_07.on2015_07_18_July_Clash__15.Sabotage;


import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Sabotage {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int edgeCount = in.readInt();
        int[] from = new int[edgeCount];
        int[] to = new int[edgeCount];
        IOUtils.readIntArrays(in, from, to);
        MiscUtils.decreaseByOne(from, to);
        IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(count);
        IntList answer = new IntArrayList();
        for (int i = edgeCount - 1; i >= 0; i--) {
            if (!setSystem.join(from[i], to[i])) {
                answer.add(i + 1);
            }
        }
        answer.inPlaceReverse();
        out.printLine(answer.size());
        for (int i : answer.toArray()) {
            out.printLine(i);
        }
    }
}
