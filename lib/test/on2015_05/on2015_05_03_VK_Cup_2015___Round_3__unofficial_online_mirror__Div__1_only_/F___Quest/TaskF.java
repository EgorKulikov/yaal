package on2015_05.on2015_05_03_VK_Cup_2015___Round_3__unofficial_online_mirror__Div__1_only_.F___Quest;


import net.egork.generated.collections.comparator.IntComparator;
import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
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
            quests[i].sort(IntComparator.REVERSE);
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
