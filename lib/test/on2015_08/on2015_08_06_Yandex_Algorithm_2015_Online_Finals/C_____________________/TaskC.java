package on2015_08.on2015_08_06_Yandex_Algorithm_2015_Online_Finals.C_____________________;


import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] u = new int[n - 1];
        int[] d = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            String[] parts = in.readString().split("/");
            u[i] = Integer.parseInt(parts[0]);
            d[i] = Integer.parseInt(parts[1]);
        }
        ArrayUtils.orderBy(d, u);
        int[] div = IntegerUtils.generateDivisorTable(n + 1);
        int[] a = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            a[i] = div[u[i]];
        }
        int[] position = new int[n - 1];
        Arrays.fill(position, -1);
        int current = 2;
        IntList answer = new IntArrayList();
        while (true) {
            if (position[current - 2] != -1) {
                out.printLine(answer.size() - position[current - 2]);
                List<String> list = new ArrayList<>();
                for (int i = position[current - 2]; i < answer.size(); i++) {
                    list.add(u[answer.get(i)] + "/" + d[answer.get(i)]);
                }
                out.printLine(list.toArray());
                return;
            }
            position[current - 2] = answer.size();
            answer.add(current - 2);
            current = a[current - 2];
        }
    }
}
