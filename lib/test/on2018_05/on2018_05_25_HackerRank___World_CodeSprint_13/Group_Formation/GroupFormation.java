package on2018_05.on2018_05_25_HackerRank___World_CodeSprint_13.Group_Formation;



import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.collections.map.Indexer;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.sort;
import static net.egork.misc.ArrayUtils.createArray;
import static net.egork.misc.ArrayUtils.maxElement;

public class GroupFormation {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int a = in.readInt();
        int b = in.readInt();
        int[] limit = in.readIntArray(3);
        int[] size = createArray(n, 1);
        int[][] byGrade = new int[3][n];
        Indexer<String> indexer = new Indexer<>();
        String[] names = new String[n];
        for (int i = 0; i < n; i++) {
            indexer.get(names[i] = in.readString());
            byGrade[in.readInt() - 1][i] = 1;
        }
        IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(n);
        setSystem.setListener(((joinedRoot, root) -> {
            size[root] += size[joinedRoot];
            for (int i = 0; i < 3; i++) {
                byGrade[i][root] += byGrade[i][joinedRoot];
            }
        }));
        for (int i = 0; i < m; i++) {
            int j = setSystem.get(indexer.get(in.readString()));
            int k = setSystem.get(indexer.get(in.readString()));
            if (j == k || size[j] + size[k] > b) {
                continue;
            }
            boolean good = true;
            for (int l = 0; l < 3; l++) {
                if (byGrade[l][j] + byGrade[l][k] > limit[l]) {
                    good = false;
                    break;
                }
            }
            if (!good) {
                continue;
            }
            setSystem.join(j, k);
        }
        int maxSize = maxElement(size);
        if (maxSize < a) {
            out.printLine("no groups");
            return;
        }
        List<String> answer = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (size[setSystem.get(i)] == maxSize) {
                answer.add(names[i]);
            }
        }
        sort(answer);
        for (String s : answer) {
            out.printLine(s);
        }
    }
}
