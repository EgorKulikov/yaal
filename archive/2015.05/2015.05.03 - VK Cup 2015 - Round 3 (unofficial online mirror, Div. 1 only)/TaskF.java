package net.egork;

import net.egork.collections.comparators.IntComparator;
import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int time = in.readInt();
        int[] required = new int[count];
        int[] weight = new int[count];
        IOUtils.readIntArrays(in, required, weight);
        IntList[] quests = new IntList[time + 1];
        for (int i = 0; i <= time; i++) {
            quests[i] = new IntArrayList();
        }
        for (int i = 0; i < count; i++) {
            quests[required[i]].add(weight[i]);
        }
        for (int i = 0; i < time; i++) {
            quests[i].inPlaceSort(IntComparator.REVERSE);
            for (int j = 0; j < quests[i].size(); j += 2) {
                int current = quests[i].get(j);
                if (j + 1 < quests[i].size()) {
                    current += quests[i].get(j + 1);
                }
                quests[i + 1].add(current);
            }
        }
        out.printLine(quests[time].max());
    }
}
