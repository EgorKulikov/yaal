package on2012_12.on2012_12_27_Volume_7._1671___Anansi_s_Cobweb;



import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Task1671 {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int edgeCount = in.readInt();
        int[] from = new int[edgeCount];
        int[] to = new int[edgeCount];
        IOUtils.readIntArrays(in, from, to);
        int willDestroy = in.readInt();
        int[] order = IOUtils.readIntArray(in, willDestroy);
        MiscUtils.decreaseByOne(from, to, order);
        boolean[] destroyed = new boolean[edgeCount];
        for (int i : order)
            destroyed[i] = true;
        IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(count);
        for (int i = 0; i < edgeCount; i++) {
            if (!destroyed[i])
                setSystem.join(from[i], to[i]);
        }
        int[] answer = new int[willDestroy];
        for (int i = willDestroy - 1; i >= 0; i--) {
            answer[i] = setSystem.getSetCount();
            setSystem.join(from[order[i]], to[order[i]]);
        }
        out.printLine(answer);
    }
}
