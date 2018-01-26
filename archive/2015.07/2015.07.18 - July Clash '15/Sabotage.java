package net.egork;

import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

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
