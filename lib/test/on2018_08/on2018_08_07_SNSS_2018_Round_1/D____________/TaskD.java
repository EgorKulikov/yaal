package on2018_08.on2018_08_07_SNSS_2018_Round_1.D____________;



import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import net.egork.string.ExpandableStringHash;

import java.util.InputMismatchException;

import static net.egork.misc.ArrayUtils.createArray;

public class TaskD {
    int[] first;
    int[] next;
    char[] label;
    ExpandableStringHash[] hash;
    int[] length;
    int[] end;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        try {
            int n = in.readInt();
            first = createArray(n, -1);
            next = new int[n - 1];
            label = new char[n - 1];
            hash = new ExpandableStringHash[n];
            end = new int[n];
            length = new int[n];
            for (int i = 0; i < n - 1; i++) {
                int from = in.readInt() - 1;
                label[i] = in.readCharacter();
                next[i] = first[from];
                first[from] = i;
            }
            for (int i = n - 1; i >= 0; i--) {
                go(i);
            }
            for (int i = 0; i < n; i++) {
                if (length[i] == 0) {
                    out.printLine(0);
                } else {
                    out.printLine(end[i] + 1);
                }
            }
        } catch (InputMismatchException e) {}
    }

    private void go(int vertex) {
        hash[vertex] = new ExpandableStringHash();
        end[vertex] = vertex;
        for (int i = first[vertex]; i != -1; i = next[i]) {
            hash[i + 1].add(label[i]);
            int c = compare(hash[i + 1], hash[vertex]);
            if (c > 0 || c == 0 && end[i + 1] < end[vertex]) {
                hash[vertex] = hash[i + 1];
                end[vertex] = end[i + 1];
                length[vertex] = length[i + 1] + 1;
            }
        }
    }

    private int compare(ExpandableStringHash a, ExpandableStringHash b) {
        int length = a.length();
        if (length != b.length()) {
            return Integer.compare(length, b.length());
        }
        int left = 0;
        int right = length;
        while (left < right) {
            int middle = (left + right + 1) >> 1;
            if (a.hash(length - middle) != b.hash(length - middle)) {
                right = middle - 1;
            } else {
                left = middle;
            }
        }
        if (left == length) {
            return 0;
        }
        return Character.compare(a.charAt(length - left - 1), b.charAt(length - left - 1));
    }
}
