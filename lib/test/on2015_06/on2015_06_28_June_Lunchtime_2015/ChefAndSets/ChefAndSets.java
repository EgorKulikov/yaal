package on2015_06.on2015_06_28_June_Lunchtime_2015.ChefAndSets;



import net.egork.collections.set.TreapSet;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class ChefAndSets {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int size = in.readInt();
        int count = in.readInt();
        List<TreapSet<Integer>> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            TreapSet<Integer> set = new TreapSet<>();
            set.add(i + 1);
            list.add(set);
        }
        for (int i = 0; i < count; i++) {
            String type = in.readString();
            if ("UNION".equals(type)) {
                int first = in.readInt() - 1;
                int second = in.readInt() - 1;
                TreapSet<Integer> left = list.get(first);
                TreapSet<Integer> right = list.get(second);
                if (left.size() > right.size()) {
                    left.addAll(right);
                    list.add(left);
                } else {
                    right.addAll(left);
                    list.add(right);
                }
            } else {
                int index = in.readInt() - 1;
                int inside = in.readInt() - 1;
                out.printLine(list.get(index).get(inside));
            }
        }
    }
}
