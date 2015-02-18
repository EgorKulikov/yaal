package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import javax.security.auth.login.AccountException;
import java.util.*;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        List<Delta> list = new ArrayList<>();
        int roleCount = in.readInt();
        for (int i = 0; i < roleCount; i++) {
            int from = in.readInt();
            int to = in.readInt();
            list.add(new Delta(from, to, -1, i));
        }
        int artistCount = in.readInt();
        for (int i = 0; i < artistCount; i++) {
            int from = in.readInt();
            int to = in.readInt();
            int value = in.readInt();
            list.add(new Delta(from, to, value, i));
        }
        NavigableSet<Delta> artists = new TreeSet<>();
        Collections.sort(list, new Comparator<Delta>() {
            @Override
            public int compare(Delta o1, Delta o2) {
                if (o1.from != o2.from) {
                    return o1.from - o2.from;
                }
                return o2.value - o1.value;
            }
        });
        int[] answer = new int[roleCount];
        for (Delta delta : list) {
            if (delta.value > 0) {
                artists.add(delta);
            } else {
                NavigableSet<Delta> tail = artists.tailSet(delta, false);
                if (tail.isEmpty()) {
                    out.printLine("NO");
                    return;
                }
                Delta artist = tail.first();
                artist.value--;
                answer[delta.index] = artist.index + 1;
                if (artist.value == 0) {
                    artists.remove(artist);
                }
            }
        }
        out.printLine("YES");
        out.printLine(answer);
    }

    static class Delta implements Comparable<Delta> {
        int from;
        int to;
        int value;
        int index;

        public Delta(int from, int to, int value, int index) {
            this.from = from;
            this.to = to;
            this.value = value;
            this.index = index;
        }

        @Override
        public int compareTo(Delta o) {
            if (to != o.to) {
                return to - o.to;
            }
            if ((value == -1) != (o.value == -1)) {
                if (value == -1) {
                    return -1;
                } else {
                    return 1;
                }
            }
            return index - o.index;
        }
    }
}
