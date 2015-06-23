package on2015_06.on2015_06_12_CodeChef_Snackdown_2015___Online_Elimination_Round.LexNext;



import net.egork.collections.intervaltree.IntervalTree;
import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.collections.intervaltree.ReadOnlyIntervalTree;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.string.SimpleStringHash;
import net.egork.string.StringHash;
import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

public class LexNext {
    enum Type {
        SPLIT,
        REMOVE
    }

    static class Event {
        int position;
        int atLength;
        Type type;

        public Event(int position, int atLength, Type type) {
            this.position = position;
            this.atLength = atLength;
            this.type = type;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.readString();
        int count = in.readInt();
        int[] index = new int[count];
        int[] length = new int[count];
        IOUtils.readIntArrays(in, index, length);
        int[] sArray = ArrayUtils.reversePermutation(StringUtils.suffixArray(s + "$"));
        sArray = Arrays.copyOfRange(sArray, 1, s.length() + 1);
        StringHash hash = new SimpleStringHash(s);
        IntervalTree min = new ReadOnlyIntervalTree(ArrayUtils.asLong(sArray)) {
            @Override
            protected long neutralValue() {
                return s.length();
            }

            @Override
            protected long joinValue(long left, long right) {
                return Math.min(left, right);
            }
        };
        int[] from = new int[s.length()];
        int[] to = new int[s.length()];
        to[0] = s.length() - 1;
        IntervalTree representative = new LongIntervalTree(s.length()) {
            @Override
            protected long joinValue(long left, long right) {
                return left == neutralValue() ? right : left;
            }

            @Override
            protected long joinDelta(long was, long delta) {
                return delta == neutralDelta() ? was : delta;
            }

            @Override
            protected long accumulate(long value, long delta, int length) {
                return delta == neutralDelta() ? value : delta;
            }

            @Override
            protected long neutralValue() {
                return -1;
            }

            @Override
            protected long neutralDelta() {
                return -1;
            }

            @Override
            protected long initValue(int index) {
                return 0;
            }
        };
        int[] reverse = ArrayUtils.reversePermutation(sArray);
        NavigableSet<Integer> set = new TreeSet<>((fi, se) -> reverse[fi] - reverse[se]);
        int[] order = ArrayUtils.order(length);
        Queue<Event> queue = new PriorityQueue<>((fi, se) -> fi.atLength != se.atLength ? fi.atLength - se.atLength : fi.type.ordinal() - se.type.ordinal());
        for (int i = 0; i < s.length(); i++) {
            queue.add(new Event(i, s.length() - sArray[i], Type.REMOVE));
        }
        for (int i = 1; i < s.length(); i++) {
            int left = 0;
            int right = Math.min(s.length() - sArray[i], s.length() - sArray[i - 1]);
            while (left < right) {
                int middle = (left + right + 1) >> 1;
                if (hash.hash(sArray[i], sArray[i] + middle) == hash.hash(sArray[i - 1], sArray[i - 1] + middle)) {
                    left = middle;
                } else {
                    right = middle - 1;
                }
            }
            queue.add(new Event(i, left, Type.SPLIT));
        }
        int[] answer = new int[count];
        for (int i : order) {
            while (!queue.isEmpty() && queue.peek().atLength < length[i]) {
                Event event = queue.poll();
                if (event.type == Type.REMOVE) {
                    set.remove((int)representative.query(event.position, event.position));
                } else {
                    int id = (int) representative.query(event.position, event.position);
                    int left = (int) min.query(from[id], event.position - 1);
                    int right = (int) min.query(event.position, to[id]);
                    from[left] = from[id];
                    to[right] = to[id];
                    to[left] = event.position - 1;
                    from[right] = event.position;
                    representative.update(from[left], to[left], left);
                    representative.update(from[right], to[right], right);
                    set.add(left);
                    set.add(right);
                }
            }
            int current = (int) representative.query(reverse[index[i] - 1], reverse[index[i] - 1]);
            Integer result = set.higher(current);
            if (result == null) {
                answer[i] = -1;
            } else {
                answer[i] = result + 1;
            }
        }
        for (int i : answer) {
            out.printLine(i);
        }
    }
}
